### Java Design Patterns 
From Vaskaran Sarcar's book ***Java Design Patterns : A Tour with 
23 Gang of Design Patterns in Java***

* [Flyweight Pattern](./src/flyweight)
    * 핵심 : 생성할 객체들이 비슷한 경우 약간씩의 Customizing 만 해주면 된다. 
    이 때 Factory 를 통해 객체를 생성하고, 외부에서 객체의 다른 속성들을 customizing 해준다.
    
* [Adapter Pattern](./src/adapter)
    * 핵심 : 언어가 다른 두 사람이 이야기 할 때 통역사가 필요하듯, 자바에서 서로 다른 
    두 인터페이스가 소통할 경우 adapter 가 필요하다. 
    * Class Adapter : 인터페이스를 구현한 클래스를 상속받아서 `super.Imethod()` 의 기능을 
    수정하는 방식으로 작동한다. 
    * Object Adapter : 인터페이스를 구현한 클래스의 객체를 생성자로 받아서 `this.IObj.Imethod()`의 
    기능을 수정하는 방식으로 작동한다. 