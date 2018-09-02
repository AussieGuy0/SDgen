package au.com.anthonybruno.generator.format;

import java.text.DateFormat;
import java.util.Date;

public class DateFormatter implements Formatter<Date> {

    private final DateFormat dateFormat;

    public DateFormatter(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public String format(Date toFormat) {
        return dateFormat.format(toFormat);
    }
}
