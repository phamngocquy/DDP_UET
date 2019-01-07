package database.h2db;

public class sig {
    private static sig ourInstance = new sig();

    public static sig getInstance() {
        return ourInstance;
    }

    private sig() {
    }
}
