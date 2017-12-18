package au.com.anthonybruno.generator;

import au.com.anthonybruno.generator.defaults.DateGenerator;
import au.com.anthonybruno.generator.format.DateFormatter;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.fail;

public class DateGeneratorTest {

    @Test
    public void dateGeneration() throws Exception {
        DateGenerator generator = new DateGenerator();
        int numValues = 100000;
        Set<Date> set = new HashSet<>();

        for (int i = 0; i < numValues; i++) {
            Date value = generator.generate();
            set.add(value);
        }

        assertTrue(set.size() + "", set.size() >= 90000); //ensure most values generated are random
    }

    @Test
    public void formattedDateGeneration() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd");

        DateFormatter dateFormatter = new DateFormatter(simpleDateFormat);
        FormattedGenerator<Date> generator = new FormattedGenerator<>(new DateGenerator(), dateFormatter);

        int generateNum = 1000;
        for (int i = 0; i < generateNum; i++) {
            try {
                simpleDateFormat.parse(generator.generate());
            } catch (ParseException e) {
                e.printStackTrace();
                fail();
            }
        }
    }
}
