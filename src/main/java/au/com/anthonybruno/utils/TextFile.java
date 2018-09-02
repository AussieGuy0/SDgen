package au.com.anthonybruno.utils;

import java.io.*;
import java.nio.charset.Charset;

public class TextFile {

    public static final Charset DEFAULT_ENCODING = Charset.forName("utf-8");

    private final File file;
    private final Lazy<String> text;
    private final Charset encoding;

    public TextFile(File file) {
        this(file, DEFAULT_ENCODING);
    }

    public TextFile(File file, Charset encoding) {
        if (!file.exists()) {
            throw new IllegalArgumentException(file + " does not exist.");
        }
        this.file = file;
        this.text = new Lazy<>(() -> readTextFromFile(file));
        this.encoding = encoding;
    }

    public Writer getWriter() {
        try {
            return new FileWriter(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getText() {
        return text.get();
    }

    private String readTextFromFile(File file) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
        StringBuilder stringBuilder = new StringBuilder();
        reader.lines().forEach((line) -> stringBuilder.append(line).append("\n"));
        return stringBuilder.toString();
    }

}
