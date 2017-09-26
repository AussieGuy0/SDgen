package au.com.anthonybruno.creator;

import java.io.File;

public interface FileFactory {

    String buildString();

    File buildFile(File file);
}
