package chapter4;

import logging.Logger;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class IntroStreams {

    public static void main(String[] args) {
        // 저칼로리의 요리명을 반환하고 칼로리를 기준으로 요리를 정렬하라.

        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );

        // java 7 (스트림 이전)
        List<Dish> lowCaloricDishes = new ArrayList<>(); // 가비지 변수
        for (Dish dish : menu) {
            if (dish.getCalories() < 400) {
                lowCaloricDishes.add(dish);
            }
        }

        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish d1, Dish d2) {
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });

        List<String> lowCaloricDishesName1 = new ArrayList<>();
        for (Dish dish : lowCaloricDishes) {
            lowCaloricDishesName1.add(dish.getName());
        }

        // java 8 (스트림 적용)
        List<String> lowCaloricDishesName2 =
                menu.stream()
                        .filter(dish -> dish.getCalories() < 400)
                        .sorted(comparing(Dish::getCalories))
                        .map(Dish::getName)
                        .collect(toList());
    }

}
