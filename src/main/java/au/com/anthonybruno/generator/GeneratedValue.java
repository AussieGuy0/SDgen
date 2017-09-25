package au.com.anthonybruno.generator;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class GeneratedValue<T> {

    private final Class<T> type;

    public GeneratedValue(Class<T> type) {
        this.type = type;
    }

    public T get() {
        T out;
        if (type.equals(String.class)) {
            out = (T) new StringGenerator().generate();
        } else if (type.equals(int.class)) {
            out = (T) new IntGenerator().generate();
        } else {
            throw new NotImplementedException();
        }
        return out;
    }
}
