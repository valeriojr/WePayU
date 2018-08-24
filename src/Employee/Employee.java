package Employee;

public class Employee {
    private String name, address;
    private long id;
    private EmployeeType type;

    public Employee(String name, String address, long id, EmployeeType type){
        this.name = name;
        this.address = address;
        this.id = id;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("Nome: %s\nEndereço: %s\nId: %d\n", name, address, id) + type.toString();
    }

    public EmployeeType getType() {
        return type;
    }
}
