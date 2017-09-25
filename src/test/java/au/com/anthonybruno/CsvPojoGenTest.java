package au.com.anthonybruno;

import au.com.anthonybruno.settings.CsvSettings;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

public class CsvPojoGenTest {

    private final String delimiter = ",";
    private final int rowsToGenerate = 5;

    @Test
    public void isNonEmpty() {
        String result = generatePersonCsv();

        assertFalse("Expected result to contain something", result.isEmpty());
    }

    @Test
    public void generatesCorrectAmountOfRows() {
        String result = generatePersonCsv();

        assertEquals(rowsToGenerate, result.split("\n").length);
    }

    @Test
    public void has2Columns() {
        String result = generatePersonCsv();
        String firstRow = result.substring(0, result.indexOf("\n"));

        assertEquals(1, countOccurrences(firstRow, delimiter));
    }

    @Test
    public void secondColumnIsNumber() {
        String result = generatePersonCsv();

        for (String line : result.split("\n")) {
            String value = line.split(delimiter)[1];
            try {
                Integer.parseInt(value);
            } catch (NumberFormatException e) { //programming via exceptions...
                fail(value + " in row " + line + " is not a number!");
            }
        }
    }

    @Test
    public void firstColumnIsRandomised() {
        String result = generatePersonCsv();
        Set<String> values = new HashSet<>();

        for (String line : result.split("\n")) {
            String value = line.split(delimiter)[0];
            values.add(value);
        }


        assertNotEquals("Values generated were not random ", 1, values.size());
    }

    @Test
    public void secondColumnIsRandomised() {
        String result = generatePersonCsv();
        Set<Integer> values = new HashSet<>();

        for (String line : result.split("\n")) {
            int value = Integer.parseInt(line.split(delimiter)[1]);
            values.add(value);
        }


        assertNotEquals("Values generated were not random ", 1, values.size());
    }


    private String generatePersonCsv() {
       return Gen.create().use(Person.class).asCsv(new CsvSettings(5)).toString();
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
