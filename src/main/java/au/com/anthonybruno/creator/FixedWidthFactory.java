package au.com.anthonybruno.creator;

import au.com.anthonybruno.record.RecordSupplier;
import au.com.anthonybruno.record.factory.RecordFactory;
import au.com.anthonybruno.settings.FixedWidthSettings;
import com.univocity.parsers.fixed.FixedWidthWriter;
import com.univocity.parsers.fixed.FixedWidthWriterSettings;

import java.io.File;
import java.io.StringWriter;

public class FixedWidthFactory extends FlatFileFactory<FixedWidthSettings> {

    public FixedWidthFactory(FixedWidthSettings settings, RecordFactory recordFactory) {
        super(settings, recordFactory);
    }

    @Override
    public String createString(int numToGenerate) {
        FixedWidthWriterSettings writerSettings = new FixedWidthWriterSettings(settings.getFixedWidthFields());
        StringWriter stringWriter = new StringWriter();
        FixedWidthWriter fixedWidthWriter = new FixedWidthWriter(stringWriter, writerSettings);

        generateAndWriteValues(fixedWidthWriter, numToGenerate);
        return stringWriter.toString();
    }


    private void generateAndWriteValues(FixedWidthWriter writer, int numToGenerate) {
        RecordSupplier recordSupplier = recordFactory.getRecordSupplier();
        if (settings.isIncludingHeaders()) {
            recordSupplier.getFields().forEach(writer::addValue);
            writer.writeValuesToRow();
        }
        recordSupplier.supplyRecords()
                .limit(numToGenerate)
                .forEach(record -> {
                    record.forEach(writer::addValue);
                    writer.writeValuesToRow();
                });
        writer.close();
    }

    @Override
    public File createFile(File file, int numToGenerate) {
        FixedWidthWriterSettings writerSettings = new FixedWidthWriterSettings(settings.getFixedWidthFields());
        FixedWidthWriter fixedWidthWriter = new FixedWidthWriter(file, writerSettings);

        generateAndWriteValues(fixedWidthWriter, numToGenerate);
        return file;
    }
}
