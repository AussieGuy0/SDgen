package au.com.anthonybruno.record;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class DefaultRecordSupplier implements RecordSupplier {


    private final List<String> fields;
    private final Supplier<Record> recordSupplier;

    public DefaultRecordSupplier(List<String> fields, Supplier<Record> recordSupplier) {
        this.fields = Collections.unmodifiableList(fields);
        this.recordSupplier = recordSupplier;

    }

    @Override
    public List<String> getFields() {
        return fields;
    }

    @Override
    public Stream<Record> supplyRecords(int numToGenerate) {
        return Stream.generate(recordSupplier).limit(numToGenerate);
    }

}
