import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class NewProjectFileSelectionUI extends JFrame {

    private NewProjectLauncher newProjectLauncher;

    private JPanel newProjectFileSelectionPanel;
    private JPanel labelPanel;
    private JPanel buttonsPanel;

    private JLabel projectPathLabel;
    private JLabel sourcePathLabel;
    private JLabel memoryPathLabel;

    private JButton getProjectPathButton;
    private JButton getSourceFileButton;
    private JButton selectMemoryButton;


    public NewProjectFileSelectionUI(){
        super("New Project");
        this.setPreferredSize(new Dimension(650, 150));

        newProjectFileSelectionPanel = new JPanel(new BorderLayout());

        newProjectLauncher = new NewProjectLauncher();

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

        getProjectPathButton.addActionListener((ActionEvent e) -> getProjectPath());
        getSourceFileButton.addActionListener((ActionEvent e) -> getSourceFile());
        selectMemoryButton.addActionListener((ActionEvent e) -> selectMemory());

        buttonsPanel.add(getProjectPathButton);
        buttonsPanel.add(getSourceFileButton);
        buttonsPanel.add(selectMemoryButton);

        newProjectFileSelectionPanel.add(buttonsPanel, BorderLayout.CENTER);
    }

    private void selectMemory() {
        JFileChooser fc = new JFileChooser(".");
        fc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("Translation Memory Files", "tmem");
        fc.addChoosableFileFilter(csvFilter);
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            String filePath = file.getPath();
            memoryPathLabel.setText("Memory File Path: " + filePath);
            newProjectLauncher.setMemory(filePath);
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
            newProjectLauncher.getDocument(filePath);
            attemptLaunch();
        }
    }

    private void getProjectPath() {
        JFileChooser fc = new JFileChooser(".");
        fc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("Smart Translate Projects", "smartt");
        fc.addChoosableFileFilter(csvFilter);
        String projectPath;
        int returnVal = fc.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            projectPath = file.getPath();
            projectPath = assertFileType(projectPath, ".smartt");
            projectPathLabel.setText("Project Path: " + projectPath);
            newProjectLauncher.setProjectPath(projectPath);
            attemptLaunch();
        }
    }

    //FILE ASSERTION {

    private String assertFileType(String filePathToSave, String fileSignature) {
        //makes sure the file is saved as the correct file type
        String[] splitFilePath = filePathToSave.split("/", -1);
        String fileNameToSave = splitFilePath[splitFilePath.length-1];
        String[] splitFileName = fileNameToSave.split("\\.", -1);
        fileNameToSave = splitFileName[0] + fileSignature;
        filePathToSave = join(splitFilePath, fileNameToSave);
        return filePathToSave;
    }

    private String join(String[] splitFilePath, String fileNameToSave) {
        StringBuilder filePathToSave = new StringBuilder();
        for (int i = 0; i < splitFilePath.length-1; i++) {
            filePathToSave.append(splitFilePath[i]).append("/");
        }
        filePathToSave.append(fileNameToSave);
        return filePathToSave.toString();
    }

    //} FILE ASSERTION

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

    private void attemptLaunch(){
        if(newProjectLauncher.readyToLaunch()){
            JButton launchButton = new JButton("Launch");
            launchButton.addActionListener((ActionEvent e) -> launch());
            newProjectFileSelectionPanel.add(launchButton, BorderLayout.SOUTH);
        }
    }

    private void launch() {
        SwingUtilities.invokeLater(ProjectUI::new);
        this.dispose();
    }

    private void finalizeNewProjectFileSelection() {
        this.add(newProjectFileSelectionPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        setVisible(true);
    }
}
