package au.com.anthonybruno.settings;

public class CsvSettings extends FlatFileSettings {

    public static final char DEFAULT_DELIMITER = ',';

    private final char delimiter;

    public CsvSettings(boolean includeHeaders) {
        this(includeHeaders, DEFAULT_DELIMITER);
    }

    public CsvSettings(boolean includeHeaders, char delimiter) {
        super(includeHeaders);
        this.delimiter = delimiter;
    }

    public char getDelimiter() {
        return delimiter;
    }

    public static class Builder {
        private char delimiter = DEFAULT_DELIMITER;
        private boolean includeHeaders = true;

        public Builder setDelimiter(char delimiter) {
            this.delimiter = delimiter;
            return this;
        }

        public Builder setIncludeHeaders(boolean includeHeaders) {
            this.includeHeaders = includeHeaders;
            return this;
        }

        public CsvSettings build() {
            return new CsvSettings(includeHeaders, delimiter);
        }
    }

}
