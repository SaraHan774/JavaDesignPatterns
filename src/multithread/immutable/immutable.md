## Immutable Pattern 
* 예시 : [ImmutableEx.java](./ImmutableEx.java)
* 참고할 점 
    * setter 처럼 메소드의 값을 변경하는 메소드가 없다. 
    * Person 클래스는 final 클래스로 선언되어 있어 서브 클래스를 만들 수 없다. 
    Immutable 클래스의 필수 조건은 아니지만 서브 클래스로 인해 필드 값이 변경되는 것을 막기 위한 조치이다. 
    * 필드들도 final 이 선언되어 있어 값이 한 번 대입되면 재 대입 될 수 없다. 
    
* Thread.currentThread() 는 현재의 스레드에 대응하는 java.lang.Thread 클래스의 인스턴스를 구하는 메소드이다. 

### 어떨 때 사용할까 
1. 인스턴스 생성 후 상태가 변하지 않을 때. 
    * Setter 메서드가 존재하지 않는다. 
2. 인스턴스가 공유되어 빈번하게 액세스 될 때 
    * synchronize 로 가드하지 않아도 된다 = 안전성과 생존성을 유지하면서 수행 능력을 높일 수 있다. 

### mutable 한 클래스와 immutable 한 클래스 비교하기 
어떠한 클래스가 불변성을 유지하고 싶지만 setter 가 있을 경우 setter 를 사용하는 상황과 사용하지 않는 
상황을 각각 다른 클래스로 작성한다. 가변 클래스와 불변 클래스 두개로 나누고, 불변 클래스에 immutable 패턴을 
적용하자. 
<br/>
**자바 클래스에는 String 과 StringBuffer 클래스가 위와 같은 불변 - 가변 의 짝을 이루고 있다.** 

### 불변성을 지키기 위해 `[재사용성]`
불변성은 매우 예민하다. 소스를 약간만 수정해도 불변성을 잃을 수 있다. Immutable 패턴임을 전제로 
synchronize 를 제거한 경우, 만약 불변성을 상실한다면 클래스의 안전성까지 잃게 된다. 
<br/>
=> 주석이나 API 문서에 불변성에 대해 명기해 둘 필요가 있다. 

### 표준 클래스 라이브러리에서 사용되는 Immutable 패턴 
* java.lang.String 
* java.math.BigInteger / BigDecimal
* java.util.regex.Pattern : Pattern 은 정규 표현의 패턴을 나타내는 클래스이며 패턴 매치를 
처리하는 중에도 값이 변하지 않는다. 
* java.lang.Integer / Short 등 Wrapper 클래스 : 인스턴스가 만들어진 후에는 Wrapping 하고 있는 값이 
변하지 않는다. 
* java.awt.Color : Color.red 나 Color.blue 등은 특정 색을 필요로 하는 경우에 공유한다. 

### final 정리 
1. final class
    * 서브 클래스를 만들 수 없다. **확장 불가능.** 
2. final method
    * 서브 클래스에 의해 override 될 수 없다. 
    * 서브 클래스의 메소드로 hide (은폐) 할 수 없다. 
3. final field  
    * 한 번 밖에 대입 할 수 없다.
4. final parameter & variable 
    * 변수에 딱 한 번 대입할 수 있다. 
    * 인수에도 한 번 밖에 대입할 수 없다. 메소드가 호출되었을 때 이미 값이 대입되어 있기 때문이다.  

* final 한 인스턴스 필드에 대입하는 방법 
```
(1)
final int value = 123; 

(2)
final int value; 
MyConstructor(){
    this.value = 123; 
} 
```

* final 한 클래스 필드에 대입하는 방법 
```
(1)
static final int value = 123; 

(2)
static final int value; 
static{ //static block 을 만들어서 대입한다. 
    value = 123; 
}
```

### 요약 
Immutable 패턴에서 클래스를 보호하는 것을 synchronized 가 아니라 immutability 이다. 
클래스의 immutability 를 지키는 것은 설계자의 몫이다. 
<br/><br/>

