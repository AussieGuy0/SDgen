package au.com.anthonybruno.writer.fixedwidth;

import au.com.anthonybruno.settings.FixedWidthSettings;
import au.com.anthonybruno.writer.FlatFileWriter;

import java.io.Writer;

public abstract class AbstractFixedWidthWriter extends FlatFileWriter<FixedWidthSettings> {

    public AbstractFixedWidthWriter(Writer writer, FixedWidthSettings settings) {
        super(writer, settings);
    }

}
