package au.com.anthonybruno;

import com.github.javafaker.Faker;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ContextGeneratorTest {

    private final int rowsToGenerate = 5;
    private final Faker faker = new Faker();

    @Test
    public void worksLikeANormalGenerator() {
        Faker faker = new Faker();
        String result = Gen.start()
                .addField("first_name", context -> faker.name().firstName())
                .generate(5)
                .asCsv()
                .toStringForm();
        assertEquals(6, result.split("\n").length);
    }

    @Test
    public void getFieldValueOfSingleField() {
        String result = Gen.start()
                .addField("full_name", context -> context.getFieldValue("first_name", String.class) + " Smith")
                .addField("first_name", () -> faker.name().firstName())
                .generate(5)
                .asCsv()
                .toStringForm();
        for (String line : result.split("\n")) {
            String[] values = line.split(",");
            System.out.println(line);
        }
    }

    @Test(expected = RuntimeException.class)
    public void throwsErrorIfGetFieldValueOnNonExistentField() {
         Gen.start()
                .addField("full_name", context -> context.getFieldValue("first_name", String.class) + " Smith")
                .generate(5)
                .asCsv()
                .toStringForm();
    }


}
