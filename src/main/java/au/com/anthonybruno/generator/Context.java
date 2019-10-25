package au.com.anthonybruno.generator;

public interface Context {

    <T> T getFieldValue(String fieldName, Class<T> clazz);

}
