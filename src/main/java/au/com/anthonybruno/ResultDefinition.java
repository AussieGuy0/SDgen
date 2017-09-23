package au.com.anthonybruno;

import java.io.File;

public interface ResultDefinition {

    File toFile(String filepath);

    String toString();
}
