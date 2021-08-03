import javax.print.attribute.standard.OrientationRequested;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class NewProjectFileSelection extends JFrame {

    private Model model;

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

        model = ModelFactory.getModel();

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

        getProjectPathButton.addActionListener((ActionEvent e) -> getProjectPath());
        getSourceFileButton.addActionListener((ActionEvent e) -> getSourceFile());
        selectMemoryButton.addActionListener((ActionEvent e) -> selectMemory());
        createMemoryButton.addActionListener((ActionEvent e) -> createMemory());

        buttonsPanel.add(getProjectPathButton);
        buttonsPanel.add(getSourceFileButton);
        buttonsPanel.add(selectMemoryButton);
        buttonsPanel.add(createMemoryButton);

        newProjectFileSelectionPanel.add(buttonsPanel, BorderLayout.CENTER);
    }

    private void createMemory() {

        JFileChooser fc = new JFileChooser(".");
        fc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("TMP Files", "tmp");
        fc.addChoosableFileFilter(csvFilter);
        int returnVal = fc.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            String newMemoryFilePath = file.getPath();
            memoryPathLabel.setText("Memory File Path: " + newMemoryFilePath);
            model.createMemory(newMemoryFilePath);
            attemptLaunch();
        }
    }

    private void selectMemory() {
        JFileChooser fc = new JFileChooser(".");
        fc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("TMP Files", "tmp");
        fc.addChoosableFileFilter(csvFilter);
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            String filePath = file.getPath();
            sourcePathLabel.setText("Memory File Path: " + filePath);
            model.setMemory(filePath);
            attemptLaunch();
        }
    }

    private void getSourceFile() {
        JFileChooser fc = new JFileChooser(".");
        fc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("DOCX Files", "docx");
        fc.addChoosableFileFilter(csvFilter);
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            String filePath = file.getPath();
            sourcePathLabel.setText("Source File Path: " + filePath);
            model.getDocument(filePath);
            attemptLaunch();
        }
    }

    private void getProjectPath() {
        JFileChooser fc = new JFileChooser(".");
        fc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("TMP Files", "tmp");
        fc.addChoosableFileFilter(csvFilter);
        String projectPath;
        int returnVal = fc.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            projectPath = file.getPath();
            projectPathLabel.setText("Project Path: " + projectPath);
            model.setProjectPath(projectPath);
            attemptLaunch();
        }
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

    private void attemptLaunch(){
        if(model.readyToLaunch()){
            JButton launchButton = new JButton("Launch");
            createMemoryButton.addActionListener((ActionEvent e) -> launch());
            newProjectFileSelectionPanel.add(launchButton, BorderLayout.SOUTH);
        }
    }

    private void launch() {
    }

}
