package constants;

public enum Role {
    USER(2), MANAGER(3), CRAFTSMAN(5);
    private final int id;

    Role(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
