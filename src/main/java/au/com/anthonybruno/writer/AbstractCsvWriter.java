package au.com.anthonybruno.writer;


import au.com.anthonybruno.settings.CsvSettings;

import java.io.Writer;

public abstract class AbstractCsvWriter extends FlatFileWriter<CsvSettings> {

    public AbstractCsvWriter(Writer writer, CsvSettings settings) {
        super(writer, settings);
    }
}
