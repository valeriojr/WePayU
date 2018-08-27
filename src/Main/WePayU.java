package Main;

import Documents.SaleResult;
import Documents.ServiceCharge;
import Documents.Timecard;
import Employee.Employee;
import Employee.Types.Commissioned;
import Employee.Types.Hourly;
import Menu.*;
import Menu.MenuManager;
import Menus.MainMenu;
import Util.Calendar;
import Util.Input.Input;

import java.util.Collection;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

public class WePayU {
    private static Map<Long, Employee> employeeMap = new TreeMap<Long, Employee>();
    private static Map<Long, Employee> syndicateMembers = new TreeMap<Long, Employee>();
    private static long employeeId = 1;
    private static long syndicateId = 1;
    private static MenuManager menuManager = MenuManager.getInstance();
    private static Calendar calendar = Calendar.getInstance();

    private static Stack<Memento> mementos = new Stack<>(), mementosForward = new Stack<>();

    public static void main(String args[]){
        System.out.println("Bem vindo!");
        boolean exit = false;

        menuManager.startNewMenu(new MainMenu());

        while(!exit){
            Menu currentMenu = menuManager.getCurrentMenu();
            if(currentMenu != null){
                currentMenu.show();
                currentMenu.waitForInput();
                currentMenu.performAction();
            }else{
                exit = true;
            }

        }
    }

    public static long getNewId(){
        return employeeId++;
    }

    public static long getNewSyndicateId(){
        return syndicateId++;
    }

    public static void addEmployee(Employee employee){
        saveState();
        employeeMap.put(employee.getId(), employee);
        menuManager.startNewMenu(new MessageDialog("Empregado adicionado com sucesso!"));
    }

    public static void addSyndicateMember(Employee employee){
        syndicateMembers.put(getNewSyndicateId(), employee);
    }

    public static void removeEmployee(Long id){
        saveState();
        if(findEmployee(id) != null){
            removeSyndicateMember(findEmployee(id).getSyndicateId());
            employeeMap.remove(id);
            menuManager.startNewMenu(new MessageDialog("Empregado removido com sucesso"));
        }
    }

    public static Employee findEmployee(Long id){
        if(employeeMap.containsKey(id)){
            return employeeMap.get(id);
        }else{
            menuManager.startNewMenu(new MessageDialog("Empregado não encontrado"));
        }

        return null;
    }

    public static Employee findSyndicateMember(Long id){
        if(syndicateMembers.containsKey(id)){
            return syndicateMembers.get(id);
        }else{
            menuManager.startNewMenu(new MessageDialog("Membro não encontrado"));
        }

        return null;
    }

    public static Collection<Employee> getEmployees(){
        return employeeMap.values();
    }

    public static void launchTimecard(Hourly employee) {
        saveState();
        employee.submitTimecard();
    }

    public static void requestTimecard(Hourly employee) {
        employee.addTimecard(new Timecard());

        menuManager.startNewMenu(new MessageDialog("Cartão de ponto gerado com sucesso"));
    }

    public static void launchSaleResult(Commissioned employee) {
        saveState();
        employee.submit(new SaleResult(Input.getDouble("Valor total da venda")));
    }

    public static void launchServiceCharge(Employee employee){
        saveState();
        if(employee.isSyndicateMember()){
            employee.addServiceCharge(new ServiceCharge(Input.getString("Serviço"), Input.getString("Descrição"),
                    Input.getDouble("Valor da taxa")));
        }else{
            menuManager.startNewMenu(new MessageDialog("Erro: O empregado não faz parte do sindicato"));
        }
    }

    public static void run() {
        saveState();
        for(Employee employee : employeeMap.values()){
            if(Calendar.getInstance().matches(employee.getSchedule())){
                employee.generatePayroll();
            }
        }
    }

    public static void saveState(){
        mementos.push(new Memento(employeeMap, syndicateMembers));
        mementosForward.clear();
    }

    public static void restoreState(Memento memento){
        employeeMap = new TreeMap<Long, Employee>(memento.getEmployeeMap());
        syndicateMembers = new TreeMap<Long, Employee>(memento.getSyndicateMembers());
    }

    public static void undo(){
        mementosForward.push(mementos.peek());
        mementos.pop();
        restoreState(mementos.peek());
    }

    public static void redo(){
        mementos.push(mementosForward.peek());
        restoreState(mementosForward.peek());
        mementosForward.pop();
    }

    public static void removeSyndicateMember(Long id) {
        if(findEmployee(id) != null){
            syndicateMembers.remove(id);
        }
    }

    public static class Memento{
        TreeMap<Long, Employee> employeeMap, syndicateMembers;

        public Memento(Map<Long, Employee> employeeMap, Map<Long, Employee> syndicateMembers){
            this.employeeMap = new TreeMap<Long,Employee>(employeeMap);
            this.syndicateMembers = new TreeMap<Long,Employee>(syndicateMembers);
        }

        public TreeMap<Long, Employee> getEmployeeMap() {
            return employeeMap;
        }

        public TreeMap<Long, Employee> getSyndicateMembers() {
            return syndicateMembers;
        }
    }
}
