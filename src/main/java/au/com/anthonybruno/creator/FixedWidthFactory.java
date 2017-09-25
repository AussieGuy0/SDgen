package au.com.anthonybruno.creator;

import au.com.anthonybruno.record.Records;
import au.com.anthonybruno.settings.FixedWidthSettings;
import com.univocity.parsers.fixed.FixedWidthWriter;
import com.univocity.parsers.fixed.FixedWidthWriterSettings;

import java.io.StringWriter;

public class FixedWidthFactory extends FlatFileFactory<FixedWidthSettings> {

    private final Class<?> useClass;

    public FixedWidthFactory(FixedWidthSettings settings, Class<?> c) {
        super(settings);
        this.useClass = c;
    }

    @Override
    public String getAsString() {
        Records records = generateRecords(useClass, settings.getRows());

        StringWriter stringWriter = new StringWriter();
        FixedWidthWriterSettings writerSettings = new FixedWidthWriterSettings(settings.getFixedWidthFields());
        FixedWidthWriter fixedWidthWriter = new FixedWidthWriter(stringWriter, writerSettings);
        records.forEach(record -> {
            record.forEach(fixedWidthWriter::addValue);
            fixedWidthWriter.writeValuesToRow();
        });
        return stringWriter.toString();
    }
}
