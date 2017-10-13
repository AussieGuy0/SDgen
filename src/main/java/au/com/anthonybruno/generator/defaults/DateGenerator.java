package au.com.anthonybruno.generator.defaults;

import au.com.anthonybruno.generator.RangedGenerator;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class DateGenerator extends RangedGenerator<Date> {

    public DateGenerator(){
        super(Date.from(Instant.EPOCH),Date.from(Instant.now()));
    }

    public DateGenerator(Date min, Date max) {
        super(min, max);
    }

    @Override
    public Date generate() {
        return Date.from(Instant.ofEpochMilli(ThreadLocalRandom.current().nextLong(min.getTime(),max.getTime())));
    }
}
