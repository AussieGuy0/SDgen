package au.com.anthonybruno.record.factory;

import au.com.anthonybruno.record.Records;

public interface RecordFactory {

    Records generateRecords(int numToGenerate);
}
