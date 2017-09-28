package au.com.anthonybruno.creator;

import au.com.anthonybruno.record.Records;
import au.com.anthonybruno.settings.FixedWidthSettings;
import com.univocity.parsers.fixed.FixedWidthWriter;
import com.univocity.parsers.fixed.FixedWidthWriterSettings;

import java.io.File;
import java.io.StringWriter;

public class FixedWidthFactory extends FlatFileFactory<FixedWidthSettings> {

    private final Class<?> useClass;

    public FixedWidthFactory(FixedWidthSettings settings, Class<?> c) {
        super(settings);
        this.useClass = c;
    }

    @Override
    public String createString(int numToGenerate) {
        FixedWidthWriterSettings writerSettings = new FixedWidthWriterSettings(settings.getFixedWidthFields());
        StringWriter stringWriter = new StringWriter();
        FixedWidthWriter fixedWidthWriter = new FixedWidthWriter(stringWriter, writerSettings);

        writeValues(fixedWidthWriter, numToGenerate);
        return stringWriter.toString();
    }


    private void writeValues(FixedWidthWriter writer, int numToGenerate) {
        Records records = generateRecords(useClass, numToGenerate);
        records.forEach(record -> {
            record.forEach(writer::addValue);
            writer.writeValuesToRow();
        });
    }

    @Override
    public File createFile(File file, int numToGenerate) {
        FixedWidthWriterSettings writerSettings = new FixedWidthWriterSettings(settings.getFixedWidthFields());
        FixedWidthWriter fixedWidthWriter = new FixedWidthWriter(file, writerSettings);

        writeValues(fixedWidthWriter, numToGenerate);
        return file;
    }
}
