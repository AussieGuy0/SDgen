package au.com.anthonybruno.generator.defaults;

import au.com.anthonybruno.generator.RangedGenerator;

import java.util.concurrent.ThreadLocalRandom;

public class IntGenerator extends RangedGenerator<Integer> {

    public IntGenerator() {
       this(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public IntGenerator(int min, int max) {
        super(min, max);
    }

    @Override
    public Integer generate() {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
