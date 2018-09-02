package au.com.anthonybruno.writer.csv;


import au.com.anthonybruno.settings.CsvSettings;
import au.com.anthonybruno.writer.FlatFileWriter;

import java.io.Writer;

public abstract class AbstractCsvWriter extends FlatFileWriter<CsvSettings> {

    public AbstractCsvWriter(Writer writer, CsvSettings settings) {
        super(writer, settings);
    }
}
