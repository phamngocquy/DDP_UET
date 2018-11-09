package test;

public class sg {
    private static sg ourInstance = new sg();

    public static sg getInstance() {
        return ourInstance;
    }

    private sg() {
    }
}
