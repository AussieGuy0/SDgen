package au.com.anthonybruno.creator;

import au.com.anthonybruno.record.Records;
import au.com.anthonybruno.record.factory.RecordFactory;
import au.com.anthonybruno.settings.CsvSettings;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;

import java.io.File;
import java.io.StringWriter;

public class CsvFactory extends FlatFileFactory<CsvSettings> {

    public CsvFactory(CsvSettings settings, RecordFactory recordFactory) {
        super(settings, recordFactory);
    }

    @Override
    public String createString(int rowsToGenerate) {
        StringWriter stringWriter = new StringWriter();
        CsvWriter csvWriter = new CsvWriter(stringWriter, new CsvWriterSettings());
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
        CsvWriter csvWriter = new CsvWriter(file, new CsvWriterSettings());
        writeValues(csvWriter, rowsToGenerate);
        return file;
    }
}
