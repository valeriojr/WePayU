package Menus;

import Employee.*;
import Employee.Types.Commissioned;
import Employee.Types.Hourly;
import Employee.Types.Salaried;
import Main.WePayU;
import Menu.Menu;
import Menu.MessageDialog;
import Util.Calendar;
import Util.Input.Input;
import Widgets.*;
import Widgets.RadioGroup.RadioGroup;
import Widgets.RadioGroup.RadioItem;

import java.util.ArrayList;
import java.util.List;

public class EditEmployee extends Menu {
    private Employee employee;
    private EditText name, address;
    private RadioGroup type, paymentMethod, schedule;
    private CheckBox syndicateMember;
    private EditText syndicalTax;
    private MenuItem save;
    private boolean initialized;
    private boolean finished = false;

    public EditEmployee(Employee employee){
        this.employee = employee;
        initialized = employee != null;

        if(!initialized){
            this.employee = new Employee();
            message = "Edite os atributos do novo empregado";
        }else{
            message = "Edite os atrubutos do empregado";
        }
    }

    @Override
    public void init() {
        super.init();

        initializeWidgets();

        add(name);
        add(address);
        add(type);
        add(paymentMethod);
        add(syndicateMember);
        add(syndicalTax);
        add(schedule);

        add(save);
        add(new CancelMenuItem("Cancelar"));
    }

    @Override
    public void resume() {
        super.resume();
        if(finished){
            finishMenu();
        }
    }

    private void initializeWidgets() {
        name = new EditText("Nome", employee.getName());
        address = new EditText("Endereço", employee.getAddress());

        List<Widget> typeList = new ArrayList<>();

        typeList.add(new RadioItem<EmployeeType>("Horista", item -> {
            ((RadioItem<EmployeeType>) item).setContent(new Hourly(Input.getDouble("Salário horário")));
        }));

        typeList.add(new RadioItem<EmployeeType>("Salariado", item -> {
            ((RadioItem<EmployeeType>) item).setContent(new Salaried(Input.getDouble("Salário")));
        }));

        typeList.add(new RadioItem<EmployeeType>("Comissionado", item -> {
            ((RadioItem<EmployeeType>) item).setContent(new Commissioned(Input.getDouble("Salário"),
                Input.getDouble("Taxa de comissão")));
        }));

        List<Widget> methodList = new ArrayList<>();

        methodList.add(new RadioItem<PaymentMethod>(PaymentMethod.CHECK_BY_HANDS.toString(), item -> {
            ((RadioItem<PaymentMethod>) item).setContent(PaymentMethod.CHECK_BY_HANDS);
        }));

        methodList.add(new RadioItem<PaymentMethod>(PaymentMethod.CHECK_BY_COURIER.toString(), item -> {
            ((RadioItem<PaymentMethod>) item).setContent(PaymentMethod.CHECK_BY_COURIER);
        }));

        methodList.add(new RadioItem<PaymentMethod>(PaymentMethod.DEPOSIT.toString(), item -> {
            ((RadioItem<PaymentMethod>) item).setContent(PaymentMethod.DEPOSIT);
        }));

        List<Widget> scheduleList = new ArrayList<>();

        scheduleList.add(new RadioItem<PaymentSchedule>("Mensal $", item ->{
            ((RadioItem<PaymentSchedule>) item).setContent(new MonthlyPaymentSchedule(-1));
        }));
        scheduleList.add(new RadioItem<PaymentSchedule>("Semanal 2 sexta", item ->{
            ((RadioItem<PaymentSchedule>) item).setContent(new WeeklyPaymentSchedule(2, Calendar.Day.FRI));
        }));

        type = new RadioGroup("Tipo de contrato", typeList);
        type.setSelected(new RadioItem<EmployeeType>("Teste", null));
        ((RadioItem<EmployeeType>) type.getSelected()).setContent(employee.getType());

        paymentMethod = new RadioGroup("Método de pagamento", methodList);
        paymentMethod.setSelected(new RadioItem<PaymentMethod>("", null));
        ((RadioItem<PaymentMethod>) paymentMethod.getSelected()).setContent(employee.getPaymentMethod());

        schedule = new RadioGroup("Agenda de pagamento", scheduleList);
        schedule.setSelected(new RadioItem<PaymentSchedule>("", null));
        ((RadioItem<PaymentSchedule>) schedule.getSelected()).setContent(employee.getSchedule());


        syndicateMember = new CheckBox("Membro do sindicato", item -> {
            ((CheckBox) item).toggle();
            if(!((CheckBox) item).getContent()){
                syndicalTax.clear();
            }
        });
        syndicateMember.setContent(employee.isSyndicateMember());

        Double tax = employee.getSyndicalTax();
        syndicalTax = new EditText("Taxa sindical", tax != null? Double.toString(tax) : null);
        syndicalTax.setListener(item -> {
            if(!syndicateMember.getContent()){
                startMenu(new MessageDialog("Erro: O empregado não é membro do sindicato"));
            }else {
                EditText.getDefaultAction().actionPerformed(item);
            }
        });

        save = new MenuItem("Salvar", item -> {
            if(name.getContent() == null || address.getContent() == null ||
                    ((RadioItem) type.getSelected()).getContent() == null ||
                    ((RadioItem) paymentMethod.getSelected()).getContent() == null ||
                    ((RadioItem) schedule.getSelected()).getContent() == null){
                startMenu(new MessageDialog("Erro. Preencha todos os campos"));
            }else {
                WePayU.saveState();
                updateEmployee();

                if(!initialized) {
                    employee.setId(WePayU.getNewId());
                    WePayU.addSyndicateMember(employee);
                    WePayU.addEmployee(employee);
                }else {
                    startMenu(new MessageDialog("Empregado atualizado com successo!"));
                }

                finished = true;
            }
        });
    }

    private void updateEmployee(){
        employee.setName(name.getContent());
        employee.setAddress(address.getContent());
        employee.setType(((EmployeeType) ((RadioItem) type.getSelected()).getContent()));
        employee.setPaymentMethod(((PaymentMethod) ((RadioItem) paymentMethod.getSelected()).getContent()));
        employee.setSyndicateMember(syndicateMember.getContent());
        if(syndicateMember.getContent()){
            employee.setSyndicalTax(Double.valueOf(syndicalTax.getContent()));
            employee.setSyndicateId(WePayU.getNewSyndicateId());
        }else{
            if(employee.getSyndicateId() != null) {
                WePayU.removeSyndicateMember(employee.getSyndicateId());
            }
        }
        employee.setSchedule(((PaymentSchedule) ((RadioItem) schedule.getSelected()).getContent()));
    }
}
