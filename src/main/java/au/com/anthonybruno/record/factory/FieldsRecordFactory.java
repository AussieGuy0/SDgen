package au.com.anthonybruno.record.factory;

import au.com.anthonybruno.definition.FieldGenerator;
import au.com.anthonybruno.generator.Context;
import au.com.anthonybruno.record.DefaultRecordSupplier;
import au.com.anthonybruno.record.Record;
import au.com.anthonybruno.record.RecordSupplier;
import au.com.anthonybruno.record.RowRecord;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FieldsRecordFactory implements RecordFactory {

    private final List<FieldGenerator<?>> fields;
    private final Map<String, FieldGenerator<?>> fieldsMap;

    public FieldsRecordFactory(List<FieldGenerator<?>> fields) {
        this.fields = fields;
        this.fieldsMap = new HashMap<>();
        for (FieldGenerator<?> fieldGenerator : fields) {
            fieldsMap.put(fieldGenerator.getName(), fieldGenerator);
        }
    }

    @Override
    public RecordSupplier getRecordSupplier() {
        List<String> fieldNames = fields.stream()
                .map(FieldGenerator::getName)
                .collect(Collectors.toList());

        return new DefaultRecordSupplier(fieldNames, this::generateRecord);
    }

    private Record generateRecord() {
        Map<String, Object> recordValues = new HashMap<>();
        Context context = new DefaultContext(recordValues);
        List<Object> row = new ArrayList<>();
        for (FieldGenerator<?> fieldGenerator : fields) {
            Object value;
            String name = fieldGenerator.getName();
            if (recordValues.containsKey(name)) {
                value = recordValues.get(name);
            } else {
                value = fieldGenerator.generateValue(context);
                recordValues.put(name, value);
            }

            row.add(value);
        }
        return new RowRecord(row);
    }

    private class DefaultContext implements Context  {

        private final Map<String, Object> recordValues;

        private DefaultContext(Map<String, Object> recordValues) {
            this.recordValues = recordValues;
        }


        @Override
        public <T> T getFieldValue(String fieldName, Class<T> clazz) {
            Object value;
            if (recordValues.containsKey(fieldName)) {
                value = recordValues.get(fieldName);
            } else {
                FieldGenerator<?> fieldGenerator = fieldsMap.get(fieldName);
                if (fieldGenerator == null) {
                    throw new RuntimeException("Cannot access field " + fieldName + " as it was not defined!");
                }
                value = fieldGenerator.generateValue(this);
                recordValues.put(fieldName, value);
            }
            return (T) value;
        }
    }
}
