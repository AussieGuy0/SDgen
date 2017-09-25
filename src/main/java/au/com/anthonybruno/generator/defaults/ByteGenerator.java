package au.com.anthonybruno.generator.defaults;

import au.com.anthonybruno.generator.Generator;

import java.util.concurrent.ThreadLocalRandom;

public class ByteGenerator implements Generator<Byte> {

    @Override
    public Byte generate() {
        byte[] bytes = new byte[1];
        ThreadLocalRandom.current().nextBytes(bytes);
        return bytes[0];
    }
}
