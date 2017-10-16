package au.com.anthonybruno.record.factory;

import au.com.anthonybruno.annotation.Generation;
import au.com.anthonybruno.annotation.Range;
import au.com.anthonybruno.generator.DefaultGenerators;
import au.com.anthonybruno.generator.Generator;
import au.com.anthonybruno.generator.RangedGenerator;
import au.com.anthonybruno.record.DefaultRecords;
import au.com.anthonybruno.record.Record;
import au.com.anthonybruno.record.Records;
import au.com.anthonybruno.record.RowRecord;
import au.com.anthonybruno.utils.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassRecordFactory implements RecordFactory {

    private final Class<?> classToUse;
    private final Map<Field, Generator> fieldGeneratorMap = new HashMap<>();

    public ClassRecordFactory(Class<?> classToUse) {
        this.classToUse = classToUse;
    }

    @Override
    public Records generateRecords(int numToGenerate) {
        Field[] fields = classToUse.getDeclaredFields();
        List<String> fieldNames = new ArrayList<>();
        for (Field field : fields) {
            fieldNames.add(field.getName());
        }

        List<Record> records = new ArrayList<>();
        for (int i = 0; i < numToGenerate; i++) {
            records.add(generateRecord(fields));

        }

        return new DefaultRecords(fieldNames, records);
    }

    private Record generateRecord(Field[] fields) {
        List<Object> list = new ArrayList<>();
        for (Field field : fields) {
            list.add(generateValue(field));
        }
        return new RowRecord(list);
    }

    private Object generateValue(Field field) {
        Generator<?> generator;
        if ((generator = fieldGeneratorMap.get(field)) == null) {
            Generator<?> canidiateGenerator;
            Generation generatorAnnotation = field.getAnnotation(Generation.class);
            if (generatorAnnotation != null) {
                canidiateGenerator = ReflectionUtils.buildWithNoArgConstructor(generatorAnnotation.value());
            } else {
                canidiateGenerator = DefaultGenerators.get(field.getType());
            }
            Range range = field.getAnnotation(Range.class);
            if (range != null && canidiateGenerator instanceof RangedGenerator<?>) {
                generator = ReflectionUtils.buildWithConstructor(canidiateGenerator.getClass(), range.min(), range.max());
            } else {
                generator = canidiateGenerator;
            }
            fieldGeneratorMap.put(field, generator);
        }

        return generator.generate();


    }
}
