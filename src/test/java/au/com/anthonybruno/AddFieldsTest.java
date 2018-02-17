package au.com.anthonybruno;

import au.com.anthonybruno.definition.RecordDefinition;
import au.com.anthonybruno.definition.StartDefinition;
import au.com.anthonybruno.generator.defaults.IntGenerator;
import au.com.anthonybruno.generator.defaults.StringGenerator;
import au.com.anthonybruno.settings.CsvSettings;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class AddFieldsTest {

    private final int rowsToGenerate = 5;

    @Test
    public void generateSingleFieldNotEmpty() {
        String result = generateSingleFieldCsv();
        assertFalse(result.isEmpty());
    }

    @Test
    public void generateSingleFieldsCorrectAmountOfRows() {
        String result = generateSingleFieldCsv();
        assertEquals(rowsToGenerate, result.split("\n").length);
    }

    @Test
    public void generateLotsOfFields() {
        StartDefinition fieldDefinition = Gen.start();
        RecordDefinition recordDefinition = null;
        for (int i = 0; i < 100; i++) {
            recordDefinition = fieldDefinition.addField("Field " + i, new StringGenerator());
        }
        String result = recordDefinition.generate(1000).asCsv().toStringForm();

        String firstLine = result.substring(0, result.indexOf("\n"));
        assertEquals(100,  firstLine.split(",").length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addNullGenerator() {
        Gen.start().addField("bad", null).generate(5).asCsv().toStringForm();
    }

    @Test(expected = IllegalStateException.class)
    public void tryToGenerateNegativeRows() {
        Gen.start().addField("bad", new IntGenerator()).generate(-1).asCsv().toStringForm();
    }

    public String generateSingleFieldCsv() {
        return Gen.start()
                .addField("Name", new StringGenerator())
                .generate(rowsToGenerate)
                .asCsv(new CsvSettings(false))
                .toStringForm();
    }
}
