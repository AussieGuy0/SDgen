package au.com.anthonybruno.record;

import java.util.List;
import java.util.stream.Stream;

public interface RecordSupplier {

    List<String> getFields();

    Stream<Record> supplyRecords(int numToGenerate);

}
