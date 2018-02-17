package au.com.anthonybruno;

import au.com.anthonybruno.creator.CsvFactory;
import au.com.anthonybruno.creator.FileFactory;
import au.com.anthonybruno.creator.FixedWidthFactory;
import au.com.anthonybruno.definition.*;
import au.com.anthonybruno.generator.Generator;
import au.com.anthonybruno.record.factory.ClassRecordFactory;
import au.com.anthonybruno.record.factory.FieldsRecordFactory;
import au.com.anthonybruno.record.factory.RecordFactory;
import au.com.anthonybruno.settings.CsvSettings;
import au.com.anthonybruno.settings.FixedWidthSettings;
import au.com.anthonybruno.utils.ArgumentUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Gen implements FileTypeDefinition, ResultDefinition, StartDefinition, RecordDefinition, FieldDefinition {

    private int numToGenerate;
    private FileFactory fileFactory;

    private Class<?> useClass;
    private List<FieldData> fields = new ArrayList<>();

    public static StartDefinition start() {
        return new Gen();
    }

    private Gen() {

    }


    @Override
    public RecordDefinition use(Class<?> c) {
        useClass = c;
        return this;
    }

    @Override
    public FieldDefinition addField(String name, Generator generator) {
        ArgumentUtils.isNotNull(generator, "Can not add '" + name + "' field with null generator");
        fields.add(new FieldData(name, generator));
        return this;
    }

    @Override
    public ResultDefinition asCsv() {
        return asCsv(new CsvSettings.Builder().build());
    }

    @Override
    public ResultDefinition asCsv(CsvSettings csvSettings) {
        fileFactory = new CsvFactory(csvSettings, createRecordFactory());
        return this;
    }

    @Override
    public ResultDefinition asFixedWidth(FixedWidthSettings fixedWidthSettings) {
        fileFactory = new FixedWidthFactory(fixedWidthSettings, createRecordFactory());
        return this;
    }

    @Override
    public File toFile(File file) {
        return fileFactory.createFile(file, numToGenerate);
    }

    @Override
    public File toFile(String path) {
        return toFile(new File(path));
    }

    @Override
    public String toStringForm() {
        checkSetup();
        return fileFactory.createString(numToGenerate);
    }

    @Override
    public FileTypeDefinition generate(int num) {
        this.numToGenerate = num;
        return this;
    }

    private RecordFactory createRecordFactory() {
        if (useClass != null) {
            return new ClassRecordFactory(useClass);
        } else if (!fields.isEmpty()){
            return new FieldsRecordFactory(fields);
        } else {
            throw new IllegalStateException("No fields or class added to generator");
        }
    }

    private void checkSetup() {
        if (useClass == null && fields.isEmpty()) {
            throw new IllegalStateException("No generation fields or class specified! Please utilise the #use or #addField methods!");
        }

        if (fileFactory == null) {
            throw new IllegalStateException("No file type specified! Use one of the 'as' methods such as #asCsv()");
        }

        if (numToGenerate <= 0) {
            throw new IllegalStateException("Need to specify a positive non-zero number of records to generate!");
        }
    }


}
