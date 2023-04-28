package singleton;

import java.util.HashMap;
import java.util.Map;

public class TestSingleton {

    public static void main(String[] args) {
        Map<String, Integer> stringIntegerHashMap = new HashMap<>();

        SingletonHungry instance = SingletonHungry.getInstance();
        SingletonHungry instance1 = SingletonHungry.getInstance();
        System.out.println(instance.hashCode());
        System.out.println(instance1.hashCode());

        SingletonLazy instance2 = SingletonLazy.getInstance();
        SingletonLazy instance3 = SingletonLazy.getInstance();
        System.out.println(instance2 == instance3);

        SingletonDoubleCheck instance4 = SingletonDoubleCheck.getInstance();
        SingletonDoubleCheck instance5 = SingletonDoubleCheck.getInstance();
        System.out.println(instance4 == instance5);

        SingletonStaticInnerClass instance6 = SingletonStaticInnerClass.getInstance();
        SingletonStaticInnerClass instance7 = SingletonStaticInnerClass.getInstance();
        System.out.println(instance6.hashCode());
        System.out.println(instance7.hashCode());


    }
}