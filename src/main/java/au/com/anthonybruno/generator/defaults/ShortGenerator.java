package au.com.anthonybruno.generator.defaults;

import au.com.anthonybruno.generator.Generator;

public class ShortGenerator implements Generator<Short> {

    private IntGenerator intGenerator = new IntGenerator(Short.MIN_VALUE, Short.MAX_VALUE);

    @Override
    public Short generate() {
        return intGenerator.generate().shortValue();
    }
}
