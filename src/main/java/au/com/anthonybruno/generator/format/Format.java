package au.com.anthonybruno.generator.format;

@FunctionalInterface
public interface Format<T> {

    String format(T toFormat);
}
