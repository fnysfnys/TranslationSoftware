import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class StartPageUI extends JFrame {
    private JPanel startPagePanel;
    private JLabel startMessageDisplayLabel;
    private JPanel optionsPanel;

    private JButton newProjectButton;
    private JButton loadProjectButton;
    private JButton loadFilesToMemoryButton;
    private JButton createMemoryButton;

    public StartPageUI(){
        super("Smart Translate");

        startPagePanel = new JPanel(new BorderLayout());
        createStartPage();
        finalizeStartPage();
    }

    private void createStartPage() {
        startMessageDisplayLabel = new JLabel("Welcome to Smart Translate, please select what you want to do", SwingConstants.CENTER);
        startPagePanel.add(startMessageDisplayLabel, BorderLayout.NORTH);

        createOptionButtons();
    }

    private void createOptionButtons(){
        optionsPanel = new JPanel();

        Dimension buttonDimensions = new Dimension(280,25);
        newProjectButton = new JButton("New SmartTranslate Project");
        loadProjectButton = new JButton("Load SmartTranslate Project");
        createMemoryButton = new JButton("Create New Translation Memory");
        loadFilesToMemoryButton = new JButton("Load Old Translations To Memory");

        newProjectButton.setPreferredSize(buttonDimensions);
        loadProjectButton.setPreferredSize(buttonDimensions);
        createMemoryButton.setPreferredSize(buttonDimensions);
        loadFilesToMemoryButton.setPreferredSize(buttonDimensions);

        newProjectButton.addActionListener((ActionEvent e) -> newProject());
        loadProjectButton.addActionListener((ActionEvent e) -> loadProject());
        createMemoryButton.addActionListener((ActionEvent e) -> createMemory());
        loadFilesToMemoryButton.addActionListener((ActionEvent e) -> loadFilesToMemory());

        optionsPanel.add(newProjectButton);
        optionsPanel.add(loadProjectButton);
        optionsPanel.add(createMemoryButton);
        optionsPanel.add(loadFilesToMemoryButton);

        startPagePanel.add(optionsPanel, BorderLayout.SOUTH);
    }

    private void createMemory() {
        SwingUtilities.invokeLater(MemoryCreationUI::new);
    }

    private void newProject() {
        SwingUtilities.invokeLater(NewProjectFileSelectionUI::new);
        this.dispose();
    }

    private void loadProject() {
        JFileChooser fc = new JFileChooser(".");
        fc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("TMP Files", "tmp");
        fc.addChoosableFileFilter(csvFilter);
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            String filePath = file.getPath();
            SavedProjectLauncher savedProjectLauncher = new SavedProjectLauncher();
            savedProjectLauncher.LaunchSavedProject(filePath);
            SwingUtilities.invokeLater(ProjectUI::new);
            this.dispose();
        }
    }

    private void loadFilesToMemory() {
        SwingUtilities.invokeLater(LoadOldTranslationsFileSelectionUI::new);
    }

    private void finalizeStartPage() {
        this.add(startPagePanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        setVisible(true);
    }
}
