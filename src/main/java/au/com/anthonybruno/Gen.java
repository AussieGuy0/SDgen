package au.com.anthonybruno;

import au.com.anthonybruno.generator.IntGenerator;
import au.com.anthonybruno.generator.StringGenerator;
import au.com.anthonybruno.record.DefaultRecords;
import au.com.anthonybruno.record.Record;
import au.com.anthonybruno.record.Records;
import au.com.anthonybruno.record.RowRecord;
import au.com.anthonybruno.settings.CsvSettings;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Gen implements FileTypeDefinition, ResultDefinition {

    private Class<?> useClass;

    public Gen() {

    }

    public Gen use(Class<?> c) {
        useClass = c;
        return this;
    }

    @Override
    public ResultDefinition asCsv() {
        return asCsv(new CsvSettings(5));
    }

    @Override
    public ResultDefinition asCsv(CsvSettings csvSettings) {
        return this;
    }

    @Override
    public File toFile(String filepath) {
        return null;
    }

    @Override
    public String toString() {
        Records records = generateRecords(useClass, 5);

        StringWriter stringWriter = new StringWriter();
        CsvWriter csvWriter = new CsvWriter(stringWriter, new CsvWriterSettings()); //TODO: Move elsewhere
        records.forEach(record -> {
            record.forEach(csvWriter::addValue);
            csvWriter.writeValuesToRow();
        });
        return stringWriter.toString();
    }

    private Records generateRecords(Class<?> c, int numOfRecords) {
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
            list.add(generateValue(field));
        }
        return new RowRecord(list);
    }

    private Object generateValue(Field field) {
        Object value;
        if (field.getType().equals(String.class)) {
            value = new StringGenerator().generate();

        } else if (field.getType().equals(int.class)) {
            value = new IntGenerator().generate();
        } else {
            throw new NotImplementedException();
        }
        return value;
    }
}
