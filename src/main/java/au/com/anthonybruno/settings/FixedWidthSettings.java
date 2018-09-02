package au.com.anthonybruno.settings;

import java.util.List;

public class FixedWidthSettings extends FlatFileSettings {

    private final List<FixedWidthField> fixedWidthFields;

    public FixedWidthSettings(boolean includeHeaders, List<FixedWidthField> fields) {
        super(includeHeaders);
        this.fixedWidthFields = fields;
    }

    public List<FixedWidthField> getFixedWidthFields() {
        return fixedWidthFields;
    }
}
