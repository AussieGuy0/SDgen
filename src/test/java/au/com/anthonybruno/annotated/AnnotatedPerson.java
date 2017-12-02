package au.com.anthonybruno.annotated;

import au.com.anthonybruno.annotation.Generation;
import au.com.anthonybruno.annotation.Range;
import au.com.anthonybruno.generator.Generator;
import au.com.anthonybruno.generator.OptionGenerator;
import au.com.anthonybruno.generator.defaults.IntGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static au.com.anthonybruno.annotated.GeneratorAnnotationTest.maxAge;
import static au.com.anthonybruno.annotated.GeneratorAnnotationTest.minAge;

public class AnnotatedPerson {

    @Generation(NameGenerator.class)
    private final String name;

    @Range(min=minAge, max=maxAge)
    @Generation(value = IntGenerator.class, name="yearsSinceBirth")
    private final int age;

    @Generation(GenderGenerator.class)
    private final String gender;

    public AnnotatedPerson(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public static class NameGenerator implements Generator<String> {

        private static final String[] names = {"Olivia", "Charlotte", "Mia", "Ava", "Amelia", "Emily", "Sofia", "Ruby"};
        private static final int namesLength = names.length;

        @Override
        public String generate() {
            int randIndex = ThreadLocalRandom.current().nextInt(0, namesLength);
            return names[randIndex];
        }

        public static List<String> getNames() {
            return Arrays.asList(names);
        }
    }

    public static class GenderGenerator implements Generator<String> {

        private final OptionGenerator<String> optionGenerator = new OptionGenerator<>("Female");

        @Override
        public String generate() {
            return optionGenerator.generate();
        }
    }
}
