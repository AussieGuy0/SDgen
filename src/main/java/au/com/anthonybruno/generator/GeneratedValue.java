package au.com.anthonybruno.generator;


import au.com.anthonybruno.generator.format.Format;
import au.com.anthonybruno.generator.format.ToStringFormat;


public class GeneratedValue<T> {

    private T value;
    private Format<T> format;


    public GeneratedValue(T value) {
        this(value, new ToStringFormat<>());
    }

    public GeneratedValue(T value, Format<T> format) {
        this.value = value;
        this.format = format;
    }

    public T get() {
        return value;
    }

    public String format() {
        return format.format(value);
    }

}
