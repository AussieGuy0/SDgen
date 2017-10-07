package au.com.anthonybruno.generator;

public abstract class RangedGenerator<T> implements Generator<T> {

    protected final T min;
    protected final T max;

    protected RangedGenerator(T min, T max) {
        this.min = min;
        this.max = max;
    }
}
