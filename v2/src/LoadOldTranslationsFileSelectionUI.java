import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class LoadOldTranslationsFileSelectionUI extends JFrame {

    private NewProjectLauncher newProjectLauncher;

    private JPanel loadOldTranslationsPanel;
    private JPanel labelPanel;
    private JPanel buttonsPanel;

    private JLabel originalPathLabel;
    private JLabel translationPathLabel;
    private JLabel memoryPathLabel;

    private JButton getOriginalFileButton;
    private JButton getTranslationFileButton;
    private JButton selectMemoryButton;


    public LoadOldTranslationsFileSelectionUI(){
        super("New Project");
        this.setPreferredSize(new Dimension(650, 150));

        loadOldTranslationsPanel = new JPanel(new BorderLayout());

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

        getOriginalFileButton = new JButton("Select Original File");
        getTranslationFileButton = new JButton("Select Translation File");
        selectMemoryButton = new JButton("Select Memory");

        getOriginalFileButton.addActionListener((ActionEvent e) -> getOriginalFile());
        getTranslationFileButton.addActionListener((ActionEvent e) -> getTranslationFile());
        selectMemoryButton.addActionListener((ActionEvent e) -> selectMemory());

        buttonsPanel.add(getOriginalFileButton);
        buttonsPanel.add(getTranslationFileButton);
        buttonsPanel.add(selectMemoryButton);

        loadOldTranslationsPanel.add(buttonsPanel, BorderLayout.CENTER);
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
            memoryPathLabel.setText("Memory File Path: " + filePath);
            newProjectLauncher.setMemory(filePath);
            attemptLaunch();
        }
    }

    private void getOriginalFile() {
        JFileChooser fc = new JFileChooser(".");
        fc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("DOCX Files", "docx");
        fc.addChoosableFileFilter(csvFilter);
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            String filePath = file.getPath();
            originalPathLabel.setText("Original File Path: " + filePath);
            newProjectLauncher.getDocument(filePath);
            attemptLaunch();
        }
    }

    private void getTranslationFile() {
        JFileChooser fc = new JFileChooser(".");
        fc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("TMP Files", "tmp");
        fc.addChoosableFileFilter(csvFilter);
        String projectPath;
        int returnVal = fc.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            projectPath = file.getPath();
            translationPathLabel.setText("Translation File Path: " + projectPath);
            newProjectLauncher.setProjectPath(projectPath);
            attemptLaunch();
        }
    }

    private void createLabels() {
        labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        originalPathLabel = new JLabel("Original File Path: --");
        translationPathLabel = new JLabel("Translation File Path: --");
        memoryPathLabel = new JLabel("Memory Path: --");

        labelPanel.add(originalPathLabel);
        labelPanel.add(translationPathLabel);
        labelPanel.add(memoryPathLabel);

        loadOldTranslationsPanel.add(labelPanel, BorderLayout.NORTH);
    }

    private void attemptLaunch(){
        if(newProjectLauncher.readyToLaunch()){
            JButton launchButton = new JButton("Launch");
            launchButton.addActionListener((ActionEvent e) -> launch());
            loadOldTranslationsPanel.add(launchButton, BorderLayout.SOUTH);
        }
    }

    private void launch() {
        SwingUtilities.invokeLater(ProjectUI::new);
        this.dispose();
    }

    private void finalizeNewProjectFileSelection() {
        this.add(loadOldTranslationsPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        setVisible(true);
    }
}
