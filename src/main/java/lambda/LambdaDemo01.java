package lambda;

import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;

public class LambdaDemo01 {

    public static void main(String[] args) {

        int i1 = calculateNum(new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                return left + right;
            }
        });
        System.out.println(i1+ "\n");

        /*
            函数式编程不关注类名方法名 只关注方法参数和方法体
            函数式接口 匿名内部类得是一个接口 接口只有一个抽象方法
         */
        int i = calculateNum((a, b) -> {
            return a + b;
        });
        System.out.println(i + "\n");
        System.out.println("-----------------------------------");

        printNum(new IntPredicate() {
            @Override
            public boolean test(int value) {
                return value % 2 == 0;
            }
        });

        printNum((value) -> {
            return value % 2 == 0;
        });

        Integer result = typeConver(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.valueOf(s);
            }
        });
        System.out.println(result);

        Integer integer = typeConver((str) -> {
            return Integer.valueOf(str);
        });
        System.out.println("..." + integer);

        System.out.println("-----------");

        foreachArr(new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.println("foreachArr..." + value);
            }
        });

        foreachArr((val) -> {
            System.out.println("foreachArr....." + val);
        });

    }

    public static int calculateNum(IntBinaryOperator operator) {
        int a = 10;
        int b = 20;

        return operator.applyAsInt(a, b);

    }
    public static void printNum(IntPredicate predicate) {
        int [] arr = {1,2,3,4,5,6,7,8,9,10};
        for (int i: arr) {
            if (predicate.test(i)) {
                System.out.println(i);
            }
        }
    }

    public static <R> R typeConver(Function<String, R> function) {
        String str = "1234";
        R apply = function.apply(str);
        return apply;
    }

    public static void foreachArr(IntConsumer consumer) {
        int arr [] = {1,2,3,4,5,6,7,8,9,10};
        for (int i : arr) {
            if (i % 2 == 0) {
                consumer.accept(i);
            }
        }



    }

}
