package au.com.anthonybruno.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ReadFile {

    private final File file;
    private final String text;

    public ReadFile(File file) {
        this.file = file;
        this.text = readTextFromFile(file);
    }

    public String getText() {
        return text;
    }

    private String readTextFromFile(File file) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
        StringBuilder stringBuilder = new StringBuilder();
        reader.lines().forEach((line) -> stringBuilder.append(line).append("\n"));
        return stringBuilder.toString();
    }

}
