package chapter5;

import logging.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class Practice5 {

    public static void main(String... args){

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // 1. 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리하시오.
        List<Transaction> tr2011 = transactions.stream().filter(t -> t.getYear()==2011).sorted(comparing(Transaction::getValue)).collect(toList());
        Logger.printList("--ex1--", tr2011);

        // 2. 거래자가 근무하는 모든 도시를 중복 없이 나열하시오.
        List<String> cities = transactions.stream().map(t -> t.getTrader().getCity()).distinct().collect(toList());
        Logger.printList("--ex2--", cities);

        // 3. 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오.
        List<Trader> tradersInCambridge = transactions.stream().map(Transaction::getTrader).distinct().filter(t -> t.getCity().equals("Cambridge")).sorted(comparing(Trader::getName)).collect(toList());
        Logger.printList("--ex3--", tradersInCambridge);

        // 4. 모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오.
        String traderStr = transactions.stream().map(t -> t.getTrader().getName()).distinct().sorted().reduce("", (s1, s2) -> s1 + s2);
        System.out.println("--ex4--");
        System.out.println("traderStr = " + traderStr);

        // 5. 밀라노에 거래자가 있는가?
        boolean milanBased = transactions.stream().anyMatch(t -> t.getTrader().getCity().equals("Milan"));
        System.out.println("--ex5--");
        System.out.println("milanBased = " + milanBased);

        // 6. 케임브리지에 거주하는 거래자의 모든 트랜잭션 값을 출력하시오.
        System.out.println("--ex6--");
        transactions.stream().filter(t->t.getTrader().getCity().equals("Cambridge")).map(Transaction::getValue).forEach(System.out::println);

        // 7. 전체 트랜잭션 중 최댓값은 얼마인가?
        Optional<Integer> maxValue = transactions.stream().map(Transaction::getValue).reduce(Integer::max);
        System.out.println("maxValue = " + maxValue);

        // 8. 전체 트랜잭션 중 최솟값은 얼마인가?
        Optional<Transaction> minValue = transactions.stream().min(comparing(Transaction::getValue));
        System.out.println("minValue = " + minValue);

    }

}
