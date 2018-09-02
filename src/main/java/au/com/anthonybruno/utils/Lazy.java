package au.com.anthonybruno.utils;

import java.util.function.Supplier;

public class Lazy<T> implements Supplier<T> {

    private final Supplier<T> supplier;
    private T value;
    private boolean hasCached;

    public Lazy(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    @Override
    public T get() {
        if (!hasCached) {
            value = this.supplier.get();
            hasCached = true;
        }
        return value;
    }

}
