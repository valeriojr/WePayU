package Widgets.RadioGroup;

import Menu.Menu;
import Widgets.Widget;

import java.util.List;

public class RadioGroup extends Widget {
    private Widget selected;

    public RadioGroup(String label, List<Widget> options){
        super(label);

        listener = w -> {
            getParentMenu().startMenu(new RadioGroupOptions(this, options));
        };
    }

    public Widget getSelected() {
        return selected;
    }

    public void setSelected(Widget selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", label, selected != null? selected : "-");
    }

    public class RadioGroupOptions extends Menu {

        public RadioGroupOptions(RadioGroup radioGroup, List<Widget> options){
            for(Widget w : options){
                add(w);
            }
        }

        @Override
        public String toString() {
            String s = "";
            int index = 0;

            for(Widget w : widgets){
                s = s.concat(String.format("%d.%s\n", ++index, w.getLabel()));
            }

            return s;
        }

        @Override
        public void performAction() {
            super.performAction();
            selected = this.getSelected();
            finishMenu();
        }
    }
}
