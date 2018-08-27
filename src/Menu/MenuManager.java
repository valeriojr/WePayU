package Menu;

import java.util.Stack;

public class MenuManager {
    private static MenuManager instance = null;
    private Stack<Menu> menus;

    public static MenuManager getInstance() {
        if(instance == null){
            instance = new MenuManager();
        }
        return instance;
    }

    private MenuManager(){
        menus = new Stack<>();
    }

    public void startNewMenu(Menu menu){
        if(getCurrentMenu() != null){
            getCurrentMenu().pause();
        }
        menu.setParent(getCurrentMenu());
        clearScreen();
        menus.push(menu);
        getCurrentMenu().init();
    }

    public void finishCurrentMenu(){
        getCurrentMenu().finish();
        clearScreen();
        menus.pop();
        if(getCurrentMenu() != null){
            getCurrentMenu().resume();
        }
    }

    public Menu getCurrentMenu() {
        return menus.empty() ? null : menus.peek();
    }

    public void clearScreen(){
        System.out.print("\033[H\033[2J");
    }
}
