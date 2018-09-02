package au.com.anthonybruno;

import au.com.anthonybruno.settings.FixedWidthField;
import au.com.anthonybruno.settings.FixedWidthSettings;
import com.univocity.parsers.fixed.FixedWidthFields;
import com.univocity.parsers.fixed.FixedWidthParser;
import com.univocity.parsers.fixed.FixedWidthParserSettings;
import org.junit.Ignore;
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
    private final List<FixedWidthField> fields = FixedWidthField.create(20, 20, 40);

    @Test
    public void isNonEmpty() {
        String result = generatePersonFixedWidth();

        assertFalse("Expected result to contain something", result.isEmpty());
    }

    @Test
    public void isFixedWidth() {
        String result = generatePersonFixedWidth();
        FixedWidthParser fixedWidthParser = createParser();
        List<String[]> parsedResult = fixedWidthParser.parseAll(new StringReader(result));
        assertEquals(5, parsedResult.size());
    }

    @Ignore("Parser seems to be randomly failing?")
    @Test
    public void fixedWidthFile() {
        File file = generateFixedWidthFile();
        FixedWidthParser fixedWidthParser = createParser();
        List<String[]> parsedResult = fixedWidthParser.parseAll(file);
        assertEquals(printRows(parsedResult), 5, parsedResult.size());
    }

    private FixedWidthParser createParser() {
        FixedWidthFields fixedWidthFields = new FixedWidthFields();
        fields.forEach(field -> {
            fixedWidthFields.addField(field.getLength());
        });
        FixedWidthParserSettings settings = new FixedWidthParserSettings(fixedWidthFields);
        return new FixedWidthParser(settings);
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
                .asFixedWidth(new FixedWidthSettings(false, fields))
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
                .asFixedWidth(new FixedWidthSettings(false, fields))
                .toFile(file);
    }
}
