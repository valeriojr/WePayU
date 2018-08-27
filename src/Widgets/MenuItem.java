package Widgets;

import Menu.ActionListener;

public class MenuItem extends Widget {

    public MenuItem(String label, ActionListener listener) {
        super(label, listener);
    }

    @Override
    public String toString() {
        return label;
    }
}
