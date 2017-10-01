package au.com.anthonybruno;

import au.com.anthonybruno.settings.FixedWidthSettings;
import com.univocity.parsers.fixed.FixedWidthFields;
import com.univocity.parsers.fixed.FixedWidthParser;
import com.univocity.parsers.fixed.FixedWidthParserSettings;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class FixedWidthPojoGenTest {

    private final int rows = 5;
    private final FixedWidthFields fixedWidthFields = new FixedWidthFields(40, 40);

    @Test
    public void isNonEmpty() {
        String result = generatePersonFixedWidth();

        assertFalse("Expected result to contain something", result.isEmpty());
    }

    @Test
    public void isFixedWidth() {
        String result = generatePersonFixedWidth();

        FixedWidthParserSettings settings = new FixedWidthParserSettings(fixedWidthFields);
        FixedWidthParser fixedWidthParser = new FixedWidthParser(settings);

        List<String[]> parsedResult = fixedWidthParser.parseAll(new StringReader(result));
        assertEquals(5, parsedResult.size());
    }

    @Test
    public void fixedWidthFile() {
        File file = generateFixedWidthFile();

        FixedWidthParserSettings settings = new FixedWidthParserSettings(fixedWidthFields);
        FixedWidthParser fixedWidthParser = new FixedWidthParser(settings);

        List<String[]> parsedResult = fixedWidthParser.parseAll(file);
        assertEquals(printRows(parsedResult), 5, parsedResult.size());

    }

    private String printRows(List<String[]> rows) {
        StringBuilder out = new StringBuilder();
        rows.forEach((row) -> out.append(Arrays.toString(row)).append("\n"));
        return out.toString();
    }


    private String generatePersonFixedWidth() {
        return Gen.start()
                .use(Person.class)
                .generate(rows)
                .asFixedWidth(new FixedWidthSettings(false, fixedWidthFields))
                .toStringForm();
    }

    private File generateFixedWidthFile() {
        File file;
        try {
            file = File.createTempFile("fixed-tst", ".txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        file.deleteOnExit();
        return Gen.start()
                .use(Person.class)
                .generate(rows)
                .asFixedWidth(new FixedWidthSettings(false, fixedWidthFields))
                .toFile(file);
    }
}
