package au.com.anthonybruno.settings;

import com.univocity.parsers.fixed.FixedWidthFields;

public class FixedWidthSettings extends Settings {

    private final FixedWidthFields fixedWidthFields;

    public FixedWidthSettings(FixedWidthFields fields) {
        this.fixedWidthFields = fields;
    }

    public FixedWidthFields getFixedWidthFields() {
        return fixedWidthFields;
    }
}
