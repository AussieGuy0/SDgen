package au.com.anthonybruno.writer.csv;

import au.com.anthonybruno.record.Record;
import au.com.anthonybruno.settings.CsvSettings;
import com.univocity.parsers.csv.CsvWriter;
import com.univocity.parsers.csv.CsvWriterSettings;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class UnivocityCsvWriter extends AbstractCsvWriter {

    private final CsvWriter csvWriter;

    public UnivocityCsvWriter(Writer writer, CsvSettings settings) {
        super(writer, settings);
        CsvWriterSettings csvWriterSettings = new CsvWriterSettings();
        csvWriterSettings.getFormat().setDelimiter(settings.getDelimiter());
        this.csvWriter = new CsvWriter(writer, csvWriterSettings);
    }

    @Override
    public void writeRow(List<String> row) {
        for (String value : row) {
            csvWriter.addValue(value);
        }
        csvWriter.writeValuesToRow();
    }

    @Override
    public void close() {
        csvWriter.close();
    }

    @Override
    public void writeRecord(Record record) {
        List<String> values = new ArrayList<>();
        record.forEach((value) -> {
            values.add(value.toString());
        });
        writeRow(values);
    }
}
