import javax.swing.*;


public class Main {

    static final int NEW_PROJECT = 0;
    static final int LOADED_PROJECT = 1;

    public static void main(final String[] args)
    {
        SwingUtilities.invokeLater(StartPageUI::new);
    }
}
