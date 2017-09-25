package au.com.anthonybruno.settings;

import com.univocity.parsers.fixed.FixedWidthFields;

public class FixedWidthSettings extends Settings {

    private final FixedWidthFields fixedWidthFields;

    public FixedWidthSettings(int rows, FixedWidthFields fields) {
        super(rows);
        this.fixedWidthFields = fields;
    }

    public FixedWidthFields getFixedWidthFields() {
        return fixedWidthFields;
    }
}
