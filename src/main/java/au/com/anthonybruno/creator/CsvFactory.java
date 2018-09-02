package au.com.anthonybruno.creator;

import au.com.anthonybruno.record.RecordSupplier;
import au.com.anthonybruno.record.factory.RecordFactory;
import au.com.anthonybruno.settings.CsvSettings;
import au.com.anthonybruno.utils.TextFile;
import au.com.anthonybruno.writer.WriterFactory;
import au.com.anthonybruno.writer.csv.AbstractCsvWriter;

import java.io.File;
import java.io.StringWriter;

public class CsvFactory extends FlatFileFactory<CsvSettings> {


    public CsvFactory(CsvSettings settings, RecordFactory recordFactory) {
        super(settings, recordFactory);
    }

    @Override
    public String createString(int rowsToGenerate) {
        StringWriter stringWriter = new StringWriter();
        try (AbstractCsvWriter csvWriter = WriterFactory.getDefaultCsvWriter(stringWriter, settings)) {
            writeValues(csvWriter, rowsToGenerate);
        }
        return stringWriter.toString();
    }

    private void writeValues(AbstractCsvWriter writer, int rowsToGenerate) {
        RecordSupplier records = recordFactory.getRecordSupplier();
        if (settings.isIncludingHeaders()) {
            writer.writeRow(records.getFields());
        }
        records.supplyRecords(rowsToGenerate)
                .forEach(record -> {
                    writer.writeRecord(record);
                });
    }

    @Override
    public File createFile(File file, int rowsToGenerate) {
        try (AbstractCsvWriter csvWriter = WriterFactory.getDefaultCsvWriter(new TextFile(file).getWriter(), settings)) {
            writeValues(csvWriter, rowsToGenerate);
        }
        return file;
    }

}
