package au.com.anthonybruno.settings;

public class FlatFileSettings extends Settings {

    private final boolean includingHeaders;

    public FlatFileSettings(boolean includeHeaders) {
        this.includingHeaders = includeHeaders;
    }

    public boolean isIncludingHeaders() {
        return includingHeaders;
    }
}
