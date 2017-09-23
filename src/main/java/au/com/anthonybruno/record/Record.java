package au.com.anthonybruno.record;

public interface Record extends Iterable<Object> {

    Object get(int index);

    Object get(String fieldName);
}
