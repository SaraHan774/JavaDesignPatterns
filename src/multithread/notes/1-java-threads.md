## Java 언어의 쓰레드 
- 처리의 흐름이 계속 한 줄의 실처럼 이어지는 프로그램을 Single Threaded Program 이라고 한다. 
- 프로그램을 실행하는 주체를 Java에서는 Thread of Control 이라 한다. 

```
무대 뒤에서 움직이는 쓰레드 
* Garbage collection 용 쓰레드나 GUI 관련 쓰레드 등 
```

### 멀티 쓰레드 프로그램 
* GUI 응용 프로그램 
    * 검색을 실행하는 쓰레드 
    * 버튼을 표시하고 누르면 검색을 중단할 수 있도록 하는, UI를 담당하는 쓰레드 

* 시간이 걸리는 IO처리 
    * File 이나 Network IO 
    
* 복수 클라이언트 
    * 일반적으로 네트워크 상에서 동작하는 서버는 여러 클라이언트를 동시에 상해야하 함. 
    * 클라이언트가 서버에 접속했을 때 그 클라이언트를 상대할 쓰레드를 준비한다. 
    
### Thread 클래스의 run, start 
* `java.lang.Thread` 클래스 사용 

### 순차, 병렬, 병행 
* 순차 (Sequential) : 복수의 업무를 순서대로 처리해 나가는 것. (CPU 한 개)
* 병렬 (Parallel) : 복수의 업무를 동시에 처리해 나가는 것. 10개의 업무를 2명이 처리하는 등. (CPU 여러개) 
* 병행 (Concurrent) : 순차 병렬보다 추상도가 높은 표현. 한 개의 업무를 어떠한 순서로 처리하든 상관 없는 여러개의 작업으로 분할하는 모습. 
10개의 업무를 두 종류의 독립된 작업으로 분할해 두면 병행 처리할 수 있다. 1명이 분할된 작업을 `순차적으로`처리할 수 도 있고, 2명이 분할된
작업을 `병렬적으로`처리할 수 도 있다. 

## Thread 의 기동 
1. Thread 클래스의 서브 클래스 인스턴스를 사용해 기동한다. 
2. Runnable 인터페이스의 구현 클래스 인스턴스를 사용해 기동한다. 

### 프로그램의 종료 
Java 프로그램은 데몬 스레드를 제외한 모든 스레드가 종료한 시점에서 종료된다. 
데몬 스레드란 무대 뒤에서 이뤄지는 작업을 시키기 위한 스레드이며, setDaemon 메서드를 사용해 데몬 스레드를 만들 수 있다. 

### `ThreadFactory` 에서의 스레드 생성 
`java.util.concurrent` 패키지 안에는 스레드 생성을 추상화시킨 ThreadFactory 인터페이스가 포함되어 있다. 
`Thread(Runnable)` 하는 부분을 `ThreadFactory` 내무에 숨길 수 있음. 

```
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        threadFactory.newThread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello Thread Factory!");
            }
        }).start();
```

### Thread 클래스와 Runnable 인터페이스 
Thread 클래스 자체적으로 Runnable 인터페이스를 구현하고 있고, run 메서드를 가진다. 
다만 Thread 클래스의 run 메서드의 body 는 비어있다. 서브 클래스가 override 해서 
실행할 코드를 작성해야 한다. 

## Thread 의 일시 정지 
* Thread 클래스의 sleep 메서드를 사용한다.
* `Thread.sleep(밀리초, 나노초)` 
```
try{
    Thread.sleep(1000); 
}catch(InterruptedException e){
    e.printStackTrace(); 
}
```

## Thread 의 배타 제어 
* data race 를 막기 위해 synchronized 키워드를 사용한다. 
* synchronized 키워드를 사용해 선언된 메서드는 synchronized 메서드 혹은 동기 메서드라고 한다. 
* 키워드 표시된 메서드에 접근하기 위해서는 lock 의 acquire 해야 하고, 사용이 끝난 후 lock 을 release 해야 한다. 
* lock 은 인스턴스마다 존재한다. 

### lock 과 모니터 
스레드의 배타제어를 하는 구조를 monitor 라 하며, 락을 취하고 있는 것을 "monitor 를 소유(own)한다" / "lock 을 hold 한다"라고 함.
현재의 스레드가 어떠한 객체의 락을 설정하고 있는지 여부는 `Thread.holdsLock` 메서드로 조사 가능. 
```
assert Thread.holdsLock(Object)
``` 

### synchronized block 
메서드 전체가 아니라 일부의 코드만 하나의 스레드로 동작하게 하고 싶은 경우 
```
void method(){

synchronized(this){ ... }
 
}
```

