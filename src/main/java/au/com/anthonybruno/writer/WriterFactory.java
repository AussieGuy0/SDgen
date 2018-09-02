package au.com.anthonybruno.writer;

import au.com.anthonybruno.settings.CsvSettings;
import au.com.anthonybruno.settings.FixedWidthSettings;
import au.com.anthonybruno.writer.csv.AbstractCsvWriter;
import au.com.anthonybruno.writer.csv.UnivocityCsvWriter;
import au.com.anthonybruno.writer.fixedwidth.AbstractFixedWidthWriter;
import au.com.anthonybruno.writer.fixedwidth.UnivocityFixedWidthWriter;

import java.io.Writer;

public class WriterFactory {

    public static AbstractCsvWriter getDefaultCsvWriter(Writer writer, CsvSettings csvSettings) {
        return new UnivocityCsvWriter(writer, csvSettings);
    }

    public static AbstractFixedWidthWriter getDefaultFixedWidthWriter(Writer writer, FixedWidthSettings settings) {
        return new UnivocityFixedWidthWriter(writer, settings);
    }
}
