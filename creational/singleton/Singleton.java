package creational.singleton;

public class Singleton {

    // Volatile keyword ensures the visibility of changes to variables across threads
    private static volatile Singleton instance;

    // Private constructor to prevent instantiation from outside
    private Singleton() {}

    // Thread-safe method with double-checked locking
    public static Singleton getInstance() {
        if (instance == null) {  // First check (no locking)
            synchronized (Singleton.class) {  // Locking
                if (instance == null) {  // Second check (after acquiring the lock)
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
