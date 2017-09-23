package au.com.anthonybruno.record;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class DefaultRecords implements Records {


    private final List<String> fields;
    private final List<Record> records;

    public DefaultRecords(List<String> fields, List<Record> records) {
        this.fields = fields;
        this.records = records;

    }

    @Override
    public List<String> getFields() {
        return Collections.unmodifiableList(fields);
    }

    @Override
    public List<Record> getRecords() {
        return Collections.unmodifiableList(records);
    }

    @Override
    public Iterator<Record> iterator() {
        return getRecords().iterator();
    }
}
