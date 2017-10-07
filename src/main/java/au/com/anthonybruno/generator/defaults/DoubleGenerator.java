package au.com.anthonybruno.generator.defaults;

import au.com.anthonybruno.generator.RangedGenerator;

import java.util.concurrent.ThreadLocalRandom;

public class DoubleGenerator extends RangedGenerator<Double> {


    public DoubleGenerator() {
        super(Double.MIN_VALUE, Double.MAX_VALUE);
    }

    public DoubleGenerator(Double min, Double max) {
        super(min, max);
    }

    @Override
    public Double generate() {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }
}
