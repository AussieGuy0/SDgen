package au.com.anthonybruno.generator;

import java.util.concurrent.ThreadLocalRandom;

public class StringGenerator implements Generator<String> {


    @Override
    public String generate() {
        StringBuilder stringBuilder = new StringBuilder();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int length = random.nextInt(1, 10);
        for (int i = 0; i < length; i++) {
           stringBuilder.append((char) random.nextInt(48,122));
        }
        return stringBuilder.toString();
    }
}
