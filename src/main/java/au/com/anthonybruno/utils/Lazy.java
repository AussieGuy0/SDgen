package au.com.anthonybruno.utils;

import java.util.function.Supplier;

public class Lazy<T> {

    private final Supplier<T> supplier;
    private T value;
    private boolean hasCached;

    public Lazy(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    public T get() {
        if (!hasCached) {
            value = this.supplier.get();
            hasCached = true;
        }
        return value;
    }

}
