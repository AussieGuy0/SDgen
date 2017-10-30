package au.com.anthonybruno.record.factory;

import au.com.anthonybruno.annotation.Generation;
import au.com.anthonybruno.annotation.Index;
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
        Field[] fields = getFields();
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

    private Field[] getFields() {
        Field[] fields = classToUse.getDeclaredFields();
        Field[] orderedFields = new Field[fields.length];
        List<Field> leftoverFields = new ArrayList<>();

        for (Field field : fields) {
            Index index = field.getAnnotation(Index.class);
            if (index != null) {
                int indexNum = index.value();
                if (indexNum >= orderedFields.length) {
                    throw new RuntimeException("Index number of field: " + field.getName() + " is larger than the number of fields " + orderedFields.length);
                }

                if (orderedFields[indexNum] != null) {
                    throw new RuntimeException("'" + orderedFields[indexNum].getName() + "' and '" + field.getName() + "' has same index number of: " + indexNum);
                }

                orderedFields[indexNum] = field;
            } else {
                leftoverFields.add(field);
            }
        }

        if (leftoverFields.size() == fields.length) { //no fields have index annotation
            return fields;
        }

        for (Field field : leftoverFields) {
            for (int i = 0; i < orderedFields.length; i++) {
                if (orderedFields[i] == null) {
                    orderedFields[i] = field;
                    break;
                }
            }
        }
        return orderedFields;
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
            Generator<?> candidateGenerator = getGenerator(field);

            Range range = field.getAnnotation(Range.class);
            if (range != null && candidateGenerator instanceof RangedGenerator<?>) {
                generator = ReflectionUtils.buildWithConstructor(candidateGenerator.getClass(), range.min(), range.max());
            } else {
                generator = candidateGenerator;
            }

            fieldGeneratorMap.put(field, generator);

        }

        return generator.generate();
    }

    private Generator<?> getGenerator(Field field) {
        Generator<?> generator;
        Generation generatorAnnotation = field.getAnnotation(Generation.class);
        if (generatorAnnotation != null) {
            generator = ReflectionUtils.buildWithNoArgConstructor(generatorAnnotation.value());
        } else {
            generator = DefaultGenerators.get(field.getType());
        }
        return generator;
    }
}
