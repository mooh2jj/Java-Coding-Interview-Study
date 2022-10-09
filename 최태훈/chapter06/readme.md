# 6. 객체지향 프로그래밍 

## 6.1 개념
--- 
### 6.1.1 객체

### 6.1.2 클래스 

### 6.1.3 추상화

### 6.1.4 캡슐화

### 6.1.5 상속

### 6.1.6 다형성

### 6.1.7 연관

### 6.1.8 집약

### 6.1.9 구성

***

## 6.2 SOLID 원칙 이해 
---
### 6.2.1 S: 단일 책임 원칙  
> S: 단일 책임 원칙 (single responsibility principle, SRP)
>> - 단일 책임 원칙은 하나의 객체가 하나의 책임만 가져야 한다는 의미 이다.  
>> - 단일 책임 원칙은 클래스를 단 한 가지 목표만 가지고 작성해야 한다는 것을 의미한다.
>> - 단일 책임 원칙은 애플리케이션 모듈 전반에서 높은 유지보수성과 가시성 제어기능을 유지하는 원칙이다.  
>> - 단일 책임 원칙을 적용하면 책임 영역이 분명해지기 떄문에 한 책임의 변경에서 다른 책임의 변경으로의 연쇄작용에서 자유로울 수 있다. 
```java
public class RectangleAreaCalculator {
  // 한 클래스 에서 두가지 목표를 가지고 있다 
  private static final double INCH_TERM = 0.0254d;

  private final int width;
  private final int height; 

  public RectangleAreaCalculator(int width, int height){
    this.width = width;
    this.height = height; 
  }

  public int area() {
    return width * height; 
  }
  
  public double metersToInches(int area){
    return are / INCH_TERM;
  }
}
```

```java
public class RectangleAreaCalculator {
  private static final double INCH_TERM = 0.0254d;

  private final int width;
  private final int height; 

  public RectangleAreaCalculator(int width, int height){
    this.width = width;
    this.height = height; 
  }

  public int area() {
    return width * height; 
  }
}

public class AreaConvertor{
  private static final double INCH_TERM = 0.0254d;
  private static final double FEET_TERM = 0.3048d;
    
    public double metersToInches(int area){
      return area / INCH_TERM;
    }

    public double metersToFeet(int area){
      return area / FEET_TERM;
    }
}
```
--- 
### 6.2.2 O: 개방 폐쇄 원칙  
> O: 개방 폐쇄 원칙 (Open-Closed Principle, OCP)
>> - 개방 폐쇄 원칙은 소프트웨어 컴포넌트는 확장에 관해 열려 있어야 하고, 수정에 관해서는 닫혀있어야 한다 라는 말이다.
>> - 개방 폐쇄 원칙은 다른 개발자가 작업을 수행하기 위해 반드시 수정해야 하는 제약 사항을 클래스에 포함해서는 안 된다는 사실을 의미한다. 다른 개발자가 클래스를 확장하기만 하면 원하는 작업을 할 수 있도록 해야 한다.    
>> - 개방 폐쇄 원칙은 다양하고 직관적이며 유해하지 않은 방식으로 소프트웨어 확장성을 유지하는 원칙이다. 

```java
  public interface shape{ }

  public class Rectangle implements Shape{
    private final int width;
    private final int height; 
  }

  public class Circle implements Shape{
    private final int radius;
  }

  public class AreaCalculator {
    private final List<Shape> shapes;

    public AreaCalculator(List<Shape> shapes){
      this.shapes = shapes;
    }

    public double sum(){
      int sum = 0; 
      for(Shape shape: shapes){
        if(shape.getClass().equals(Circles.class)){
          // ~~
        }else if(shape.getClass().equals(Rectangle.class)){
          // ~~
        }
      }
    }
  }
```
```java 
 public interface Shape{
  public double area();
 
  public class Rectangle implements Shape{
    private final int width;
    private final int height; 

    @Override
    public double area(){
      return width * height; 
    }
  }

  public class Circle implements Shape{
    private final int radius;

    @Override
    public double area(){
      return radius * radius * Math.PI;
    }
  }

  public class AreaCalculator {
    private final List<Shape> shapes;

    public AreaCalculator(List<Shape> shapes){
      this.shapes = shapes;
    }

    public double sum(){
      int sum = 0; 
      for(Shape shape: shapes){
        sum += shape.area();
      }
      return sum; 
    }
  }

```
---
### 6.2.3 L: 라스코프 치환 원칙  
> L: 라스코프 치환 원칙 (Liskov substitution principle, LSP)
>> - 라스코프 치환 원칙은 파생 타입은 반드시 기본타입을 완벽하게 대체 할수 있어야 한다.
>> - 라스코프 치환 원칙은 서브클래스의 객체는 슈퍼클래스의 객체와 반드시 같은 방식으로 동작해야 한다.    
>> - 라스코프 치환 원칙은 타입 변환 후에 뒤따라오는 런타임 타입 식별(RTTI)에 유용한 원칙이다.
>>> #### RTTI : runtime type identification 
>>> - <a>https://onevely.tistory.com/19</a>

