package au.com.anthonybruno.settings;

import com.univocity.parsers.fixed.FixedWidthFields;

public class FixedWidthSettings extends FlatFileSettings {

    private final FixedWidthFields fixedWidthFields;

    public FixedWidthSettings(boolean includeHeaders, FixedWidthFields fields) {
        super(includeHeaders);
        this.fixedWidthFields = fields;
    }

    public FixedWidthFields getFixedWidthFields() {
        return fixedWidthFields;
    }
}
