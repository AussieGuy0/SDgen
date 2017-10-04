package au.com.anthonybruno.record.factory;

import au.com.anthonybruno.definition.FieldData;
import au.com.anthonybruno.record.DefaultRecords;
import au.com.anthonybruno.record.Record;
import au.com.anthonybruno.record.Records;
import au.com.anthonybruno.record.RowRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FieldsRecordFactory implements RecordFactory {

    private final List<FieldData> fields;

    public FieldsRecordFactory(List<FieldData> fields) {
        this.fields = fields;
    }

    @Override
    public Records generateRecords(int numToGenerate) {
        List<Record> records = new ArrayList<>();
        for (int i = 0; i < numToGenerate; i++) {
            List<Object> data = fields.stream()
                    .map(fieldData -> fieldData.getGenerator().generate())
                    .collect(Collectors.toList());
            records.add(new RowRecord(data));
        }

        List<String> fieldNames = fields.stream()
                .map(FieldData::getName)
                .collect(Collectors.toList());
        return new DefaultRecords(fieldNames, records);
    }
}
