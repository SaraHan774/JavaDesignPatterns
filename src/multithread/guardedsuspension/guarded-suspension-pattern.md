## Guarded Suspension 
* **지금 이 처리를 실행하면 안될 때 처리하기 직전에 쓰레드를 기다리게 하는 패턴.**
* 여러 명칭이 있다 : guarded wait, spin lock 등 

### 예제 프로그램의 시퀀스 다이어그램 

### 예제 프로그램
* [Guarded Suspension 예시 코드](./GuardedSuspensionEx.java)
* Output : 
```
Alice requests Request{name='NO. 0'}
Bobby handles Request{name='NO. 0'}
Alice requests Request{name='NO. 1'}
Alice requests Request{name='NO. 2'}
Bobby handles Request{name='NO. 1'}
Bobby handles Request{name='NO. 2'}
Alice requests Request{name='NO. 3'}
Bobby handles Request{name='NO. 3'}
Alice requests Request{name='NO. 4'}
Bobby handles Request{name='NO. 4'}
...
``` 

### 가드로 보호받고 있다 
* getRequest 메서드의 목적 : queue 에서 Request 인스턴스를 한 개 꺼내고 싶다. 
    * queue.remove() 의 실행 
* 안전하게 실행되기 위한 조건 : `queue.peek() != null` 
    * **이것을 Guarded Suspension 패턴의 가드 조건이라 한다.**
* while 문의 조건식은 가드 조건의 논리 부정이다. 
    * `queue.peek() == null` 즉, 비어있을 동안 실행할 코드들이 들어가 있음. 
    * **`queue.remove()` 가 호출될 때에는 큐가 반드시 비어있는 상태가 아니라는 것을 보장한다.**

### wait 를 이용해 조건의 변화를 기다린다
wait() 를 실행하고서 기다리는 쓰레드는 인스턴스의 상태 변화를 기다리는 것이다. 
인스턴스 상태가 바뀌어 가드 조건이 충족되기를 기다리는 상태. 
당연한 이야기인것 같지만, 확실히 기억해야 할 것은 `쓰레드가 기다리고 있는 이유`를 알면 `언제 notify/notifyAll 하면 좋을지` 를 
알 수 있다. 

```
while(가드 조건의 논리부정){
    wait 로 대기한다
}
목적하는 처리를 한다. 
``` 
* 목적하는 처리를 실행하기 직전에는 반드시 가드 조건이 충족되어 있다. 
* 사전 조건 (Precondition)

### putRequest 살펴보기 
* synchronized 는 무엇을 지키고 있는가? 
    * queue 필드를 지키고 있다. 

### wait 과 lock 
wait 을 하려면 스레드가 그 인스턴스의 락을 취하고 있어야 함. 
synchronized 메서드 안에서 wait 메서드를 호출하면 this 의 락을 취하고 있는 것. 
this 의 wait 을 호출하면 this의 wait set 안으로 들어가고, 쓰레드가 갖고 있던 this의 락은 해제된다. 
**notify 로 다시 나올 때 this 의 락을 다시 한 번 취해야 한다.**

<br/><br/>

## Guarded Suspension 패턴의 등장 인물 
* Guarded Object
    * 역할 : guardedMethod 외에 인스턴스의 상태를 변화시키는 메소드 (state changing method) 를 가지는 경우도 있음. 
    * Java 에서는 while, wait 을 통해 guardedMethod 를 실현 
    * notify, notifyAll 을 통해 stateChangingMethod 를 실현 
    * 예제에서는 RequestQueue 가 그 역할을 함. 
    
### 상태 변경의 망각과 생존성 
프로그램의 실수로 Guarded Object 역할의 상태를 변경하는 것을 잊었다면 가드 조건은 시간이 지나도 충족되지 않는다. 
notify 를 아무리 해도 가드 조건을 다시 확인하기 때문에 프로그램은 **생존성을 잃는다.**

* **wait 메서드의 인자값에 time out 시간을 지정하면 시간 경과 후 처리를 중단한다.**

