package au.com.anthonybruno.generator.defaults;

import au.com.anthonybruno.generator.Generator;

import java.util.concurrent.ThreadLocalRandom;

public class LongGenerator implements Generator<Long> {

    @Override
    public Long generate() {
        return ThreadLocalRandom.current().nextLong();
    }
}
