import Documents.Timecard;
import Employee.Employee;
import Employee.Types.Commissioned;
import Employee.Types.Hourly;
import Employee.Types.Salaried;
import Util.Input.Input;
import Util.Menu.ConfirmDialog;
import Util.Menu.MainMenu;
import Util.Menu.Menu;

import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

public class WePayU {
    static Map<Long, Employee> employeeMap;
    static long employeeId = 1;
    static Stack<Menu> menuStack;

    public static void main(String args[]){
        employeeMap = new TreeMap<>();
        menuStack = new Stack<>();

        System.out.println("Bem vindo!");

        startMenu(new MainMenu());

        boolean exit = false;

        while(!exit){
            showMenu();
            int in = Input.getInt("Selecione uma opção");
            switch (in){
                case 1:
                    addEmployee(createEmployee());
                    break;
                case 2:
                    removeEmployee(employeeId);
                    break;
                case 3:
                    requestTimecard();
                    break;
                case 4:
                    launchTimecard();
                    break;
            }

        }
    }

    static Employee createEmployee(){
        return new Employee(
                Input.getString("Nome do funcionário"),
                Input.getString("Endereço"),
                employeeId++,
                new Hourly(Input.getDouble("Salário horário"))
        );
    }

    static void addEmployee(Employee employee){
        employeeMap.put(employee.getId(), employee);
    }

    static void removeEmployee(long id){
        employeeMap.remove(id);
    }

    static void requestTimecard(){
        Employee e = searchEmployee(Input.getLong("Digite a id do empregado"));
        if(e.getType() instanceof Hourly){
            Hourly hourly = (Hourly) e.getType();
            hourly.addTimecard(new Timecard());
            System.out.println(hourly.getTimecards().peek());
        }
    }

    static void launchTimecard(){
        Employee e = searchEmployee(Input.getLong("Digite a id do empregado"));
        if(e.getType() instanceof Hourly){
            Hourly hourly = ((Hourly) e.getType());
            hourly.getTimecards().peek().submit(Input.getInt("Digite a quantidade de horas trabalhadas"));
            System.out.println(hourly.getTimecards().peek());
        }
    }

    static void launchSaleResult(){

    }

    private static void showMenu(){
        if(!menuStack.empty()){
            menuStack.peek().show();
        }
    }

    private static void startMenu(Menu menu){
        menuStack.push(menu);
    }

    private static void finishMenu(){
        menuStack.pop();
    }

    private static Employee searchEmployee(long key){
        return employeeMap.get(key);
    }
}
