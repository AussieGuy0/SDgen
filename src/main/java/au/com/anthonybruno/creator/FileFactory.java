package au.com.anthonybruno.creator;

import java.io.File;

public interface FileFactory {

    String createString(int rowsToGenerate);

    File createFile(File file, int rowsToGenerate);
}