## 연습문제 

### 문제 2-1 (기초 지식 확인)
1. O / String 클래스는 불변 
2. X / StringBuffer 클래스는 가변 
3. O / final 필드에는 값이 한번밖에 대입되지 않는다. 
4. O / private 필드에 setter 가 있는 경우 서브 클래스에서 직접 액세스 가능 
5. X / synchronized 키워드를 붙이면 안전성은 확보되나 수행 능력이 떨어진다. 많이 붙인다고 좋은게 아니다. 

### 문제 2-2 (immutability 의 확인)
* String.replace() 메서드를 보고, String 클래스는 mutable 하다는 주장에 대한 반박 

```
package multithread.immutable;

public class StringEx {

    public static void main(String[] args) {

        String string = "BAT";
        System.out.println(string.replace('B', 'C')); //CAT 출력
        System.out.println(string); //BAT 출력

        //새로운 스트링에 대입해야 한다.
        String newString = string.replace('B', 'C');
        //각자 스트링은 다른 해시코드 값을 출력한다. 서로 다른 객체이다. 
        System.out.println(string.hashCode());
        // 65525
        System.out.println(newString.hashCode());
        //66486
    }
}
```

### 문제 2-3 (수행 능력의 비교)
* synchronized 대신 Immutable 을 사용하면 수행 능력이 얼마나 향상되는지 알아보는 프로그램 
* [PerformanceCompareEx.java](./PerformanceCompareEx.java)
* Output : 
```
Not Synchronized : BEGIN 
Not Synchronized : END 
Elapsed Time = 371ms
Synchronized : BEGIN 
Synchronized : END 
Elapsed Time = 17397ms
```

### 문제 2-4 (setter 메소드가 없는데도 immutable 이 되지 않는다)
* [NoSetterEx.java](./NoSetterEx.java)
```
    public static void main(String[] args) {
        //User Info 클래스는 왜 Immutable 이 아닌가?
        UserInfo userInfo = new UserInfo("Sara", "Huam-dong");
        System.out.println(userInfo);

        System.out.println(userInfo.getInfo().replace(0, 2, "CHANGED"));
        //User Info 의 필드가 StringBuffer 로 선언되어 있기 때문에 여전히 수정 될 수 있으므로. 
        System.out.println(userInfo);
    }
```
* Output : 
```
UserInfo{info=info name = Sara address = Huam-dong}
CHANGEDfo name = Sara address = Huam-dong
UserInfo{info=CHANGEDfo name = Sara address = Huam-dong}
```

### 문제 2-5 (immutable 인지 아닌지의 판단)
* [Line.java](./Line.java)
* Line 클래스는 immutable 인가? : 정답은 아니다. 
* Point 클래스를 통해서 x, y 값들을 변경할 수 있으므로 immutable 이 아니다. 

* [TestCode](./LineTest.java)
```
public class LineTest {

    public static void main(String[] args) {

        Point sPoint = new Point(3, 4);
        Point ePoint = new Point(30, 40);

        Line line = new Line(sPoint, ePoint);
        System.out.println(line);
        sPoint.x = 123;
        sPoint.y = 234;
        ePoint.x = 300;
        ePoint.y = 4000;
        System.out.println(line);
    }
}
```
* Output : 
```
Line{startPoint=Point{x=3, y=4}, endPoint=Point{x=30, y=40}}
Line{startPoint=Point{x=123, y=234}, endPoint=Point{x=300, y=4000}}
```

### 문제 2-6 (mutable 한 인스턴스에서 immutable 한 인스턴스를 만든다)
```
public ImmutablePerson(MutablePerson person){
        //여러개의 스레드를 만들어서 각 스레드에서 MutablePerson 객체를 통해 Immutable Person 객체를 
        //만들기를 시도하는 경우 person 인자값에 다른 스레드가 접근할 수 있게 되어서 getter 의 
        //결과가 안전하지 않게 된다. 
        this.name = person.getName();
        this.address = person.getAddress();
    }

```
