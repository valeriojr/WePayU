import Employee.Employee;
import Employee.Types.Commissioned;
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

        launchSaleResult((Commissioned) e.getType());

        System.out.println(e);
    }

    static Employee createEmployee(){
        return new Employee(
                Input.getString("Nome do funcionário"),
                Input.getString("Endereço"),
                employeeId++,
                new Commissioned(400.0, 12)
        );
    }

    static void addEmployee(Employee employee){
        employeeMap.put(employee.getId(), employee);
    }

    static void removeEmployee(long id){
        employeeMap.remove(id);
    }

    static void launchPointCard(Hourly h){
        h.submit(Input.getInt("Digite a quantidade de horas trabalhadas"));
    }

    static void launchSaleResult(Commissioned c){
        c.submit(Input.getDouble("Digite o valor total da venda"));
    }
}
