# chapter 5 스트림 활용

## 1. 필터링
### filter()
```java
List<Dish> lowCaloricDishes =
        menu.stream()
            .filter(dish -> dish.getCalories() < 400)
            .collect(toList());
```
- 인수로 받은 프레디케이트와 일치하는 모든 요소를 포함하는 스트림을 반환

### distinct()
```java
List<Integer> distinctNumbers
                = numbers.stream()
                        .distinct()
                        .collect(toList());
```
- unique 요소로 이루어진 스트림을 반환. 즉, 중복 제거

## 슬라이싱
|메서드|반환 형식|인수|설명|
|---|---|---|---|
|```takeWhile```|default Stream\<T>|Predicate\<T>|정렬된 스트림이면 프레디케이트와 일치하는 마지막 요소까지 구성된 스트림을 반환|
|```dropWhile```|default Stream\<T>|Predicate\<T>|정렬된 스트림이면 프레디케이트가 처음으로 거짓인 요소까지 버리고 스트림을 반환|

## 매핑
- 데이터 **변환**

|메서드|반환 형식|인수|설명|
|---|---|---|---|
|```map```|\<R> Stream\<R>|Function<T, R>|매핑 함수를 적용한 결과로 구성된 스트림을 반환|
|```flatMap```|\<R> Stream\<R>|Function<T, Stream<R>>|컬렉션 스트림의 모든 요소를 매핑 함수에 적용하여 모든 컬렉션이 결합된 스트림을 반환|

### flatMap
```java
Stream<String[]> -> flatMap -> Stream<String>
Stream<Set<String>> -> flatMap -> Stream<String>
Stream<List<String>> -> flatMap -> Stream<String>
```

```java
List<String> words = Arrays.asList("Hello", "World");

// map
List<String[]> mapList =
        words.stream()
                .map(word->word.split(""))
                .distinct()
                .collect(toList());

// flatMap
List<String> flatMapList =
        words.stream()
                .map(word->word.split(""))
                // 생성된 스트림을 하나의 스트림으로 평면화
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
```

## 매칭과 검색
### anyMatch() , allMatch(), noneMatch()
```java
if (menu.stream().anyMatch(Dish::isVegetarian)) {
    System.out.println("채식 요리는 하나 이상 있다.");
}

if (menu.stream().allMatch(Dish::isVegetarian)) {
    System.out.println("모든 요리는 채식이다.");
}

if (menu.stream().noneMatch(Dish::isVegetarian)) {
    System.out.println("모든 요리는 채식이 아니다");
}
```
- 프레디케이트와 주어진 스트림의 요소의 일치 여부를 확인한다.
- boolean을 반환하는 최종 연산
- 쇼트서킷 기법을 활용한다.

> **쇼트서킷 기법**  
> java의 &&, ||와 같이 모든 스트림의 요소를 처리하지 않고도 결과를 반환할 수 있다.   

### findAny(), findFirst()

```java
Optional<Dish> dish = 
        menu.stream()
            .filter(Dish::isVegetarian)
            .findAny();

List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
Optional<Integer> firstSquareDivisibleByThree =
        numbers.stream()
                .map(n -> n * n)
                .filter(n -> n % 3 == 0)
                .findFirst();
```
- findAny() : 현재 스트림에서 임의의 요소를 반환한다.
- findFirst() : 현재 스트림에서 첫 번째 요소를 반환한다. 
- 단, 아무 요소도 반환하지 않을 수 있으므로 Optional을 반환한다.

> **Optional\<T>**  
> 값의 존재나 부재 여부를 표현하는 컨테이너 클래스
  
  

## 리듀싱
### reduce()
```java
// 최종 합계 구하기
int sum = numbers.stream().reduce(0, Integer::sum);

// 스트림의 요리 개수 구하기
int count = menu.stream()
                .map(d -> 1)
                .reduce(0, (a, b) -> a + b);
```

- 모든 스트림 요소를 처리해서 하나의 값으로 도출한다.
- reduce(T identity, BinaryOperator \<T> accumulator)는 두 개의 인수를 갖는다. 
- 인수는 초기값 T와 두 요소를 조합해서 새로운 값을 만드는 BinaryOperator 이다.
- 초기값이 없도록 오버로드된 reduce는 Optional 객체를 반환한다.  
  

## references
https://wjjeong.tistory.com/42  

https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/stream/Stream.html