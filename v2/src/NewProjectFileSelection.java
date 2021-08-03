import javax.swing.*;
import java.awt.*;

public class NewProjectFileSelection extends JFrame {
    private JPanel newProjectFileSelectionPanel;

    private JLabel projectPath;
    private JLabel sourceFile;
    private JLabel memoryPath;

    public NewProjectFileSelection(){
        super("New Project");
        newProjectFileSelectionPanel = new JPanel(new BorderLayout());
        createNewProjectFileSelection();
    }

    private void createNewProjectFileSelection() {

    }
}
