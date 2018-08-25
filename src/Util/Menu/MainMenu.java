package Util.Menu;

public class MainMenu extends Menu {

    public MainMenu(){
        super(new String[]{"Adicionar empregado", "Remover empregado", "Lançar cartão de ponto",
                "Submeter cartão de ponto", "Lançar resultado de venda"});
        title = "Menu principal";
    }

    @Override
    public void show() {
        System.out.println(title);
        super.show();
    }
}
