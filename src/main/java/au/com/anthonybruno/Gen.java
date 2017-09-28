package au.com.anthonybruno;

import au.com.anthonybruno.creator.CsvFactory;
import au.com.anthonybruno.creator.FileFactory;
import au.com.anthonybruno.creator.FixedWidthFactory;
import au.com.anthonybruno.defintion.FieldDefinition;
import au.com.anthonybruno.defintion.FileTypeDefinition;
import au.com.anthonybruno.defintion.RecordDefinition;
import au.com.anthonybruno.defintion.ResultDefinition;
import au.com.anthonybruno.generator.Generator;
import au.com.anthonybruno.settings.CsvSettings;
import au.com.anthonybruno.settings.FixedWidthSettings;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;

public class Gen implements FileTypeDefinition, ResultDefinition, FieldDefinition, RecordDefinition {

    private static final int defaultRowsToGenerate = 5;

    private Class<?> useClass;
    private int numToGenerate;
    private FileFactory fileFactory;

    public static FieldDefinition start() {
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
    public RecordDefinition asCsv() {
        return asCsv(new CsvSettings());
    }

    @Override
    public RecordDefinition asCsv(CsvSettings csvSettings) {
        fileFactory = new CsvFactory(csvSettings, useClass);
        return this;
    }

    @Override
    public RecordDefinition asFixedWidth(FixedWidthSettings fixedWidthSettings) {
        fileFactory = new FixedWidthFactory(fixedWidthSettings, useClass);
        return this;
    }

    @Override
    public File toFile(File file) {
        return fileFactory.createFile(file, numToGenerate);
    }

    @Override
    public String toStringForm() {
        checkSetup();
        return fileFactory.createString(numToGenerate);
    }

    private void checkSetup() {
        if (useClass == null) {
            throw new IllegalStateException("No generation class specified! Please utilise the #use(Class c) method!");
        }

        if (fileFactory == null) {
            throw new IllegalStateException("No file type specified! Use one of the 'as' methods such as #asCsv()");
        }
    }


    @Override
    public ResultDefinition generate(int num) {
        this.numToGenerate = num;
        return this;
    }
}
