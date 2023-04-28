package singleton;

public class SingletonLazy {

    private static SingletonLazy singletonLazy = null;
    private SingletonLazy () {

    }

    public static synchronized SingletonLazy getInstance() {
        if (singletonLazy == null) {
            singletonLazy = new SingletonLazy();
        }
        return singletonLazy;
    }
}