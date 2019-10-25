package au.com.anthonybruno.definition;

import au.com.anthonybruno.generator.Context;
import au.com.anthonybruno.generator.ContextGenerator;
import au.com.anthonybruno.generator.Generator;

public class FieldGenerator<T> {

    private final String name;
    private final ContextGenerator<T> generator;

    public FieldGenerator(String name, Generator<T> generator) {
        this(name, (context) -> generator.generate());
    }

    public FieldGenerator(String name, ContextGenerator<T> generator) {
        this.name = name;
        this.generator = generator;
    }

    public String getName() {
        return name;
    }

    public T generateValue(Context context) {
        return generator.generate(context);
    }

}
