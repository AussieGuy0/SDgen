package au.com.anthonybruno.writer;

import au.com.anthonybruno.settings.CsvSettings;

import java.io.Writer;

public class WriterFactory {

    public static AbstractCsvWriter getDefaultCsvWriter(Writer writer, CsvSettings csvSettings) {
        return new UnivocityCsvWriter(writer, csvSettings);
    }
}
