package Menu;

import Util.Input.Input;
import Widgets.Widget;

import java.util.ArrayList;
import java.util.List;

public abstract class Menu {
    protected String title;
    protected String message;
    protected List<Widget> widgets;
    private Widget selected;
    private Menu parent;

    protected Menu(){
        this.widgets = new ArrayList<>();
        selected = null;
    }

    public void init(){

    }

    public void pause(){

    }

    public void resume(){

    }

    public void finish() {

    }

    public void show(){
        System.out.println(this);
    }

    @Override
    public String toString() {
        String menu = "";
        int index = 0;

        menu = title != null? menu.concat(title + "\n\n") : menu;
        menu = message != null? menu.concat(message + "\n\n") : menu;

        for(Widget w : widgets){
            menu = menu.concat(String.format("%d.%s\n",++index, w));
        }

        return menu;
    }

    protected void add(Widget w){
        this.widgets.add(w);
        w.setParentMenu(this);
    }

    public void waitForInput() {
        Integer i = 0;

        while(i < 1 || i > widgets.size()) {
            i = Input.getInt("Selecione uma opção");
        }

        selected = widgets.get(i-1);
    }

    public void performAction() {
        if(selected != null){
            selected.getListener().actionPerformed(selected);
        }
    }

    public Widget getSelected() {
        return selected;
    }

    public void setSelected(Widget selected) {
        this.selected = selected;
    }

    public void startMenu(Menu menu){
        MenuManager.getInstance().startNewMenu(menu);
    }

    public void finishMenu(){
        MenuManager.getInstance().finishCurrentMenu();
    }

    protected Menu getParent() {
        return parent;
    }

    void setParent(Menu parent) {
        this.parent = parent;
    }
}
