package chapter2.behavior.param;

import java.util.ArrayList;
import java.util.List;

public class AppleFilter {
    public static List<Apple> filterApple(List<Apple> inventory, ApplePredicate p) {
        List<Apple> filteringApples = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                filteringApples.add(apple);
            }
        }
        return filteringApples;
    }
}
