package au.com.anthonybruno.generator.defaults;

import au.com.anthonybruno.generator.Generator;

import java.util.concurrent.ThreadLocalRandom;

public class BooleanGenerator implements Generator<Boolean> {

    @Override
    public Boolean generate() {
        return ThreadLocalRandom.current().nextBoolean();
    }
}
