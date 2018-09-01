package au.com.anthonybruno.record.factory;

import au.com.anthonybruno.definition.FieldData;
import au.com.anthonybruno.record.DefaultRecordSupplier;
import au.com.anthonybruno.record.Record;
import au.com.anthonybruno.record.RecordSupplier;
import au.com.anthonybruno.record.RowRecord;

import java.util.List;
import java.util.stream.Collectors;

public class FieldsRecordFactory implements RecordFactory {

    private final List<FieldData> fields;

    public FieldsRecordFactory(List<FieldData> fields) {
        this.fields = fields;
    }

    @Override
    public RecordSupplier getRecordSupplier() {
        List<String> fieldNames = fields.stream()
                .map(FieldData::getName)
                .collect(Collectors.toList());

        return new DefaultRecordSupplier(fieldNames, () -> generateRecord());
    }

    private Record generateRecord() {
        List<Object> data = fields.stream()
                .map(fieldData -> fieldData.getGenerator().generate())
                .collect(Collectors.toList());
        return new RowRecord(data);
    }
}
