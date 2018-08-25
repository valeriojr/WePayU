package Util.Menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Menu {
    private static final String separator = "------------------------------";

    protected List<String> options;
    protected String title;

    public Menu(String[] options){
        this.options = new ArrayList<String>(Arrays.asList(options));
    }

    public void show(){
        System.out.println(separator);
        for(int i = 0;i < options.size();i++){
            System.out.println(String.format("|%d.%s", i+1, options.get(i)));
        }
        System.out.println(separator);
    }
}
