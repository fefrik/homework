package homework.factory;

public enum TranslatorType {

    MORSE("morse"),
    CAESAR("caesar");

    private final String value;

    TranslatorType(String input) {
        this.value = input;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
