package au.com.anthonybruno.generator.format;

@FunctionalInterface
public interface Formatter<T> {

    String format(T toFormat);
}
