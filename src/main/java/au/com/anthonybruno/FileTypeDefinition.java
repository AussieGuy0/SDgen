package au.com.anthonybruno;

import au.com.anthonybruno.settings.CsvSettings;
import au.com.anthonybruno.settings.FixedWidthSettings;

public interface FileTypeDefinition {

    ResultDefinition asCsv();

    ResultDefinition asCsv(CsvSettings settings);

    ResultDefinition asFixedWidth(FixedWidthSettings fixedWidthSettings);
}
