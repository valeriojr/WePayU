package Documents;

public class ServiceCharge {
    private String service, description;
    private double value;

    public ServiceCharge(String service, String description, double value){
        this.service = service;
        this.description = description;
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("Serviço: %s\nDescrição: %s\nValor: %.2fR$", service, description, value);
    }
}