```java
  public abstract class Member{ 
    private final String name;

    public Member(String name){
      this.name = name; 
    }

    public abstract void joinTournament();
    public abstract void organizeTournament();
  }

  public class PremuimMember extends Member{
    public PremiumMember(String name){
      super(name);
    }

    @Override
    public void joinTournament(){
      sout("Premuim members joins tournament");
    }

    @Override
    public void organizeTournament(){
      sout("Premuim members organize tournament");
    }
  }

  public class FreeMember extends Member{
    public FreeMember(String name){
      super(name);
    }

    @Override
    public void joinTournament(){
      sout("Classic members joins tournament");
    }

    @Override
    public void organizeTournament(){
      sout("A Free members cannot organize tournament");
    }
  }

  List<Member> memebers = List.of(
    new PremiumMember("jack hores");
    new FreeMember("Martin vilop");
  )
  for(Member member: members){
    member.organizeTournament();
  }
```
```java 
  public interface TournamentOrganizer{ 
    public void organizeTournament();
  }
  public interface TournamentJoiner{
    public void JoinTournament();
  }
  public abstract class Member implements TournamentJoiner , TournamentOrganizer{
    private final String name;

    public Member(String name){
      this.name = name;
    }
  }

  public class FreeMember implements TournamentJoiner{
    private final String name;
    
    public FreeMember(String name){
      this.name = name;
    }

    @Override
    public void joinTournament(){
      sout("Classic members joins tournament");
    }
  }

  List<Member> memebers = List.of(
    new PremiumMember("jack hores");
    new FreeMember("Martin vilop");
  )
  
  for(TournamentJoiner member: members){
    member.joinTournament();
  }

  List<TournamentOrganizer> memebers = List.of(
    new PremiumMember("jack hores");
    new VipMember("Martin vilop");
  )
  
  for(TournamentOrganizer member: members){
    member.organizeTournament();
  }

```

--- 
### 6.2.4 I: 인터페이스 분리 원칙  
> I: 인터페이스 분리 원칙 (Interface-Segregation Principle ,ISP)
>> - 인터페이스 분리 원칙은 클라이언트가 사용 하지 않을 불필요한 메소드를 강제로 구현하게 해서는 안된다.
>> - 인터페이스 분리원칙은 클라이언트가 사용하지 않을 메서드를 강제로 구현하는 일이 없을때까지 하나의 인터페이스를 2개이상의 인터페이스로 분할한다.    

```java
public interface Connection {
  public void socket();
  public void http();
  public void connect();
}

public class WwwPingConnection implements Connection {
  private final String www;

  public WwwPingConnection(String www){
    this.www = www;
  }
  @Override
  public void http(){
    sout("Setup an HTTP connection to " + www);
  }
  @Override 
  public void connect(){
    sout("Connect to " + www);
  }
  @Override 
  public void socket() {}
}
```
```java
public interface Connection{
  public void connect();
}
public interface HttpConnection extends Connection{
  public void http();
}
public interface SockectConnection extends Connection{
  public void socket();
}
public class WwwPingConnection implements HttpConnection{
  private final String www;

  public WwwPingConnection(String www){
    this.www = www;
  }

  @Override
  public void http(){
    sout("setup an http connection to " + www);
  }
  @Override
  public void connect(){
    sout("connect to "+ www);
  }
}
```


--- 
### 6.2.5 D: 의존관계 역전 원칙  
> D: 의존관계 역전 원칙 (Dependency inversion principle ,DIP)
>> - 의존 관계 역전의 원칙은 구체화가 아닌 추상화에 의존해야 한다.
>> - 의존 관계 역전의 원칙은 다른 구상 모듈에 의존하는 구상 모듈 대신, 구상 모듈을 결합하기 위한 추상 계층을 사용한다는 것을 의미한다. 
>> - 의존 관계 역전의 원칙은 구상 모듈을 분리한다.     