### wait & notify 의 책임 `[재사용성]`
예제 프로그램에서 RequestQueue 클래스 안에 notify 와 wait 가 갇혀있다. 
이것은 RequestQueue 의 재사용성 관점에서 중요한 사실이다. 
**이 클래스를 이용하는 측에서는 단지 이 클래스 안의 put, get 메소드만 호출하면 된다.**

### 여러가지 이름
1. Guarded Suspension : 가드되어 있는 실행을 일시 중단한다. 
2. Guarded Wait : 가드 상태에서 기다린다. 쓰레드가 wait 에서 기다리고 notify 가 다시 실행되어 조건을 다시 테스트 하도록 구현. 
* 기다리는 쪽의 예 
```
while(!ready){
    wait() 
}
``` 

* 깨우는 쪽의 예 
```
ready = true 
notifyAll() 
```
3. Busy Wait : 바쁘게 기다린다. 쓰레드가 wait 에서 기다리는 것이 아니라 yield(가능한 다른 쓰레드에 우선권을 준다) 하면서 
조건을 테스트하는 구현 방법. **기다리는 쪽의 쓰레드도 계속 동작하므로 Java 실행 처리계의 시간을 낭비하게 된다.**

4. Spin Lock : 돌아가며 lock 한다. while 루프 안에서 돌아가며 기다리는 것. 
5. Polling : 여론 조사를 한다. 어떤 이벤트가 발생하는지 반복적으로 조사하러 가며, 이벤트가 일어난 경우 그것을 처리하는 방법. 
   
* Guarded Suspension 의 공통된 특징들 
    * 루프, 반복이 있다. 
    * 조건 테스트가 있다. 
    * 어떠한 의미에서든 기다린다. 
    
### LinkedBlockingQueue 
* 예제의 RequestQueue 처럼 동작하는 클래스. 
* take : 앞에서 요소를 꺼낸다.
* put : 큐 맨 뒤에 요소를 추가한다. 
* [LinkedBlockedQueue 예시 코드](./LinkedBlockingTest.java)

<br/><br/>

## 연습문제 
### 문제 3-1 (기초지식의 확인)
1. O / get 은 사용하는 쪽에서, put 은 요청하는 쪽에서 사용한다 
2. X / 요청큐 클래스의 인스턴스는 하나이다. 
3. O / remove 를 했다는 것은 큐가 null 이 아니라는 것을 의미한다. 
4. O / wait 을 했다는 것은 큐가 null 이었기 때문이다. 
5. X / put 메서드는 synchronized 선언이 되어 있으므로 하나의 쓰레드만이 들어갈 수 있다. 하지만 이것이 
서버 쓰레드가 다른 일을 할 수 없게 하는 것은 아니므로, 서버 쓰레드는 여전히 동작할 수 있다.  
6. X / getRequest 안에서 wait 메소드를 불러낸 쓰레드는 락을 해제하고 ~~queue 의 wait 셋으로 들어간다.~~
    * queue(Linked List 의 인스턴스) 의 wait 셋으로 들어가는 것이 아니라 this (RequestQueue 클래스의 인스턴스) 의 wait 셋 안으로 
    들어간다.    
7. X / notifyAll() 하는 것은 프로그램의 쓰레드를 깨우는 것이지 큐 객체에는 notifyAll 메서드가 존재하지 않는다.

