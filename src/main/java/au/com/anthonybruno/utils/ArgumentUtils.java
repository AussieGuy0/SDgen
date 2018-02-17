package au.com.anthonybruno.utils;

public class ArgumentUtils {

    public static void isNotNull(Object testedArgument, String messsage) {
        if (testedArgument == null) {
            throw new IllegalArgumentException(messsage);
        }
    }
}
