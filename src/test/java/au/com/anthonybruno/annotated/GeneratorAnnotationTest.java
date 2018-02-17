package au.com.anthonybruno.annotated;

import au.com.anthonybruno.Gen;
import au.com.anthonybruno.settings.CsvSettings;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.junit.Test;

import java.io.StringReader;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.*;

public class GeneratorAnnotationTest {

    public static final int minAge = 18;
    public static final int maxAge = 80;


    @Test
    public void notEmpty() {
        String result = generateAnnotatedPersonCsv();
        assertFalse(result.isEmpty());
    }

    @Test
    public void correctHeaders() {
        String[] firstRow = generateAnnotatedPersonCsv().split("\n")[0].split(",");

        assertArrayEquals(new String[]{"name", "yearsSinceBirth", "gender"}, firstRow);
    }

    @Test
    public void everyGenderFemale() {
        String result = generateAnnotatedPersonCsv();
        CsvParser csvParser = new CsvParser(new CsvParserSettings());

        boolean firstRow = true;
        for (String[] row : csvParser.iterate(new StringReader(result))) {
            if (firstRow) {
                firstRow = false;
                continue;
            }

            if (!row[2].equals("Female")) {
                fail("Gender column is not female! Result: " + row[2]);
            }
        }
    }

    @Test
    public void everyNameInList() {
        String result = generateAnnotatedPersonCsv();
        CsvParser csvParser = new CsvParser(new CsvParserSettings());

        List<String> names = AnnotatedPerson.NameGenerator.getNames();
        boolean firstRow = true;
        for (String[] row : csvParser.iterate(new StringReader(result))) {
            if (firstRow) {
                firstRow = false;
                continue;
            }

            if (!names.contains(row[0])) {
                fail("Name column value is not in list! Result: '" + row[0] + " 'List: " + names);
            }
        }
    }

    @Test
    public void agesInRange() {
        String result = generateAnnotatedPersonCsv();
        CsvParser csvParser = new CsvParser(new CsvParserSettings());

        boolean firstRow = true;
        for (String[] row : csvParser.iterate(new StringReader(result))) {
            if (firstRow) {
                firstRow = false;
                continue;
            }

            int age = Integer.parseInt(row[1]);
            assertTrue(age + "",age <= maxAge);
            assertTrue(age + "",age >= minAge);

        }
    }

    @Test(expected = IllegalStateException.class)
    public void generateMultivalued() {
        Gen.start()
                .use(UnsupportedTypeClass.class)
                .generate(5)
                .asCsv()
                .toStringForm();

    }

    private String generateAnnotatedPersonCsv() {
        return Gen.start()
                .use(AnnotatedPerson.class)
                .generate(5)
                .asCsv(new CsvSettings(true))
                .toStringForm();
    }


    private static class UnsupportedTypeClass {

        private final AtomicInteger unsupportedType;

        private UnsupportedTypeClass(AtomicInteger unsupportedType) {
            this.unsupportedType = unsupportedType;
        }
    }

}
