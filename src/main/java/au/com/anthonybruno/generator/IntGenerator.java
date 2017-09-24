package au.com.anthonybruno.generator;

import java.util.concurrent.ThreadLocalRandom;

public class IntGenerator implements Generator<Integer> {

    @Override
    public Integer generate() {
        return ThreadLocalRandom.current().nextInt();
    }
}
