import Employee.Employee;
import Employee.Types.Hourly;
import Employee.Types.Salaried;
import Util.Input.Input;

import java.util.Map;
import java.util.TreeMap;

public class WePayU {
    static Map<Long, Employee> employeeMap;
    static long employeeId = 1;

    public static void main(String args[]){
        employeeMap = new TreeMap<>();

        System.out.println("Bem vindo!");

        Employee e = createEmployee();
        addEmployee(e);

        System.out.println(e);
    }

    static Employee createEmployee(){
        return new Employee(
                Input.getString("Nome do funcionário"),
                Input.getString("Endereço"),
                employeeId++,
                new Hourly(6.0)
        );
    }

    static void addEmployee(Employee employee){
        employeeMap.put(employee.getId(), employee);
    }
}
