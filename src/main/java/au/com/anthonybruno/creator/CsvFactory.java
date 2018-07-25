package au.com.anthonybruno.creator;

import au.com.anthonybruno.record.Records;
import au.com.anthonybruno.record.factory.RecordFactory;
import au.com.anthonybruno.settings.CsvSettings;
import au.com.anthonybruno.writer.AbstractCsvWriter;
import au.com.anthonybruno.writer.WriterFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        Records records = recordFactory.generateRecords(rowsToGenerate);
        if (settings.isIncludingHeaders()) {
            writer.writeRow(records.getFields());
        }
        records.forEach(record -> {
            writer.writeRecord(record);
        });
    }

    @Override
    public File createFile(File file, int rowsToGenerate) {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (AbstractCsvWriter csvWriter = WriterFactory.getDefaultCsvWriter(fileWriter, settings)) {
            writeValues(csvWriter, rowsToGenerate);
        }
        return file;
    }

}
