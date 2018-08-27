package Employee;

public enum PaymentMethod {
    CHECK_BY_HANDS("Cheque em mãos"),
    CHECK_BY_COURIER("Cheque pelos correios"),
    DEPOSIT("Depósito em conta bancária");

    private String text;

    PaymentMethod(String text){
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
