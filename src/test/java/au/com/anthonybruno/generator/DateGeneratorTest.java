package au.com.anthonybruno.generator;

import au.com.anthonybruno.generator.defaults.DateGenerator;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertTrue;

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
}
