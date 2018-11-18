package database.model;

public enum PaymentType {
    TRANSFER("TRANSFER"),
    MOBILE("MOBILE"),
    PHONE("PHONE"),
    APARTMENT("APARTMENT"),
    ELECTRICITY("ELECTRICITY");

    final String value;

    PaymentType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
