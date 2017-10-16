package au.com.anthonybruno.generator.defaults;

import au.com.anthonybruno.generator.RangedGenerator;

import java.time.Instant;
import java.util.Date;

public class DateGenerator extends RangedGenerator<Date> {

    private final LongGenerator longGenerator;

    public DateGenerator(){
        this(Date.from(Instant.EPOCH),Date.from(Instant.now()));
    }

    public DateGenerator(Date min, Date max) {
        super(min, max);
        longGenerator = new LongGenerator(min.getTime(), max.getTime());
    }

    @Override
    public Date generate() {
        return Date.from(Instant.ofEpochMilli(longGenerator.generate()));
    }
}