## Thread 의 협조
좀 더 치밀하게 스레드를 제어하고 싶은 경우 
- 영역이 비어있으면 데이터를 써넣지만 비어있지 않다면 빌 때 까지 `기다린다`. 
- 비어 있다고 하는 사실을 대기중인 스레드에게 `알린다`. 

### wait set : Thread 대합실 
* wait set : 인스턴스의 wait 메서드를 실행한 후 정지하고 있는 스레드들의 집합. 
* wait set 에서 나오는 경우 
    * 다른 스레드에서 notify() 호출
    * notifyAll() 호출
    * interrupt() 호출
    * wait 메서드가 타임아웃 되는 경우 
    
* notify 로 깨어난 스레드가 notify 를 한 순간에 실행을 재개하는 것은 아니다. notify 를 한 순간에는 notify 스레드가 
락을 가지고 있으므로 다른 어떠한 스레드도 그 인스턴스의 락을 취할 수 없다. 

* **notify 로 인해서 어떤 스레드가 선택될지는 정해져 있지 않다. 이에 의존해 프로그램 짜지 말 것.**

* notifyAll() 은 모든 스레드들을 다 깨우는 것. 

* 만약 lock 을 확보하지 못한 스레드가 wait, notify, notifyAll 을 호출하면 `java.lang.IllegalMonitorStateException`이 일어난다. 

* 일반적으로 notifyAll() 을 사용하는 것이 안전하다. 

* wait, notify, notifyAll 은 `java.lang.Object` 클래스의 메소드이지, Thread 클래스의 고유 메서드는 아니다. 


### Java Thread LifeCycle 
* [Lifecycle Image](./java-thread-lifecycle.jpg)


## 멀티스레드 프로그램의 평가 기준 
1. 안전성 (Safety) : 객체를 망가뜨리지 않는 것. 객체의 필드가 예상 외의 값을 취하면 안된다. 
    * 복수의 스레드가 이용해도 안전성이 유지되는 클래스를 Thread Safe 한 클래스라고 한다. 
    * Vector 클래스는 thread safe 하지만, ArrayList 클래스는 thread safe 한 클래스가 아니다. 
    * cf. 어떤 클래스가 스레드 세이프인지 아닌지는 synchronized 메서드인 것과는 관계 없음. 

2. 생존성 (liveness) : 필요한 처리가 언젠가 반드시 이뤄지는 것. 생존성 대신 활동성이란 표현이 사용되는 경우도 있다. 
    * 객체를 망가뜨리지 않는다 해서 무조건 좋은 프로그램이 아니다. 프로그램이 동작 중 멈추면 더 이상 처리가 실행되지 않아 
    객체의 상태가 변화하지 않는다. 안전성은 확보되나, 프로그램이 작동하지 않으면 아무 소용이 없다. 
    * 안전성과 생존성은 서로 상반될수도. 대표적인 예가 deadlock. 복수의 스레드가 서로 상대편에서 락을 해제하기를 서로 기다리는 상황 
    
3. 재사용성 (reusability) : 클래스를 다시 사용할 수 있는 것. 필수는 아니나 프로그램의 품질을 높이는 데 필요. 
    * 클래스를 마치 부품처럼 잘라낼 수 있다면 그 클래스는 재사용성이 높다고 한다. 
    * 멀티스레드 프로그램에서는 스레드 배타제어의 구조나 방침을 클래스 안에 잘 숨기면 재사용성이 높은 프로그램이 된다. 
    
4. 수행 능력 (performance) : 처리를 고속, 대량으로 할 수 있는 것. 
* 수행 능력에는 복수의 요소가 관련되어 있다. 
    * Throughput : 단위 시간 당 처리 수
    * Responsiveness : 요구를 한 뒤 반응이 나타날 때 까지 걸리는 시간. 응답성이 높은 것을 대기 시간 (latency) 가 짧다고 표현하기도 한다. 
    * Capacity : 동시에 처리할 수 있는 수. 예) 서버가 동시에 처리할 수 있는 클라이언트의 수 혹은 파일의 수. 
    * 그 밖에도 Efficiency, Scalability, Degradation 등 다수의 평가기준이 있다. 

> 이들 요소는 서로 상반되는 경우가 있다. Trade-off 관계에 있다고 말한다. 예를들어 처리량을 높이고자 할 때 
> 응답성이 낮아질수도 있고, 안전성을 높이려다 보면 Throughput 이 낮아질 수 도 있다. 

한편, 설계에서 사용되는 평가기준을 Pattern 에서는 Force 라고 부르기도 한다. 포스는 설계자가 우연히 맞닥뜨리게 되는 
`제약`이자 `압력` 이다. 안전성, 생존성, 재사용성, 수행 능력은 병행성과 관련 패턴에서 특히 중요한 force 이다. 






