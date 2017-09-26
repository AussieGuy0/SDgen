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
    public String buildString() {
        FixedWidthWriterSettings writerSettings = new FixedWidthWriterSettings(settings.getFixedWidthFields());
        StringWriter stringWriter = new StringWriter();
        FixedWidthWriter fixedWidthWriter = new FixedWidthWriter(stringWriter, writerSettings);

        writeValues(fixedWidthWriter);
        return stringWriter.toString();
    }

    private void writeValues(FixedWidthWriter writer) {
        Records records = generateRecords(useClass);
        records.forEach(record -> {
            record.forEach(writer::addValue);
            writer.writeValuesToRow();
        });
    }

    @Override
    public File buildFile(File file) {
        FixedWidthWriterSettings writerSettings = new FixedWidthWriterSettings(settings.getFixedWidthFields());
        FixedWidthWriter fixedWidthWriter = new FixedWidthWriter(file, writerSettings);

        writeValues(fixedWidthWriter);
        return file;
    }
}
