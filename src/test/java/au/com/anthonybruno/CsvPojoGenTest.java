package au.com.anthonybruno;

import au.com.anthonybruno.settings.CsvSettings;
import au.com.anthonybruno.utils.TextFile;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
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
    public void has3Columns() {
        String result = generatePersonCsv();
        String firstRow = result.substring(0, result.indexOf("\n"));

        assertEquals(2, countOccurrences(firstRow, delimiter));
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

    @Test
    public void containsHeaders() {
        String result = generatePersonCsvWithHeaders();
        String[] headerRows = result.split("\n")[0].split(",");
        assertEquals("name", headerRows[0]);
        assertEquals("age", headerRows[1]);
        assertEquals("birthday", headerRows[2]);
    }

    @Test
    public void createFile() {
        File file = generatePersonCsvFile();
        assertNotNull(file);
        TextFile readFile = new TextFile(file);

        assertTrue(!readFile.getText().isEmpty());
    }

    @Test
    public void generateCsvWithTabs() {
        CsvSettings csvSettings = new CsvSettings.Builder().setDelimiter('\t').build();
        String result = Gen.start().use(Person.class).generate(5).asCsv(csvSettings).toStringForm();
        String firstRow = result.split("\n")[0];

        assertEquals(3, firstRow.split("\t").length);
    }



    private String generatePersonCsv() {
       return Gen.start().use(Person.class).generate(rowsToGenerate).asCsv(new CsvSettings(false)).toStringForm();
    }

    private String generatePersonCsvWithHeaders() {
        return Gen.start().use(Person.class).generate(rowsToGenerate).asCsv(new CsvSettings(true)).toStringForm();
    }

    private File generatePersonCsvFile() {
        File file;
        try {
            file = File.createTempFile("test", ".csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        file.deleteOnExit();
        return Gen.start().use(Person.class).generate(5).asCsv(new CsvSettings(false)).toFile(file);
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
