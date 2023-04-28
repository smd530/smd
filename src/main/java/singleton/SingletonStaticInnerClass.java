package singleton;

public class SingletonStaticInnerClass {

    static {
        System.out.println("SingletonStaticInnerClass 创建成功......");
    }

    private SingletonStaticInnerClass () {}

    private static class SingletonHolder {
        static {
            System.out.println("SingletonHolder 创建成功");
        }
        public static SingletonStaticInnerClass singletonStaticInnerClass = new SingletonStaticInnerClass();
    }

    public static SingletonStaticInnerClass getInstance() {
        return SingletonHolder.singletonStaticInnerClass;
    }
}