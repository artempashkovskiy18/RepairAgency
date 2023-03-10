package constants;

public enum Role {
    USER(1), MANAGER(2), CRAFTSMAN(3);
    private final int id;

    Role(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
