package Menu;

import Util.Input.Input;
import Widgets.MenuItem;

public class MessageDialog extends Menu {
    public MessageDialog(String message){
        this.message = message;
    }

    @Override
    public void waitForInput() {
        Input.getString("Pressione ENTER para continuar");
        MenuManager.getInstance().finishCurrentMenu();
    }
}
