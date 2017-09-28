package au.com.anthonybruno.defintion;

import au.com.anthonybruno.generator.Generator;

public interface FieldDefinition {

    RecordDefinition use(Class<?> c);

    RecordDefinition addField(String name, Generator generator);
}
