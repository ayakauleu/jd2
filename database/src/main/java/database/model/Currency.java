package database.model;

public enum Currency {
    BYN("BYN"), USD("USD"), EUR("EUR"), RUB("RUB");

    private String value;

    Currency(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}