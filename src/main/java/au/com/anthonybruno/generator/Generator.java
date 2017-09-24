package au.com.anthonybruno.generator;

@FunctionalInterface
public interface Generator<T> {

    T generate();
}
