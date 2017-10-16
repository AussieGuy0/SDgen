package au.com.anthonybruno.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectionUtils {

    @SuppressWarnings({"unchecked"})
    public static <T> T buildWithNoArgConstructor(Class<T> c) {
        for (Constructor<?> constructor : c.getDeclaredConstructors()) {
            if (constructor.getParameterCount() == 0) {
                try {
                    return (T) constructor.newInstance();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    throw new IllegalArgumentException("Couldn't construct a new object of type: " + c + " using it's 0 args constructor!", e);
                }
            }
        }

        throw new IllegalArgumentException("No 0 args constructor found in class: " + c);
    }

    @SuppressWarnings({"unchecked"})
    public static <T> T buildWithConstructor(Class<T> toBuild, Object... args) {
        for (Constructor<?> constructor : toBuild.getDeclaredConstructors()) {
            if (constructor.getParameterCount() == args.length) {
                try {
                    return (T) constructor.newInstance(args);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    throw new IllegalArgumentException("Couldn't construct a new object of type: " + toBuild + " using it's 0 args constructor!", e);
                }
            }
        }
        throw new IllegalArgumentException("No " + args.length + " args constructor found in class: " + toBuild);
    }

    public static Field getField(Class<?> c, String fieldName) throws NoSuchFieldException {
        Class<?> current = c;
        do {
            for (Field field : current.getDeclaredFields()) {
                System.out.println(field.getName());
                if (field.getName().equals(fieldName)) {
                    return field;
                }
            }
            current = current.getSuperclass();
        } while (current != null);
        throw new NoSuchFieldException();
    }

    public static List<Field> getAllFields(Class<?> c) {
        List<Field> out = new ArrayList<>();
        Class<?> current = c;
        do {
            out.addAll(Arrays.asList(current.getDeclaredFields()));
            current = current.getSuperclass();
        } while (current != null);
        return out;
    }
}
