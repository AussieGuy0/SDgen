package au.com.anthonybruno.definition;

import au.com.anthonybruno.generator.Generator;

public class FieldData<T> {

    private final String name;
    private final Generator<T> generator;

    public FieldData(String name, Generator<T> generator) {
        this.name = name;
        this.generator = generator;
    }

    public String getName() {
        return name;
    }

    public Generator<T> getGenerator() {
        return generator;
    }
}
