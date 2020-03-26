### Java Design Patterns 
From Vaskaran Sarcar's book ***Java Design Patterns : A Tour with 
23 Gang of Design Patterns in Java***

* [Flyweight Pattern](./src/flyweight)
    * [Flyweight Pattern 설명](./src/flyweight/flyweight-pattern.md)
    * 핵심 : 생성할 객체들이 비슷한 경우 약간씩의 Customizing 만 해주면 된다. 
    이 때 Factory 를 통해 객체를 생성하고, 외부에서 객체의 다른 속성들을 customizing 해준다.
    
* [Adapter Pattern](./src/adapter)
    * [Adapter Pattern 설명](./src/adapter/adapter-pattern.md)
    * 핵심 : 언어가 다른 두 사람이 이야기 할 때 통역사가 필요하듯, 자바에서 서로 다른 
    두 인터페이스가 소통할 경우 adapter 가 필요하다. 
    * Class Adapter : 인터페이스를 구현한 클래스를 상속받아서 `super.Imethod()` 의 기능을 
    수정하는 방식으로 작동한다. 
    * Object Adapter : 인터페이스를 구현한 클래스의 객체를 생성자로 받아서 `this.IObj.Imethod()`의 
    기능을 수정하는 방식으로 작동한다. 
    
* [State Pattern](./src/state)
    * [State Pattern 설명](./src/state/state-pattern.md)
    * 핵심 : 네트워크의 연결 상태처럼 state 가 변화하는 경우 이 패턴을 사용한다. 
    * TV가 on off 된 상태를 표현하는 구현이 예제임. 
    * 주의 : state 가 많아질수록 system 을 유지하기 힘들어 질 수 있음. 
    
* [Builder Pattern](./src/builder)
    * [Builder Pattern 설명](./src/builder/builder-pattern.md)
    * 핵심 : 객체의 조립 과정과 최종적인 객체의 생성을 분리시킬 때 사용한다. 
    * 포괄적으로 "차량" 제품을 만든다고 할 때, 어떤 차량을 만들지는 클라이언트가 정한다. 
    예를들어 자동차를 만들 수 도 있고, 오토바이를 만들수 도 있다. 자동차든 오토바이든 이들을 
    조립할 때 공통적으로 들어가는 요소들이 있다. 차량의 몸체, 바퀴, 헤드라이트 등은 개수와 형태만
    다를 뿐 들어가긴 들어간다. 따라서 포괄적으로 "차량의 생산"을 담당하는 인터페이스를 만들고 
    이들을 Building 하는 과정은 각기 다른 CarBuilder, MotorCycleBuilder 등 에서 담당한다. 
    * 단점 : 코드의 중복이 많다. Mutable Object 를 원할 경우 이 패턴을 사용해서는 안된다. 객체를 
    생성한 후 수정하는 것이 불가능 - 혹은 불편 - 하기 때문이다. 