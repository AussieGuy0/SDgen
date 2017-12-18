package au.com.anthonybruno.generator;

import au.com.anthonybruno.generator.format.Format;

public class FormattedGenerator<T> implements Generator<String> {

    private final Generator<T> generator;
    private final Format<T> format;

    public FormattedGenerator(Generator<T> generator, Format<T> format) {
        this.generator = generator;
        this.format = format;
    }

    @Override
    public String generate() {
        return format.format(generator.generate());
    }
}
