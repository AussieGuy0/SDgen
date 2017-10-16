package au.com.anthonybruno.generator;

import au.com.anthonybruno.generator.defaults.BooleanGenerator;
import au.com.anthonybruno.generator.defaults.StringGenerator;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertTrue;

public class PrimitiveGeneratorsTest {

    @Test
    public void stringGeneration() {
        StringGenerator generator = new StringGenerator();
        int numValues = 100000;
        Set<String> set = new HashSet<>();

        for (int i = 0; i < numValues; i++) {
            String value = generator.generate();
            set.add(value);
        }

        assertTrue(set.size() + "", set.size() >= 90000); //ensure most values generated are random
    }

    @Test
    public void booleanGenerator() {
        BooleanGenerator generator = new BooleanGenerator();
        int numValues = 100000;

        int trueValues = 0;
        int falseValues = 0;
        for (int i = 0; i < numValues; i++) {
            boolean value = generator.generate();
            if (value) {
                trueValues++;
            } else {
                falseValues++;
            }
        }
        int diff = Math.abs(trueValues - falseValues);

        assertTrue(diff < 1000);

    }
}
