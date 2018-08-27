package Employee;

import Documents.ServiceCharge;

import java.util.Stack;

public class Employee {
    private String name, address;
    private Long id;
    private EmployeeType type;
    private PaymentMethod paymentMethod;
    private boolean syndicateMember;
    private Double syndicalTax;
    private Stack<ServiceCharge> serviceCharges;
    private Long syndicateId;
    private PaymentSchedule schedule;

    public Employee() {
        this.name = null;
        this.address = null;
        this.id = null;
        this.type = null;
        this.paymentMethod = null;
        this.syndicateMember = false;
        this.syndicalTax = 0.0;
        this.syndicateId = null;
        this.serviceCharges = new Stack<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(EmployeeType type) {
        this.type = type;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setSyndicateMember(Boolean syndicateMember) {
        this.syndicateMember = syndicateMember;
    }

    public void setSyndicalTax(Double syndicalTax) {
        this.syndicalTax = syndicalTax;
    }

    public void setSyndicateId(Long syndicateId) {
        this.syndicateId = syndicateId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Long getId() {
        return id;
    }

    public EmployeeType getType() {
        return type;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public Boolean isSyndicateMember() {
        return syndicateMember;
    }

    public Double getSyndicalTax() {
        return syndicalTax;
    }

    public PaymentSchedule getSchedule() {
        return schedule;
    }

    @Override
    public String toString() {
        return String.format("Nome: %s\nEndereço: %s\nId: %d\nTipo de contrato: %s\n",
                name, address, id, type.toString()) +
                (syndicateMember ? String.format("Taxa sindical%.2fR$\n", syndicalTax):"");
    }

    public void show() {
        System.out.println(this);
    }

    private Double getSalary() {
        if(type == null){
            return 0.0;
        }

        return type.getPayment() - syndicalTax;
    }

    public boolean checkType(Class<?> typeClass){
        return type.getClass() == typeClass;
    }

    public void addServiceCharge(ServiceCharge charge){
        serviceCharges.push(charge);
    }

    public void generatePayroll() {
        show();
        System.out.println(String.format("Salário: %.2f", getSalary()));
    }

    public void setSchedule(PaymentSchedule schedule) {
        this.schedule = schedule;
    }

    public Long getSyndicateId() {
        return syndicateId;
    }
}
