package chapter2;

import chapter2.behavior.param.*;
import chapter2.lambda.intro.ListFilter;
import logging.Logger;

import java.util.Arrays;
import java.util.List;

import static chapter2.behavior.param.Color.GREEN;
import static chapter2.behavior.param.Color.RED;

public class FilteringService {

    public static void main(String... args) {
        /* 동작 파라미터화
         *  : 메서드가 동작(기능)을 받아서 내부적으로 다양한 동작을 수행할 수 있다.
         */

        // 1. 클래스
        // 동작을 추상화했으나 여러 클래스를 구현해서 인스턴스화하는 과정이 필요함
        List<Apple> inventory = Arrays.asList(new Apple(80, GREEN),
                new Apple(180, GREEN),
                new Apple(120, RED));

        List<Apple> heavyApples = AppleFilter.filterApple(inventory, new AppleHeavyWeightPredicate());
        Logger.printList("Heavy apples", heavyApples);
        List<Apple> greenApples = AppleFilter.filterApple(inventory, new AppleGreenColorPredicate());
        Logger.printList("Green apples", greenApples);

        // 2. 람다 맛보기
        // 리스트 형식으로 추상화 + 람다 표현식 사용으로 인터페이스 구현 및 인스턴스화 없이 여러 요구사항 반영 가능.
        List<Apple> redApples = ListFilter.filter(inventory, (Apple apple) -> RED.equals(apple.getColor()));
        Logger.printList("Red apples", redApples);

        List<Integer> numbers = Arrays.asList(1,4,5,7,8,12,15,18,20);
        List<Integer> evenNumber = ListFilter.filter(numbers, (integer -> integer%2 == 0));
        Logger.printList("Even number", evenNumber);
    }
}
