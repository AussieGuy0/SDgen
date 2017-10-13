package au.com.anthonybruno.generator;

import au.com.anthonybruno.generator.defaults.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DefaultGenerators {

    private static final Map<Class<?>, Generator<?>> defaultGenerators = getDefaultGenerators();

    public static Generator<?> get(Class<?> c) {
        return defaultGenerators.get(c);
    }

    private static Map<Class<?>, Generator<?>> getDefaultGenerators() {
        Map<Class<?>, Generator<?>> map = new HashMap<>();

        map.put(String.class, new StringGenerator());

        map.put(int.class, new IntGenerator());
        map.put(Integer.class, new IntGenerator());

        map.put(boolean.class, new BooleanGenerator());
        map.put(Boolean.class, new BooleanGenerator());

        map.put(char.class, new CharGenerator());
        map.put(Character.class, new CharGenerator());

        map.put(double.class, new DoubleGenerator());
        map.put(Double.class, new DoubleGenerator());

        map.put(long.class, new LongGenerator());
        map.put(Long.class, new LongGenerator());

        map.put(short.class, new ShortGenerator());
        map.put(Short.class, new ShortGenerator());

        map.put(byte.class, new ByteGenerator());
        map.put(Byte.class, new ByteGenerator());

        map.put(float.class, new FloatGenerator());
        map.put(Float.class, new FloatGenerator());

        map.put(Date.class, new DateGenerator());

        return map;
    }
}
