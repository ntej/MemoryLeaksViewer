package info3.gm.com.memoryleaksviewer.leakCanary;

/**
 * Created by hz7d7v on 7/10/17.
 */

public class Preconditions {
    /**
     * Returns instance unless it's null.
     *
     * @throws NullPointerException if instance is null
     */
    static <T> T checkNotNull(T instance, String name) {
        if (instance == null) {
            throw new NullPointerException(name + " must not be null");
        }
        return instance;
    }

    private Preconditions() {
        throw new AssertionError();
    }
}
