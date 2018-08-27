package Widgets.RadioGroup;

import Menu.ActionListener;
import Widgets.Widget;

public class RadioItem<T> extends Widget {
    public T content;

    public RadioItem(String label, ActionListener listener) {
        super(label);
        this.listener = listener;
    }

    @Override
    public String toString() {
        return String.format("%s", content != null? content.toString() : "-");
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
