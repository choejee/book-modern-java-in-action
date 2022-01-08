package logging;

import java.util.List;

public class Logger {

    public static <T> void printList(String title, List<T> list) {
        System.out.println(title);
        for (T t : list) {
            System.out.println(t.toString());
        }
        System.out.println("-----------------");
    }
}
