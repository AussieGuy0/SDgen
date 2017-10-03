package au.com.anthonybruno.defintion;

import au.com.anthonybruno.generator.Generator;

public interface RecordDefinition {

    RecordDefinition addField(String name, Generator generator);

    FileTypeDefinition generate(int num);
}
