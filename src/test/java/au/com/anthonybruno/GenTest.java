package au.com.anthonybruno;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GenTest {

    @Test
    public void csvWithPojoNonEmpty() {
        String result = new Gen().use(Person.class).asCsv().toString();

        assertFalse("Expected result to contain something", result.isEmpty());
    }

    @Test
    public void csvWithPojoHasMultipleLines() {
        String result = new Gen().use(Person.class).asCsv().toString();

        assertTrue(countOccurrences(result, "\n") > 1);
    }

    @Test
    public void csvWithPojoHas2Columns() {
        String result = new Gen().use(Person.class).asCsv().toString();

        String firstRow = result.substring(0, result.indexOf("\n"));

        assertEquals(1, countOccurrences(firstRow, ","));

    }

    private int countOccurrences(String haystack, String needle) {
        int count = 0;
        int pointer = 0;
        while ((pointer = haystack.indexOf(needle, pointer)) > 0) {
            pointer++;
            count++;
        }
        return count;
    }
}
