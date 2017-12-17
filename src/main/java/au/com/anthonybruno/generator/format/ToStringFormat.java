package au.com.anthonybruno.generator.format;


public class ToStringFormat<T> implements Format<T> {

    @Override
    public String format(T toFormat) {
        return toFormat.toString();
    }
}
