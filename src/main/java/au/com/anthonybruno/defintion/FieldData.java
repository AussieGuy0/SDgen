package au.com.anthonybruno.defintion;

import au.com.anthonybruno.generator.Generator;

public class FieldData {

    private final String name;
    private final Generator generator;

    public FieldData(String name, Generator generator) {
        this.name = name;
        this.generator = generator;
    }

    public String getName() {
        return name;
    }

    public Generator getGenerator() {
        return generator;
    }
}