```java
public class PostgreSQLJdbcUrl {
  private final String dbName;

  public PostgreSQLJdbcUrl(String dbName){
    this.dbName = dbName;
  }

  public String get(){
    return "jdbc:postgresql://..." + this.dbName;
  }
}

public class connectToDatabase{
  public void connect(PostgreSQLJdbcUrl postgresql){
    sout("connection to " + postgresql.get());
  }
}
```
```java
public interface JdbcUrl{
  public String get();
}
public class PostgreSQLJdbcUrl implements JdbcUrl{
  private final String dbName;

  public PostgreSQLJdbcUrl(String dbName){
    this.dbName = dbName;
  }

  @Override
  public String get(){
    return "jdbc:postgresql://..." + this.get();
  }
}

public class ConnectToDatabase {
  public void connedct(JdbcUrl jdbcUrl){
    sout("connectiong TO " + jdbcUrl.get());
  }
}
```
*** 
## 6.3 객체지향 프로그래밍 , SOLID, GOF 디자인 패턴과 관련한 유명 질문  
--- 
### 6.3.1 객체지향 프로그래밍에서 메서드 오버라이딩이란 무엇인가? 
- 메서드 오버라이딩은 객체지향 프로그래밍 기법으로, 개발자가 같은 이름과 시그니처를 가지면서 다르게 동작하게 2개의 메서드를 작성하는 것을 의미합니다. 이때 메서드는 static, private, final 메서드가 아니여야 합니다.   
--- 
### 6.3.2 객체지향 프로그래밍 에서 메서드 오버로딩이란 무엇인가 
- 메서드 오버로딩은 객체지향 프로그래밍 기법으로, 개발자가 같은이름이지만 다른 시그니처와 다른 기능을 하도록 2개의 메서드를 작성하는 것을 의미합니다. 이때 두 메서드는 모두 static 이거나 static 이 아니여야 합니다. 
---
### 6.3.3 자바에서 공변 메서드 오버라이딩이란 무엇인가?
- 공변 메서드 오버라이딩을 사용하여 오버라이딩 메서드는 실제 반환 타입의 하위타입을 반환할 수 있습니다.
> 자바의 clone 메서드는 object를 반환하는데, 이 메소드를 활영하여 복제된 객체를 반환 받고자 할때 object를 받아서 object의 서브 클래스로 명시적으로 타입변환하여 원하는 실제 결과를 만들어야합니다. 그러나 자바5 부터는 공변 메서드 오버라이딩이 지원되기 떄문에 clone 메서드를 override 하여 object대신 필요한 서브클래스를 직접 반환 할수 있다.  
--- 
### 6.3.4 오버라이딩 및 오버로딩 메소드에서 예외를 다룰 때 주요 제한 사항은 무엇인가? 
- 확인되지 않은 예외의 경우 오버라이딩 메서드에서 사용하는 데 아무런 제한 사항이 없습니다. , 확인된 예외의 경우 오버라이딩 메서드는 오버라이드 된 메서드의 확인된 예외 혹은 그 서브클래스 예외만 발생시킬수 있습니다. 
--- 
### 6.3.5 슈퍼클래스의 오버라으된 메서드를 서브클래스의 오버라이딩 메서드에서 어떻게 호출할수 있는가? 
- 자바의 super 키워드를 이용하여 슈퍼클래스의 오버라이드된 메서드를 서브클래스의 오버라이딩 메서드에서 활용할수 있습니다. 
--- 
### 6.3.6 메인 메서드를 오버라이드 또는 오버로드 할수 있는가? 
- main 메소드가 static 메서드 이기때문에 오버로드 할수는 있지만, 오버라이드는 할수 없습니다. 
--- 
### 6.3.7 자바에서 static이 아닌 메서드를 static 메서드로 오버라이드 할수 있는가? 
- static 이 아닌 메서드는 static 메서드로 오버라이드 할수 없으며, 그반대도 불가능 하다. 
--- 
### 6.3.8 자바 인터페이스 안에 abstract가 아닌 메서드를 포함할 수 있는가? 
- 자바 8 전까지는 자바 인터페이스 안에 abstract가 아닌 메서드를 사용 할수 없었지만, 자바 8 부터는 구현된 메서드를 default 또는 static 키워드를 사용하여 이 작업을 수행할 수 있습니다. default 키워드는 기본 , 방어 , 확장 메서드 라고 불리는 메서드를 인터페이스에 포함하는 키워드로서 자바8에 도입되었다. 
---
### 6.3.9 default 메서드를 가지는 인터페이스와 추상 클래스의 주요 차이점은 무엇인가? 
- 자바 8 인터페이스는 생성자를 지원하지 않지만 추상 클래스는 생성자를 가질수 있다. 
---
### 6.3.10 추상 클래스와 인터페이스의 주요 차이점은 무엇인가?
- 자바 8 이전까지 추상 클래스는 abstract 가 아닌 메서드를 포함할수 있지만, 인터페이스는 그런 메서드를 포함할수 없다. 
자바 8 부터는 추상 클래스는 생성자와 상태를 가질 수 있는 반면, 인터페이스는 이둘 중 하나를 가질수 없다. 
--- 
### 6.3.11 abstract 메서드가 없는 추상 클래스를 만들수 있는가? 
- 가능하다. abstract 키워드를 크래스에 추가하면 추상 클래스가 된다 추상클래스는 인스턴스화 할수 없지만, 생성자와 abstract ㄱ 아닌 메서드만 있어도 괜찮다. 
--- 
### 6.3.12 추상이면서 동시에 final 클래스를 만들수 있는가? 
- final 클래스는 서브 클래스화 되거나 상속 할수 없다. 추상 클래스는 확장을 통해서만 사용할수 있습니다. 그러므로 final 과 추상은 반대되는 개념이다. 이것은 두가지를 동시에 같은 클래스에 적용 할수 없다는 사실을 의미 한다. 
---
### 6.3.13 다형성 오바리딩 오버로딩의 차이점은 무엇인가? 
- 오버로딩 기법은 컴파일 타임 다형성으로 알려져있는 알려져있는 반면에 오버라디익 기법은 런타임 다형성으로 알려져 있다, 오버로딩은 정적 혹은 이른 바이딩을 사용하는 반면에 오버라이딩은 동적 혹은 늦은 바인딩을 사용합니다. 
---
### 6.3.14 바인딩 작업이란 무엇인가? 
- 바인딩 작업은 코드라인에서 참조한 결과로 호출할 메서드 또는 변수를 결정합니다. 즉, 메서드 호출을 메서드 본문에 연결하는 과정을 바인딩 작업이라고 합니다. 일부 참조는 컴파일 시간에 바인딩 되는 반면에 다른참조는 런타임에 바인딩 됩니다. 
- 런타임에 바인딩되는 참조는 객체의 타입에 따라 다릅니다. 컴파일 시간에 바인딩되는 작업이라고하며 런타임에 바인딩 되는 참조는 동적 바인딩 작업이라고 합니다. 
---
### 6.3.15 정적 바인딩과 동적 바인디의 주요차이점은 무엇인가? 
- 정적 바인딩은 컴파일 시간에 발생하며, 동적 바인딩은 런타임에 발생한다. 두번째 차이점은 priate, static, final 메서드 혹은 변수는 정적바인딩을 사용하는 반면에 가상 메서드는 객체의 타입에 따라 런타임에 바인딩 된다는 사실입니다. 
- 즉 정적바인딩은 type 정보를 통해 결정되며 동적 바인딩은 object에 의해 결정된다 다시말해 정적바인딩에 의존하는 메서드는 객체와 관련이 없으며, type에 의해 호출되고 동적 바인딩에 의존하는 메서드는 object와 관련이 있습니다. 정적 바인딩에 의존하는 메서드의 실행은 동적바인딩에 의존하는 메소드 보다 조금 더 빠르다. 
- 정적과 동적 바인딩은 다형성에서도 사용된다. 정적 바인딩은 컴파일 타임 다형성(오버로딩 메서드)에서 쓰이고 동적 바인딩은 런타임 다형성(오버라이딩 메서드) 에서 쓰입니다. 정적 바인딩은 컴파일 시간 성능에 오버헤드를 더하고 동적 바인딩은 런타임 성능에 오버헤드를 더하기 떄문에 정적 바인딩이 더 선호 됩니다. 
---
### 6.3.16 자바에서 메서드 하이딩이란 무엇인가 ? 
- 메서드 하이딩은 정적 메서드에 한정되며 슈퍼클래스와 서브클래스에서 같은 시그니처와 같은이름을 가진 2개의 static 메서드를 선언한다면 두 메서드는 서로를 숨긴다. 
- 슈퍼클래스에서 메서드를 호출하면 슈퍼클래스의 static 메서드가 호출되며 , 같은메서드를 서브클래스에서 호출하면 서브클래스의 static 메서드가 호출된다. 
--- 
### 6.3.17 자바에서 가상 메서드를 작성 할수 있는가? 
- static 이아닌 메서드는 기본적으로 가상 메서드 이며 private 또는 final 키워드로 표시하여 가상이 아닌 메서드를 작성할수 있으며 다형동작을 위해 상속 될수 있는 메서드는 가상 메서드 이다 . 
--- 
### 6.3.18 추상화와 다형성의 차이점은 무엇인가 
- 추상화는 인터페이스와 추상클래스를 통해 구현되는 반면 다형성과 오버라이딩과 오버로딩 기법으로 구현된다. 
--- 
### 6.3.19 다형성을 구현하는 방법으로 오버로딩을 고려 할수 있는가. 
- 논쟁의 여지가 있지만, 오버라이딩과 오버로딩의 전제를 이해하는 한두가지 방법이 다형성 동작을 유지하는 방법이다. 
--- 
### 6.3.20 데커레이터 패턴에 적합한 객체지향 프로그래밍 개념은 무엇인가 
- 더커레이터 디자인 패턴에 적합한 객체지향 프로그래밍 개념은 구성이다. 
--- 
### 6.3.21 싱글턴 패턴은 언제 사용해야하는가 
- 싱글턴 디자인 패턴은 클래스의 애플리케이션 레벨 인스턴스가 하나만 필요할때 사용하면 매우 적합하다. 
--- 
### 6.3.22 전략패턴과 상태 패턴의 차이점은 무엇인가? 
- 상태 디자인 패턴은 상태에 따라 특정 작업을 수행하도록 설계되었다 이패턴은 클래스를 변경하지 않는 환경에서 서로 다른 상태의 특정 동작을 나타낸다, 반면 전략 패턴은 코드를 수정하지 않고 여러 알고리즘을 전환하여 사용하기 위한 패턴이다. 
--- 
### 6.3.23 프록시 패턴과 데커레이터 패턴의 차이점은 무엇인가? 
- 프록시 패턴은 무언가에 대한 접근 제어 게이트웨이를 제공하는 데 유용하다 일반적으로 이패턴은 실제 객체를 대신할 대리객체를 생성하며, 실제객체에 대한 각 요청은 대리 객체를 통과해야하며 대리 객체는 이를 실제 객체로 전달할 방법과 시점을 결정한다, 데커레이터 패턴은 객체를 생성하지 않으며 런타임에 기존 객체를 새로운 기능으로 장식할 뿐이다. 
--- 
### 6.3.24 퍼사드 패턴과 데커레이터 패텅늬 차이점은 무엇인가? 
- 데커레이터 디자인 패턴은 객체에 새로운 기능을 추가하거나 객체를 장식한다는 의미이지만, 퍼사드 디자인 패턴은 객체에 새로운 기능을 전혀 추가하지 않는다. 
--- 
### 6.3.25 템플릿 메서드 패턴과 전략패턴의 차이점은 무엇인가 
- 전략패턴은 요구사항을 기반으로 서로 다른 전략또는 알고리즘 중 사용할 알고리즘을 런타임에 결정하지만, 텀플릿 메서드 패턴은 고정된 뼈대 즉 사전에 정의된 일련의 단계에 따라 구현된 알고리즘 대로 동작한다는 사실이다. 
--- 
### 6.3.26 빌더 패턴과 팩토리 패턴의 주요차이점은 무엇인가 
- 팩토리 패턴은 단일 메서드 호출로 객체를 생성한다. 이때 필요한 모든 매개 변수를 전달해야하며 팩토리는 일반적으로 생성자를 호출하여 객체를 반환 할것이다, 반면 빌더 패턴은 매개변수의 조합을 형성 할수있는 setter의 연쇄작용을 통하여 복잡한 객체를 구성할 수 있도록 설계 되었다. 
--- 
### 6.3.27 어댑터 패턴과 브리지 패턴의 차이점은 무엇인가 
- 어댑터 패턴은 타사코드와 같이 수정할 수 없는 기존 코드와 새로운 시스템 또는 인터페이스 사이의 호환성을 제공하는 반면, 브리지 패턴은 사전에 구현되며 엄청난 수의 클래스 작성을 피하고자 구현에서 추상화를 분리한다. 