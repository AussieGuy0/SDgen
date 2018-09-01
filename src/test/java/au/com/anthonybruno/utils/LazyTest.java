package au.com.anthonybruno.utils;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class LazyTest {

    @Test
    public void valueIsCached() {
        Lazy<Integer> lazy = new Lazy<>(() -> new Random().nextInt());
        int original = lazy.get();
        int second = lazy.get();
        assertEquals(original, second);
    }
}
