package au.com.anthonybruno.creator;

import au.com.anthonybruno.record.Records;
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

        writeValues(fixedWidthWriter, numToGenerate);
        return stringWriter.toString();
    }


    private void writeValues(FixedWidthWriter writer, int numToGenerate) {
        Records records = recordFactory.generateRecords(numToGenerate);
        if (settings.isIncludingHeaders()) {
            records.getFields().forEach(writer::addValue);
            writer.writeValuesToRow();
        }
        records.forEach(record -> {
            record.forEach(writer::addValue);
            writer.writeValuesToRow();
        });
        writer.close();
    }

    @Override
    public File createFile(File file, int numToGenerate) {
        FixedWidthWriterSettings writerSettings = new FixedWidthWriterSettings(settings.getFixedWidthFields());
        FixedWidthWriter fixedWidthWriter = new FixedWidthWriter(file, writerSettings);

        writeValues(fixedWidthWriter, numToGenerate);
        return file;
    }
}
