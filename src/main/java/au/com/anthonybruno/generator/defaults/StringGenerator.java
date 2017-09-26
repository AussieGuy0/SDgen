package au.com.anthonybruno.generator.defaults;

import au.com.anthonybruno.generator.Generator;

import java.util.concurrent.ThreadLocalRandom;

public class StringGenerator implements Generator<String> {

    private final CharGenerator charGenerator = new CharGenerator();

    @Override
    public String generate() {
        StringBuilder stringBuilder = new StringBuilder();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int length = random.nextInt(2, 10);
        for (int i = 0; i < length; i++) {
           stringBuilder.append(charGenerator.generate());
        }
        return stringBuilder.toString();
    }
}
