package au.com.anthonybruno.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Determines the maximum and minimum value of a generated field.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Range {

    /**
     * The minimum generated value (inclusive) that this field will be assigned.
     *
     * @return The lowest value that can be generated for the field.
     */
    int min();

    /**
     * The maximum generated value (exclusive) that this field will be assigned.
     *
     * @return The highest value that can be generated for the field.
     */
    int max();
}
