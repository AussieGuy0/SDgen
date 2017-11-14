package au.com.anthonybruno.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Determines the order in which the field will appear in the generated data. 0 being the first column.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Index {

    /**
     * The index position of the field.
     *
     * @return The index position of the field
     */
    int value();

}
