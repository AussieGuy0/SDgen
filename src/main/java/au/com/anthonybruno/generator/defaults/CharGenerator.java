package au.com.anthonybruno.generator.defaults;

import au.com.anthonybruno.generator.Generator;

import java.util.concurrent.ThreadLocalRandom;

public class CharGenerator implements Generator<Character> {

    @Override
    public Character generate() {
        return (char) ThreadLocalRandom.current().nextInt(48,122);
    }
}
