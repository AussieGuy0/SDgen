package au.com.anthonybruno.generator.format;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DateFormatterTest {

    @Test
    public void formatBasicDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/M/d");
        DateFormatter dateFormatter = new DateFormatter(dateFormat);

        Instant instant = Instant.now();
        String formattedDate = dateFormatter.format(Date.from(instant));
        LocalDate date = LocalDate.now();

        String expectedDate = date.getYear() + "/" + date.getMonthValue() + "/" + date.getDayOfMonth();
        assertEquals(expectedDate, formattedDate);
    }
}
