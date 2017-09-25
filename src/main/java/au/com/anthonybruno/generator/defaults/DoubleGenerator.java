package au.com.anthonybruno.generator.defaults;

import au.com.anthonybruno.generator.Generator;

import java.util.concurrent.ThreadLocalRandom;

public class DoubleGenerator implements Generator<Double> {

    @Override
    public Double generate() {
        return ThreadLocalRandom.current().nextDouble();
    }
}
