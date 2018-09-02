package au.com.anthonybruno.settings;

import java.util.ArrayList;
import java.util.List;

public class FixedWidthField {

    private final int length;

    public FixedWidthField(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }


    public static List<FixedWidthField> create(int... lengths) {
        List<FixedWidthField> out = new ArrayList<>();
        for (int length : lengths) {
            out.add(new FixedWidthField(length));
        }
        return out;
    }
}
