package au.com.anthonybruno.generator;

@FunctionalInterface
public interface ContextGenerator<T> {

    T generate(Context context);
}
