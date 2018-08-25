package Util.Menu;

public class ConfirmDialog extends Menu{
    private String message;

    public ConfirmDialog(String message) {
        super(new String[]{"Sim", "Não"});
        this.message = message;
    }

    @Override
    public void show() {
        System.out.println(message);
        super.show();
    }
}
