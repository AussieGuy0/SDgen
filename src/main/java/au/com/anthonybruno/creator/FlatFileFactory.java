package au.com.anthonybruno.creator;

import au.com.anthonybruno.generator.GeneratedValue;
import au.com.anthonybruno.record.DefaultRecords;
import au.com.anthonybruno.record.Record;
import au.com.anthonybruno.record.Records;
import au.com.anthonybruno.record.RowRecord;
import au.com.anthonybruno.settings.Settings;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class FlatFileFactory<T extends Settings> implements FileFactory  {

    protected final T settings;

    public FlatFileFactory(T settings) {
        this.settings = settings;
    }

    @Override
    public Records generateRecords(Class<?> c, int numOfRecords) {
        Field[] fields = c.getDeclaredFields();
        List<String> fieldNames = new ArrayList<>();
        for (Field field : fields) {
            fieldNames.add(field.getName());
        }

        List<Record> records = new ArrayList<>();
        for (int i = 0; i < numOfRecords; i++) {
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
