package au.com.anthonybruno.generator;

import au.com.anthonybruno.generator.defaults.StringGenerator;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertTrue;

public class PrimitiveGeneratorsTest {

    @Test
    public void stringGeneration() {
        StringGenerator generator = new StringGenerator();
        int numValues = 10000;
        Set<String> set = new HashSet<>();

        for (int i = 0; i < numValues; i++) {
            String value = generator.generate();
            set.add(value);
        }

        assertTrue(set.size() >= 9500); //ensure most values generated are random
    }
}
