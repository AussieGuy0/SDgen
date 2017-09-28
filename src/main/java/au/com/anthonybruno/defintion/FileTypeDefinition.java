package au.com.anthonybruno.defintion;

import au.com.anthonybruno.settings.CsvSettings;
import au.com.anthonybruno.settings.FixedWidthSettings;

public interface FileTypeDefinition {

    RecordDefinition asCsv();

    RecordDefinition asCsv(CsvSettings settings);

    RecordDefinition asFixedWidth(FixedWidthSettings fixedWidthSettings);
}
