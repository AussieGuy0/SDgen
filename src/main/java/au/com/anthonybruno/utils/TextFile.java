package au.com.anthonybruno.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class TextFile {

    private final File file;
    private final Lazy<String> text;

    public TextFile(File file) {
        if (!file.exists()) {
            throw new IllegalArgumentException(file + " does not exist.");
        }
        this.file = file;
        this.text = new Lazy<>(() -> readTextFromFile(file));
    }

    public String getText() {
        return text.get();
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
