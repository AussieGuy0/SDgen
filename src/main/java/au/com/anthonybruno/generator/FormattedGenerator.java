package au.com.anthonybruno.generator;

import au.com.anthonybruno.generator.format.Formatter;

public class FormattedGenerator<T> implements Generator<String> {

    private final Generator<T> generator;
    private final Formatter<T> formatter;

    public FormattedGenerator(Generator<T> generator, Formatter<T> formatter) {
        this.generator = generator;
        this.formatter = formatter;
    }

    @Override
    public String generate() {
        return formatter.format(generator.generate());
    }
}
