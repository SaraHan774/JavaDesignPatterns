## State Patterns 
* GoF 의 정의 : 객체의 내부 상태가 변화함에 따라 객체의 행동을 바꾸도록 하는 패턴이다. 
객체는 자신의 class 를 바꾸는 듯 보일 것이다. 

### Real-Life Example 
네트워크 연결을 생각해보자. 여기서 객체 (통신을 담당하는 객체)는 여러가지의 state
에 있을 수 있다. 예를들어 connection established, connection closed, listening 
to the connection 등 과 같은 상태에 있을 수 있다. 같은 맥락에서 교통 신호와 비슷하다고 
생각해도 된다. 

### Computer World Example 
위의 예시는 컴퓨터 세계에서도 적용 가능하다. 예를 들어보자 : 주어진 시간 동안 
어떠한 Job 을(혹은 여러개의 Job 들을) 프로세싱하는 어플리케이션이 있다고 가정하자.
만약 새로운 Job 이 생겨난다면 어플리케이션은 그 Job 을 프로세싱 하거나 혹은 이미
시스템이 처리할 수 있는 Job 의 최대 개수를 초과하였으므로 
새로운 Job 이 지금 당장 처리될 수 없음을 시그널할 것이다.
(i.e., 어플리케이션의 Job 프로세싱 능력이 최대치에 달했을 경우)

### Illustration 
본 예제에서는 아주 간단한 TV switching 매커니즘을 보여준다. (TV는 on 과 off 의 상태가 있음)
우리는 TV를 끄고 킬 수 있는 리모컨이 있다고 가정하자. 초기에는 TV가 off 인 상태로 있다. 
우리가 power 버튼을 누르면 TV는 on 상태가 될 것이다. 또 다시 한 번 power 버튼을 
누른다면 TV는 off 상태가 될 것이다. 우리는 이 상황을 state design patter 으로 
구현한다. 

### Note 
* 만약 state 의 개수가 시스템 안에서 많아진다면 해당 시스템은 유지하기 힘들어진다. 

 
 