package Widgets;

import Menu.ActionListener;

public class CheckBox extends Widget {
    private boolean content;

    public CheckBox(String label, ActionListener listener) {
        super(label, listener);
        content = false;
    }

    public void toggle(){
        content = !content;
    }

    public boolean getContent(){
        return content;
    }

    public void setContent(boolean content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", label, content? "x" : "o");
    }
}
