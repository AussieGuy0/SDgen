package au.com.anthonybruno.record.factory;

import au.com.anthonybruno.generator.GeneratedValue;
import au.com.anthonybruno.record.DefaultRecords;
import au.com.anthonybruno.record.Record;
import au.com.anthonybruno.record.Records;
import au.com.anthonybruno.record.RowRecord;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ClassRecordFactory implements RecordFactory {

    private final Class<?> classToUse;

    public ClassRecordFactory(Class<?> classToUse) {
        this.classToUse = classToUse;
    }

    @Override
    public Records generateRecords(int numToGenerate) {
        Field[] fields = classToUse.getDeclaredFields();
        List<String> fieldNames = new ArrayList<>();
        for (Field field : fields) {
            fieldNames.add(field.getName());
        }

        List<Record> records = new ArrayList<>();
        for (int i = 0; i < numToGenerate; i++) {
            records.add(generateRecord(fields));

        }

        return new DefaultRecords(fieldNames, records);
    }

    private Record generateRecord(Field[] fields) {
        List<Object> list = new ArrayList<>();
        for (Field field : fields) {
            Object value = new GeneratedValue<>(field.getType()).get();
            list.add(value);
        }
        return new RowRecord(list);
    }
}
