package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaDemo02 {
    static List<Integer> list = new ArrayList<Integer>();

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }

        Stream<Integer> stream = list.stream();
//        stream.limit(2).collect(Collectors.toList()).forEach(System.out::println);


        List<String> collect = list.stream()
                .filter(i -> i < 50)
                .map(i -> {
                    return new String(i + "....");
                }).collect(Collectors.toList());
        collect.forEach(System.out::println);



//        for (Integer integer : collect) {
//            System.out.println(integer);
//        }
    }


}
