package au.com.anthonybruno.creator;

import au.com.anthonybruno.record.Records;

public interface FileFactory {

    Records generateRecords(Class<?> toUse, int numToGenerate);

    String getAsString();
}
