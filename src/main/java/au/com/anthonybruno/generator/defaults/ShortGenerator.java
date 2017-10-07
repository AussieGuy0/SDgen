package au.com.anthonybruno.generator.defaults;

import au.com.anthonybruno.generator.RangedGenerator;

public class ShortGenerator extends RangedGenerator<Short> {

    private final IntGenerator intGenerator;

    public ShortGenerator() {
        this(Short.MIN_VALUE, Short.MAX_VALUE);
    }

    public ShortGenerator(Short min, Short max) {
        super(min, max);
        intGenerator = new IntGenerator(min, max);
    }

    @Override
    public Short generate() {
        return intGenerator.generate().shortValue();
    }
}
