package au.com.anthonybruno.definition;

import au.com.anthonybruno.generator.Generator;

public interface FieldDefinition extends RecordDefinition {

    /**
     * Adds a field that will be used when generating data.
     *
     * @param name      the key of the field
     * @param generator the Generator that creates values for the field
     */
    FieldDefinition addField(String name, Generator generator);
}
