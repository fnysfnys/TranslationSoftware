import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StartPageUI extends JFrame {
    private JPanel startPagePanel;
    private JLabel startMessageDisplayLabel;
    private JPanel optionsPanel;

    private JButton newProjectButton;
    private JButton loadProjectButton;
    private JButton loadFilesToMemoryButton;

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
        loadFilesToMemoryButton = new JButton("Load Old Translations To Memory");

        newProjectButton.setPreferredSize(buttonDimensions);
        loadProjectButton.setPreferredSize(buttonDimensions);
        loadFilesToMemoryButton.setPreferredSize(buttonDimensions);

        newProjectButton.addActionListener((ActionEvent e) -> newProject());
        loadProjectButton.addActionListener((ActionEvent e) -> loadProject());
        loadFilesToMemoryButton.addActionListener((ActionEvent e) -> loadFilesToMemory());

        optionsPanel.add(newProjectButton);
        optionsPanel.add(loadProjectButton);
        optionsPanel.add(loadFilesToMemoryButton);

        startPagePanel.add(optionsPanel, BorderLayout.SOUTH);
    }

    private void newProject() {
        SwingUtilities.invokeLater(NewProjectFileSelectionUI::new);
        this.dispose();
    }

    private void loadProject() {
        System.out.println("load proj");
    }

    private void loadFilesToMemory() {
        System.out.println("load files to mem");
    }

    private void finalizeStartPage() {
        this.add(startPagePanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        setVisible(true);
    }
}
