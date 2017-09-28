package au.com.anthonybruno.creator;

import au.com.anthonybruno.record.factory.RecordFactory;
import au.com.anthonybruno.settings.FlatFileSettings;

public abstract class FlatFileFactory<T extends FlatFileSettings> implements FileFactory  {

    protected final T settings;
    protected final RecordFactory recordFactory;

    public FlatFileFactory(T settings, RecordFactory recordFactory) {
        if (settings == null) {
            throw new IllegalArgumentException("Settings cannot be null!");
        }

        if (recordFactory == null) {
            throw new IllegalArgumentException("recordFactory cannot be null!");
        }
        this.settings = settings;
        this.recordFactory = recordFactory;
    }

}
