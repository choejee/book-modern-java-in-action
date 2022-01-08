package chapter2.lambda.intro;

import java.util.ArrayList;
import java.util.List;

public class ListFilter {

    public static <T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> filteringResult = new ArrayList<>();
        for (T t : list) {
            if (p.test(t)) {
                filteringResult.add(t);
            }
        }
        return filteringResult;
    }
}
