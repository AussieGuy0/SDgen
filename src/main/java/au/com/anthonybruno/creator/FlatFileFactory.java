package au.com.anthonybruno.creator;

import au.com.anthonybruno.record.ClassRecordFactory;
import au.com.anthonybruno.record.Records;
import au.com.anthonybruno.settings.Settings;

public abstract class FlatFileFactory<T extends Settings> implements FileFactory  {

    protected final T settings;

    public FlatFileFactory(T settings) {
        this.settings = settings;
    }

    protected Records generateRecords(Class<?> useClass, int numToGenerate) {
        return new ClassRecordFactory(useClass).generateRecords(numToGenerate);
    }
}
