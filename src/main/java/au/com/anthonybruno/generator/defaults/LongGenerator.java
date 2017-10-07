package au.com.anthonybruno.generator.defaults;

import au.com.anthonybruno.generator.RangedGenerator;

import java.util.concurrent.ThreadLocalRandom;

public class LongGenerator extends RangedGenerator<Long> {

    public LongGenerator() {
        this(Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public LongGenerator(Long min, Long max) {
        super(min, max);
    }

    @Override
    public Long generate() {
        return ThreadLocalRandom.current().nextLong();
    }
}
