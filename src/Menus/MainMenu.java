package Menus;

import Employee.Employee;
import Employee.Types.Commissioned;
import Employee.Types.Hourly;
import Main.WePayU;
import Menu.Menu;
import Menu.MessageDialog;
import Util.Calendar;
import Util.Input.Input;
import Widgets.CancelMenuItem;
import Widgets.MenuItem;

public class MainMenu extends Menu {
    @Override
    public void init() {
        super.init();

        add(new MenuItem("Gerenciar empregados", item -> startMenu(new EmployeeManagement())));

        add(new MenuItem("Solicitar cartão de ponto", item -> {
            Employee employee =  WePayU.findEmployee(Input.getLong("ID"));

            if(checkEmployeeType(employee, Hourly.class)) {
                WePayU.requestTimecard((Hourly) employee.getType());
            }
        }));
        add(new MenuItem("Lançar cartão de ponto", item -> {
            Employee employee =  WePayU.findEmployee(Input.getLong("ID"));

            if(checkEmployeeType(employee, Hourly.class)) {
                WePayU.launchTimecard((Hourly) employee.getType());
            }
        }));

        add(new MenuItem("Lançar resultado de venda", item -> {
            Employee employee = WePayU.findEmployee(Input.getLong("ID"));

            if(checkEmployeeType(employee, Commissioned.class)){
                WePayU.launchSaleResult((Commissioned) employee.getType());
            }
        }));

        add(new MenuItem("Lançar taxa de serviço", item -> {
            Employee employee = WePayU.findSyndicateMember(Input.getLong("ID"));
            if(employee != null){
                WePayU.launchServiceCharge(employee);
            }
        }));

        add(new MenuItem("Rodar folha de pagamento", item -> WePayU.run()));

        add(new MenuItem("Avançar dia", item -> {
            Calendar.getInstance().goToNextDay();
            System.out.println(String.format("%d\n", Calendar.getInstance().getCurrentDate()));
        }));

        add(new MenuItem("Desfazer", item -> WePayU.undo()));
        add(new MenuItem("Refazer", item -> WePayU.redo()));

        add(new CancelMenuItem("Sair"));
    }

    private boolean checkEmployeeType(Employee employee, Class<?> type){
        if (employee == null){
            return false;
        }
        if(!employee.checkType(type)){
            startMenu(new MessageDialog("Erro: operação não permitida para este tipo de empregado"));
            return false;
        }

        return true;
    }
}