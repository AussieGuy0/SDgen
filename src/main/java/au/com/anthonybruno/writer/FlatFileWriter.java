package au.com.anthonybruno.writer;

import au.com.anthonybruno.settings.FlatFileSettings;

import java.io.Writer;
import java.util.List;

public abstract class FlatFileWriter<T extends FlatFileSettings> implements AbstractWriter {

    private final Writer writer;
    protected final T settings;

    public FlatFileWriter(Writer writer, T settings) {
        this.writer = writer;
        this.settings = settings;
    }


    public abstract void writeRow(List<String> row);



}
