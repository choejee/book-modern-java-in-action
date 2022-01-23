package chapter3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExecuteAroundPattern {

    private static String FILE = ExecuteAroundPattern.class.getResource("./data.txt").getFile();

    public static void main(String[] args) throws IOException {
        /*
        실행 어라운드 패턴 : 실제 자원을 처리하는 코드를 설정과 정리 코드로 감싸고 있는 패턴
        람다를 활용하여 실제 작업 코드만 유연하게 적용해보자
        */

        String oneLine = processFile(br -> br.readLine());
        System.out.println("oneLine = " + oneLine);

        String twoLine = processFile(br -> br.readLine() + br.readLine());
        System.out.println("twoLine = " + twoLine);
    }

    public static String processFile(BufferedReaderProcessor processor) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            return processor.process(br);
        }
    }

}
