# chapter 5 스트림 활용

## 필터링

|메서드|반환 형식|인수|설명|
|---|---|---|---|
|```filter```|Stream<T>|Predicate<T>|프레디케이트와 일치하는 모든 요소를 포함하는 스트림을 반환|
|```dsitinct```|Stream<T>||unique 요소로 이루어진 스트림을 반환|

## 슬라이싱
|메서드|반환 형식|인수|설명|
|---|---|---|---|
|```takeWhile```|default Stream<T>|Predicate<T>|정렬된 스트림이면 프레디케이트와 일치하는 마지막 요소까지 구성된 스트림을 반환|
|```dropWhile```|default Stream<T>|Predicate<T>|정렬된 스트림이면 프레디케이트가 처음으로 거짓인 요소까지 버리고 스트림을 반환|

## 매핑
- 데이터 **변환**

|메서드|반환 형식|인수|설명|
|---|---|---|---|
|```map```|<R> Stream<R>|Function<T, R>|매핑 함수를 적용한 결과로 구성된 스트림을 반환|
|```flatMap```|<R> Stream<R>|Function<T, Stream<R>>|컬렉션 스트림의 모든 요소를 매핑 함수에 적용하여 모든 컬렉션이 결합된 스트림을 반환|

### flatMap
```java
Stream<String[]> -> flatMap -> Stream<String>
Stream<Set<String>> -> flatMap -> Stream<String>
Stream<List<String>> -> flatMap -> Stream<String>
```

```java
List<String> words = Arrays.asList("Hello", "World");


List<String[]> mapList =
        words.stream()
                .map(word->word.split(""))
                .distinct()
                .collect(toList());

List<String> flatMapList =
        words.stream()
                .map(word->word.split(""))
                // 생성된 스트림을 하나의 스트림으로 평면화
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
```

## references
https://wjjeong.tistory.com/42
https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/stream/Stream.html