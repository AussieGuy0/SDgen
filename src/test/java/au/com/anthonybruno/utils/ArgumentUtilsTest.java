package au.com.anthonybruno.utils;

import org.junit.Test;

public class ArgumentUtilsTest {

    @Test(expected = IllegalArgumentException.class)
    public void notNullThrowsException() {
        ArgumentUtils.isNotNull(null, "Argument can't be null!");
    }

    @Test
    public void notNullAcceptsNonNull() {
        ArgumentUtils.isNotNull("lol", "Argument can't be null!");
    }
}
