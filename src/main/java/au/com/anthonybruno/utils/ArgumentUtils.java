package au.com.anthonybruno.utils;

public class ArgumentUtils {

    public static void isNotNull(Object testedArgument, String message) {
        if (testedArgument == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
