package au.com.anthonybruno.record;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Iterator;
import java.util.List;

public class RowRecord implements Record {

    private final List<Object> rows;

    public RowRecord(List<Object> rows) {
        this.rows = rows;
    }

    @Override
    public Object get(int index) {
        return rows.get(index);
    }

    @Override
    public Object get(String fieldName) {
        throw new NotImplementedException();
    }

    @Override
    public Iterator<Object> iterator() {
        return rows.iterator();
    }
}
