### Flyweight Pattern 개념 
flyweight 는 하나의 객체로서 이를 통해 데이터를 공유함으로써 메모리 사용을 최소화 하고자 한다. 

* intrinsic state 와 extrinsic state
    * intrinsic state : flyweight 안에 store 되어서 공유될 수  있다. 
    * extrinsic state : flyweight 의 context 에 의존적이며 공유될 수 없다.
    * 클라이언트 객체들은 extrinsic state 를 flyweight 에게 전달해 주어야 한다. 
    
### Real-Life Example 
* 비슷한 객체들을 저장하고 싶지 않는 경우 이 패턴이 사용된다. (?) 
* 예를들어, 두 사람이 아파트를 찾는데 한 아파트가 매우 괜찮지만 렌트비가 높아 두 사람이 같이 살며 렌트비를 나누어 내는 경우. 

### Computer World Example 
* 워드 프로세서에서 문자들의 graphical representation 이 한 예가 될 수 있다. 
* 컴퓨터 게임 안에서 많은 participant 들이 있는데, 이들의 모양새는 비슷하지만 서로 약간씩만 다른 경우 (색깔, 의상, 무기 등)

### 예제 개선 
* 현재는 Singleton 과 Flyweight 의 차이점이 보이지 않는다. 
* 차이점을 명확히 하기 위해서 로봇을 king 과 queen 타입을 지정할 수 있도록 하고, 각 타입은 Green, Red 의 색을 가질 수 있도록 한다. 
* Factory 에서 이미 king 또는 queen 타입이 있는지 확인하고, 만약 있다면 새로 생성하지 않도록 로직을 수정한다. 
* 그 후에 기본 구조를 factory 에서 가져온 후 color 를 입히도록 한다. 
* 여기서 color 는 extrinsic data 이고, category of robot 은 (king or queen) intrinsic data 임을 확인하라. 
    * color 는 category of robot  - 즉 factory 의 context 에 의존적이며, category 는 flyweight 안에 저장되어 공유될 수 있으므로.
    
### Note 
* storage 사용의 최소화가 여기서 가장 중요한 concern 이다. 만약 공유할 수 있는 flyweight 이 더 많다면, 메모리를 더욱 절약할 수 있다. 
* 만약 extrinsic state 를 storing 하는 대신(객체 생성 시 내부에서 찍어내는 대신) compute 할 수 있다면 우리는 메모리를 더욱 많이 절약할 수 있다. 
* 트리 구조에서 리프 노드들을 공유하기 위해 우리는 이 패턴을 composite pattern 과 함께 사용하기도 한다. 
* flyweight interface 는 공유를 가능하게 할 수도, 못할수도 있다. 몇몇 경우, 공유되지 않은 flyweight 객체들이 있을 수 있다. 공유되지 않고 
concrete 한 flyweight object 를 자식으로서 갖게 하는 것이다. (?) 
* **In simple terms : Intrinsic data make the instance unique, whereas extrinsic data are passed as arguments.** 