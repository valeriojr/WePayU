package Documents;

public class ServiceCharge {
    private String service, description;
    private double value;
    private boolean paid;

    public ServiceCharge(String service, String description, double value){
        this.service = service;
        this.description = description;
        this.value = value;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return String.format("Serviço: %s\nDescrição: %s\nValor: %.2fR$", service, description, value);
    }
}
