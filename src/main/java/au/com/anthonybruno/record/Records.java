package au.com.anthonybruno.record;

import java.util.List;

public interface Records extends Iterable<Record> {

    List<String> getFields();

    List<Record> getRecords();


}
