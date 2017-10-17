package au.com.anthonybruno.creator;

import au.com.anthonybruno.record.Records;
import au.com.anthonybruno.record.factory.RecordFactory;
import au.com.anthonybruno.settings.CsvSettings;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;

import java.io.File;
import java.io.StringWriter;

public class CsvFactory extends FlatFileFactory<CsvSettings> {

    private final CsvWriterSettings csvWriterSettings;

    public CsvFactory(CsvSettings settings, RecordFactory recordFactory) {
        super(settings, recordFactory);
        csvWriterSettings = new CsvWriterSettings();

        csvWriterSettings.getFormat().setDelimiter(settings.getDelimiter());
    }

    @Override
    public String createString(int rowsToGenerate) {
        StringWriter stringWriter = new StringWriter();
        CsvWriter csvWriter = new CsvWriter(stringWriter, csvWriterSettings);
        writeValues(csvWriter, rowsToGenerate);
        return stringWriter.toString();
    }

    private void writeValues(CsvWriter writer, int rowsToGenerate) {
        Records records = recordFactory.generateRecords(rowsToGenerate);
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
    public File createFile(File file, int rowsToGenerate) {
        CsvWriter csvWriter = new CsvWriter(file, csvWriterSettings);
        writeValues(csvWriter, rowsToGenerate);
        return file;
    }
}
