import javax.print.attribute.standard.OrientationRequested;
import javax.swing.*;
import java.awt.*;

public class NewProjectFileSelection extends JFrame {
    private JPanel newProjectFileSelectionPanel;
    private JPanel labelPanel;
    private JPanel buttonsPanel;

    private JLabel projectPathLabel;
    private JLabel sourcePathLabel;
    private JLabel memoryPathLabel;

    private JButton getProjectPathButton;
    private JButton getSourceFileButton;
    private JButton selectMemoryButton;
    private JButton createMemoryButton;

    public NewProjectFileSelection(){
        super("New Project");
        newProjectFileSelectionPanel = new JPanel(new BorderLayout());
        createNewProjectFileSelection();
        finalizeNewProjectFileSelection();
    }

    private void createNewProjectFileSelection() {
        createLabels();
        createButtons();
    }

    private void createButtons() {
        buttonsPanel = new JPanel();

        getProjectPathButton = new JButton("Set Project Name");
        getSourceFileButton = new JButton("Select Source File");
        selectMemoryButton = new JButton("Load Memory");
        createMemoryButton = new JButton("Create New Memory");



        buttonsPanel.add(getProjectPathButton);
        buttonsPanel.add(getSourceFileButton);
        buttonsPanel.add(selectMemoryButton);
        buttonsPanel.add(createMemoryButton);

        newProjectFileSelectionPanel.add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void createLabels() {
        labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        projectPathLabel = new JLabel("Project Path: --");
        sourcePathLabel = new JLabel("Source File Path: --");
        memoryPathLabel = new JLabel("Memory Path: --");

        labelPanel.add(projectPathLabel);
        labelPanel.add(sourcePathLabel);
        labelPanel.add(memoryPathLabel);
        newProjectFileSelectionPanel.add(labelPanel, BorderLayout.NORTH);
    }

    private void finalizeNewProjectFileSelection() {
        this.add(newProjectFileSelectionPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
