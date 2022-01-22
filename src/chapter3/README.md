# Chapter 3 람다 표현식

## 람다
람다 표현식은 메서드로 전달할 수 있는 익명 함수를 단순화한 것이다.
- **Anonymous** : 메서드와 달리 이름이 없으므로 **익명**이라 표현한다.
- **Function** : 메서드와 달리 특정 클래스에 종속되지 않으므로 **함수**라고 부른다.
- 람다 표현식을 메서드 인수로 전달하거나 변수로 저장할 수 있다.
- 코드가 간결하다.

## 람다 표현식
람다 표현식은 파라미터, 화살표, 바디로 이루어진다. 
```java
(Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
```
- ```파라미터``` : Comparator의 compare 메서드 파라미터(사과 두 개)
- ```화살표``` : 파라미터 리스트와 바디를 구분
- ```바디``` : 두 사과의 무게를 비교한다. 람다의 반환값

## 함수형 인터페이스
- 함수형 인터페이스는 오직 하나의 추상 메서드만(Single Abstract Method)을 정의하는 인터페이스이다.
- 함수형 인터페이스를 기대하는 곳에서만 람다를 사용할 수 있다.
- 람다를 이용해서 함수형 인터페이스의 추상 메서드를 즉석으로 제공할 수 있으며 람다는 함수형 인터페이스의 인스턴스로 취급된다. 
- cf) default method는 '구현'이 있으므로 추상이 아니다.

```java
//java.util.Comparator
public interface Comparator<T> {
    int compare(T o1, T o2);
}
```
```java
//java.lang.Runnable
public interface Runnable {
    void run();
}
```
```java
// java8부터 java.util.function 패키지로 여러 가지 함수형 인터페이스를 제공한다.
// java.util.function.Predicate
public interface predicate<T> {
    boolean test (T t);
}
```

### @FunctionalInterface
함수형 인터페이스임을 가리키는 어노테이션이다. 함수형 인터페이스의 인스턴스는 람다 표현식, 메서드 참조, 생성자 참조를 사용하여 만들 수 있다. 
컴파일러는 @FunctionalInterface 어노테이션 여부에 관계없이 함수형 인터페이스의 정의를 충족하는 모든 인터페이스를 함수형 인터페이스로 취급한다.
@FunctionalInterface로 인테페이스를 선언했지만 실제로 함수형 인터페이스가 아니면 컴파일러가 에러를 발생시킨다.

## 형식 추론
컴파일러는 람다가 사용된 context(예를 들면 람다가 전달될 메서드 파라미터나 람다가 할당되는 변수 등)를 이용해서 람다 표현식과 관련된 함수형 인터페이스를 추론한다.
함수형 인터페이스가 가지는 한 개의 추상 메서드의 파라미터와 반환 타입을 통해 람다 형식을 파악할 수 있다. 따라서 람다 표현식에서 파라미터 타입을 생략할 수 있다.

````java
Comparator<String> strComparator = (String str1, String str2) -> str1.compareTo(str2);
````

````java
Comparator<String> strComparator = (str1, str2) -> str1.compareTo(str2);
````

## 지역 변수 사용
- 람다는 인스턴스 변수와 정적 변수를 자유롭게 캡처(자신의 바디에서 참조할 수 있도록)할 수 있다.
- 그러나 지역 변수는 명시적으로 final로 선언되어 있거나 실질적으로 final처럼 취급되었어야 사용할 수 있다.
- 즉, 람다는 한 번만 할당한 지역 변수를 캡처할 수 있다.(Effectively Final)

```java
int port = 1337;
Runnable r = () -> System.out.println(port); // error
port = 31337;
```

## 메서드 참조
- 특정 메서드만 호출하는 람다의 축약형이다.
- 메서드명 앞에 구분자(::)를 붙이는 방식을 사용하며 실제로 메서드를 호출하는 것은 아니므로 괄호는 필요 없다.

```java 
String::substring
```

```java 
Apple::getWeight
```

## 생성자 참조
- 클래스명과 new 키워드를 이용해서 기존 생성자의 참조를 만들 수 있다.

```java 
Apple::new;
```

## References
https://docs.oracle.com/javase/8/docs/api/java/lang/FunctionalInterface.html