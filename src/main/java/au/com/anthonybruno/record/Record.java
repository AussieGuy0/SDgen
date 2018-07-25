package au.com.anthonybruno.record;

public interface Record<T> extends Iterable<T> {

    T get(int index);

    T get(String fieldName);
}
