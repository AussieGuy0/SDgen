package au.com.anthonybruno.generator;

import au.com.anthonybruno.generator.defaults.IntGenerator;

public class OptionGenerator<T> implements Generator<T> {

    private final T[] options;
    private final IntGenerator indexGenerator;

    public OptionGenerator(T... options)  {
        this.options = options;
        this.indexGenerator = new IntGenerator(0, options.length);
    }


    @Override
    public T generate() {
        return options[indexGenerator.generate()];
    }
}
