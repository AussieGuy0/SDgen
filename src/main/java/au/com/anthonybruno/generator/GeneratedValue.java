package au.com.anthonybruno.generator;

public class GeneratedValue<T> {

    private final Class<T> type;
    private T value;


    public GeneratedValue(Class<T> type) {
        this.type = type;
    }

    public T get() {
        if (value == null) {
            Generator<?> generator = DefaultGenerators.get(type);
            if (generator == null) {
                throw new RuntimeException("No generator for type: " + type);
            }
            value = (T) generator.generate();
        }
        return value;
    }

}
