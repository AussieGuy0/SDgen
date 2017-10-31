package au.com.anthonybruno.annotation;

import au.com.anthonybruno.generator.Generator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Generation {

    public static final String DEFAULT_FIELD = "${default}";

    Class<? extends Generator> value();

    String name() default DEFAULT_FIELD;
}
