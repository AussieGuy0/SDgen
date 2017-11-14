package au.com.anthonybruno.annotation;

import au.com.anthonybruno.generator.Generator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A field Annotation that provides configuration options on how a field is used for data generation.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Generation {

    public static final String DEFAULT_FIELD = "${default}";

    /**
     * The Generator that will be used to randomly create values for this field.
     *
     * @return The random value generator for this field
     */
    Class<? extends Generator> value();

    /**
     * The label that will be used to identify the field in the generated data.
     *
     * @return The name of the field
     */
    String name() default DEFAULT_FIELD;
}
