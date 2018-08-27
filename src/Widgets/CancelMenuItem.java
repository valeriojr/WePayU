package Widgets;

public class CancelMenuItem extends Widget {
    public CancelMenuItem(String label) {
        super(label, item -> item.getParentMenu().finishMenu());
    }
}
