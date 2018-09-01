package au.com.anthonybruno.writer;

import au.com.anthonybruno.record.Record;

public interface AbstractWriter extends AutoCloseable {

    void writeRecord(Record record);

    @Override
    void close();
}
