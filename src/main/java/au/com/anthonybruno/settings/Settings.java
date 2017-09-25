package au.com.anthonybruno.settings;

public abstract class Settings {

    private final int rows;

    public Settings(int rows) {
        this.rows = rows;
    }

    public int getRows() {
        return rows;
    }
}
