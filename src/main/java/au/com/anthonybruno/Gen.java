package au.com.anthonybruno;

import au.com.anthonybruno.creator.CsvFactory;
import au.com.anthonybruno.creator.FileFactory;
import au.com.anthonybruno.creator.FixedWidthFactory;
import au.com.anthonybruno.generator.Generator;
import au.com.anthonybruno.settings.CsvSettings;
import au.com.anthonybruno.settings.FixedWidthSettings;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;

public class Gen implements FileTypeDefinition, ResultDefinition, FieldDefinition {

    private static final int defaultRowsToGenerate = 5;

    private Class<?> useClass;
    private FileFactory fileFactory;

    public static FieldDefinition create() {
        return new Gen();
    }

    private Gen() {

    }


    @Override
    public FileTypeDefinition use(Class<?> c) {
        useClass = c;
        return this;
    }

    @Override
    public FileTypeDefinition addField(String name, Generator generator) {
        throw new NotImplementedException();
    }

    @Override
    public ResultDefinition asCsv() {
        return asCsv(new CsvSettings(defaultRowsToGenerate));
    }

    @Override
    public ResultDefinition asCsv(CsvSettings csvSettings) {
        fileFactory = new CsvFactory(csvSettings, useClass);
        return this;
    }

    @Override
    public ResultDefinition asFixedWidth(FixedWidthSettings fixedWidthSettings) {
        fileFactory = new FixedWidthFactory(fixedWidthSettings, useClass);
        return this;
    }

    @Override
    public File toFile(File file) {
        return fileFactory.buildFile(file);
    }

    @Override
    public String toString() {
        checkSetup();
        return fileFactory.buildString();
    }

    private void checkSetup() {
        if (useClass == null) {
            throw new IllegalStateException("No generation class specified! Please utilise the #use(Class c) method!");
        }

        if (fileFactory == null) {
            throw new IllegalStateException("No file type specified! Use one of the 'as' methods such as #asCsv()");
        }
    }


}
