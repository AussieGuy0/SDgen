package au.com.anthonybruno;

import au.com.anthonybruno.settings.CsvSettings;

public interface FileTypeDefinition {

    ResultDefinition asCsv();

    ResultDefinition asCsv(CsvSettings settings);
}
