package Widgets;

import Menu.ActionListener;
import Menu.Menu;

public abstract class Widget {
    protected String label;
    protected ActionListener listener;
    protected Menu parentMenu;

    public Widget(String label){
        this.label = label;
        this.listener = null;
    }

    public Widget(String label, ActionListener listener){
        this.label = label;
        this.listener = listener;
    }

    public void setListener(ActionListener listener) {
        this.listener = listener;
    }

    public ActionListener getListener() {
        return listener;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }



    public void setParentMenu(Menu parentMenu){
        this.parentMenu = parentMenu;
    }

    public Menu getParentMenu() {
        return parentMenu;
    }

    @Override
    public String toString() {
        return label;
    }
}
