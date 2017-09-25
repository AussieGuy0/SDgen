package au.com.anthonybruno;

import au.com.anthonybruno.settings.FixedWidthSettings;
import com.univocity.parsers.fixed.FixedWidthFields;
import com.univocity.parsers.fixed.FixedWidthParser;
import com.univocity.parsers.fixed.FixedWidthParserSettings;
import org.junit.Test;

import java.io.StringReader;
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


    private String generatePersonFixedWidth() {
        return Gen.create().use(Person.class).asFixedWidth(new FixedWidthSettings(rows, fixedWidthFields)).toString();
    }
}
