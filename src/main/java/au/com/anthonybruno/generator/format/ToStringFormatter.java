package au.com.anthonybruno.generator.format;


public class ToStringFormatter<T> implements Formatter<T> {

    @Override
    public String format(T toFormat) {
        return toFormat.toString();
    }
}
