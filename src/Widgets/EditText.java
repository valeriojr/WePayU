package Widgets;

import Menu.*;
import Menu.MenuManager;
import Util.Input.Input;

public class EditText extends Widget{
    private String content;

    private static ActionListener defaultAction = item  -> {
        if(((EditText) item).getContent() != null) {
            MenuManager.getInstance().startNewMenu(new EditTextOptions((EditText) item));
        }else{
            ((EditText) item).setContent(Input.getString(item.getLabel()));
        }
    };

    public EditText(String label){
        super(label);
        this.content = null;

        listener = defaultAction;
    }

    public EditText(String label, String content) {
        super(label);
        this.content = content;

        listener = widget -> {
            if(this.content != null) {
                MenuManager.getInstance().startNewMenu(new EditTextOptions(this));
            }else{
                setContent(Input.getString(widget.getLabel()));
            }
        };
    }

    public void clear(){
        setContent(null);
    }

    public String getContent() {
        return content;
    }

    private void setContent(String content) {
        this.content = content;
    }

    public static ActionListener getDefaultAction() {
        return defaultAction;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", label, content == null ? "-" : content);
    }

    public static class EditTextOptions extends Menu {
        private MenuItem edit, clear, exit;

        public EditTextOptions(EditText editText) {
            super();

            message = editText.toString();

            edit = new MenuItem("Editar", w -> {
                editText.setContent(Input.getString(editText.getLabel()));
                update(editText);
            });

            clear = new MenuItem("Limpar", w -> {
                editText.clear();
                update(editText);
            });

            exit = new MenuItem("Voltar", w -> MenuManager.getInstance().finishCurrentMenu());

            add(edit);
            add(clear);
            add(exit);
        }

        private void update(EditText editText){
            message = editText.toString();
            finishMenu();
        }
    }
}
