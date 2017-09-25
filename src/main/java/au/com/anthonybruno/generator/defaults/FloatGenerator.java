package au.com.anthonybruno.generator.defaults;

import au.com.anthonybruno.generator.Generator;

import java.util.concurrent.ThreadLocalRandom;

public class FloatGenerator implements Generator<Float> {

    @Override
    public Float generate() {
        return ThreadLocalRandom.current().nextFloat();
    }
}
