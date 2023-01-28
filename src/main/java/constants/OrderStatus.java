package constants;

public enum OrderStatus {
    PENDING_PAYMENT(1), PAID(2), IN_PROGRESS(3), COMPLETE(4), CANCELED(5), WAITING(6);

    private final int id;

    OrderStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
