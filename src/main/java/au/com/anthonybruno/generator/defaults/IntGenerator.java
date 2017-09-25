package au.com.anthonybruno.generator.defaults;

import au.com.anthonybruno.generator.Generator;

import java.util.concurrent.ThreadLocalRandom;

public class IntGenerator implements Generator<Integer> {

    private final int min;
    private final int max;

    public IntGenerator() {
       this(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public IntGenerator(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public Integer generate() {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
