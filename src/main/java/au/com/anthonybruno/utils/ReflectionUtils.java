package au.com.anthonybruno.utils;

import java.lang.reflect.Field;

public class ReflectionUtils {

    public Field[] getPrivateFields(Class<?> c) {
        System.out.println(c.getDeclaredFields());
        return c.getDeclaredFields();
    }
}
