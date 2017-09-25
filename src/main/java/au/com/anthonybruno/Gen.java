package au.com.anthonybruno;

import au.com.anthonybruno.creator.CsvFactory;
import au.com.anthonybruno.creator.FileFactory;
import au.com.anthonybruno.creator.FixedWidthFactory;
import au.com.anthonybruno.settings.CsvSettings;
import au.com.anthonybruno.settings.FixedWidthSettings;

import java.io.File;

public class Gen implements FileTypeDefinition, ResultDefinition {

    private static final int defaultRowsToGenerate = 5;

    private Class<?> useClass;
    private FileFactory fileFactory;

    public Gen() {

    }

    public FileTypeDefinition use(Class<?> c) {
        useClass = c;
        return this;
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
    public File toFile(String filepath) {
        return null;
    }

    @Override
    public String toString() {
        checkSetup();
        return fileFactory.getAsString();
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