### 문제 3-2 (notifyAll 의 위치)
~~* notifyAll 을 queue.offer(Request) 보다 먼저 실행하게 되면 큐가 비어있는 상태에서 notifyAll 을 
하게 될수도 있으므로 옳은 방법이 아니다. notifyAll 을 해도 큐가 비어있으면 다시 wait 하게 되는데 이 이후에 
queue.offer() 로 인스턴스의 상태를 변화시켰음에도 불구하고 notifyAll() 은 다시 호출되지 않으므로 
계속해서 wait 상태에 머물게 된다.~~
* notifyAll 을 먼저 실행해도 RequestQueue 는 안전하게 동작한다. 
notifyAll 을 실행한 시점에서 인수 request 는 아직 queue 에 추가되지 않는다. 
**그러나 notifyAll 을 실행한 쓰레드는 this 의 락을 갖고 있으므로 notifyAll 로 인해 
wait 셋 밖으로 나온 다른 쓰레드들은 락을 취하려고 전원 블록한다.**
따라서 쓰레드의 처리는 실질적으로 진행되지 않는다. (가드 조건도 테스트 하지 않는다)
<br/>
한편 notifyAll 을 실행한 쓰레드는 **queue.offer() 을 실행한 후 putRequest 메서드로부터
리턴한다. 이 때서야 this 의 락이 해제된다.** 그 후 블록되어 있던 다른 쓰레드(중의 한 개)가 this
의 락을 취하고 처리를 진행하게 된다. (우선 가드 조건을 테스트하러 간다)
<br/>
결국 putRequest 안에 있는 두 개 코드의 순서가 어떻든 간에 안전하게 작동한다. 
<br/>
**단, notifyAll 을 마지막에 적는 편이 이해하기 쉬우므로 그렇게 프로그램을 짜는 것.**    

### 문제 3-3 (디버그 프린트를 넣는다)
* wait 과 notifyAll 이 제대로 동작하는지 확인하기 위해 디버그 프린트문을 넣어보자. 

### 문제 3-4 (닮은 듯 닮지 않은 Guarded Suspension 패턴)
1. while 을 if 로 바꾼 경우 queue.peek() 가 한 번만 체크되어 요청 객체가 새로 들어온 것이 확인되지 않는다.
    * 해설: **wait하고 있던 쓰레드는 움직이기 전에 가드 조건을 다시 체크해야 합니다. 그러기 위해
      서는 if가 아니라 while을 사용해야 합니다.** 
2. synchronized 범위를 wait 으로만 한 경우 다른 서버 쓰레드가 요청 객체를 가져갈 수 도 있다.
    * 해설 : queue 안에 요소가 한 개 밖에 존재하지 않을 때 <그림 3-2>와 같이 2개의 쓰레드가 실
      행해 버리면 쓰레드 1에서 예외 NoSuchElementException이 통보됩니다.
      게다가 원래 queue 필드의 LinkedList 클래스는 쓰레드 세이프가 아닙니다.
      이 클래스를 사용하면 안전성이 떨어지게 됩니다 
3. ~~try ... catch 를 while 바깥으로 꺼낸 경우 아무런 변화가 일어나지 않는다. (🤔)~~
    * 해설 : 쓰레드가 wait하고 있는 중에 다른 쓰레드에서 interrupt 메소드를 호출했다고 합시다.
           그러면 **아직 가드 조건이 충족되지 않은 상태라 하더라도 이 쓰레드는 while문을 탈출하여
           catch절로 뛰어 들어가 remove를 호출해 버립니다.** 즉, 가드 조건이 충족될 때까지 기다
           린다고 하는 기능을 수행하지 못합니다.
4. wait 대신 Thread.sleep 을 사용한 경우 정해진 시간이 지난 후에 큐 인스턴스의 상태가 변화할 수 있는 여지가 없다.
    * wait을 실행했던 쓰레드는 대상 인스턴스의 락을 해제합니다.
      **한편 Thread.sleep는 인스턴스의 락을 해제하지 않습니다.**
      그러니까 getRequest라고 하는 synchronized 메소드 안에서 Thread.sleep를 실행해 버리면 그 어떤 쓰레드도
      putRequest 쓰레드나 getRequest 쓰레드로 들어갈 수 없습니다(블록해 버립니다).
    * 이렇게 움직이고는 있으나 실질적으로 전혀 진전이 없는 상황을 일반적으로 라이브 락(livelock)이라고 합니
      다. 라이브 락은 데드락과 마찬가지로 생존성을 잃은 상태입니다.

### 문제 3-5 (두개의 Guarded Suspension) 
* 두 개의 RequestQueue 클래스 만들어서 대화를 주고받는 쓰레드 만들기 
* 해답 : input, output 인스턴스를 서로 교차해서 넣어주고, 최초의 input 큐에다가 
Request 인스턴스를 전달한 후 쓰레드 두 개를 기동한다. 
