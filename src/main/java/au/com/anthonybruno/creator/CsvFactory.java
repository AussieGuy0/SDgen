package au.com.anthonybruno.creator;

import au.com.anthonybruno.record.Records;
import au.com.anthonybruno.settings.CsvSettings;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;

import java.io.StringWriter;

public class CsvFactory extends FlatFileFactory<CsvSettings> {

    private final Class<?> useClass;

    public CsvFactory(CsvSettings settings, Class<?> useClass) {
        super(settings);
        this.useClass = useClass;
    }

    @Override
    public String getAsString() {
        Records records = generateRecords(useClass, settings.getRows());

        StringWriter stringWriter = new StringWriter();
        CsvWriter csvWriter = new CsvWriter(stringWriter, new CsvWriterSettings()); //TODO: Move elsewhere
        records.forEach(record -> {
            record.forEach(csvWriter::addValue);
            csvWriter.writeValuesToRow();
        });
        return stringWriter.toString();
    }
}
