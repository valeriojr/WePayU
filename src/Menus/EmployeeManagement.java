package Menus;

import Employee.Employee;
import Main.WePayU;
import Menu.Menu;
import Util.Input.Input;
import Widgets.CancelMenuItem;
import Widgets.MenuItem;

public class EmployeeManagement extends Menu {
    @Override
    public void init() {
        super.init();

        add(new MenuItem("Listar empregados", item -> {
            for(Employee employee : WePayU.getEmployees()){
                employee.show();
            }
        }));
        add(new MenuItem("Novo empregado", item -> startMenu(new EditEmployee(null))));
        add(new MenuItem("Remover empregado", item -> WePayU.removeEmployee(Input.getLong("ID do empregado"))));
        add(new MenuItem("Editar empregado", item -> {
            Employee employee = WePayU.findEmployee(Input.getLong("ID"));
            if(employee != null){
                startMenu(new EditEmployee(employee));
            }
        }));

        add(new CancelMenuItem("Cancelar"));
    }
}
