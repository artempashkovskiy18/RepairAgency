package constants;

public enum Role {
    USER("user"), MANAGER("manager"), CRAFTSMAN("craftsman");

    private final String name;
    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
