package au.com.anthonybruno.generator;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class StringGenerator implements Generator<String> {


    @Override
    public String generate() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
           stringBuilder.append((char) ThreadLocalRandom.current().nextInt(48,122));
        }
        return stringBuilder.toString();
    }
}
