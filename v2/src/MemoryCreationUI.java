import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MemoryCreationUI extends JFrame {
    private JPanel memoryCreationPanel;
    private JPanel labelPanel;
    private JPanel optionsPanel;

    private JLabel pathLabel;
    private JLabel directionLabel;

    private JButton setMemoryPathButton;
    private JButton goButton;

    private JComboBox setDirectionComboBox;

    private String[] directions = {"English -> Swedish", "Swedish -> English"};

    private String memoryFilePath;

    private String direction = "English -> Swedish";

    public MemoryCreationUI(){
        super("Create Memory");
        this.setPreferredSize(new Dimension(650, 150));
        memoryCreationPanel = new JPanel(new BorderLayout());

        createMemoryCreationUI();
        finalizeMemoryCreationUI();
    }

    private void createMemoryCreationUI() {
        createLabels();
        createOptions();
    }

    private void createOptions() {
        optionsPanel = new JPanel();

        setMemoryPathButton = new JButton("Select Memory Path");

        setDirectionComboBox = new JComboBox<>(directions);

        setMemoryPathButton.addActionListener((ActionEvent e) -> createMemoryPath());

        setDirectionComboBox.addActionListener((ActionEvent e) -> changeDirection());

        optionsPanel.add(setMemoryPathButton);
        optionsPanel.add(setDirectionComboBox);

        memoryCreationPanel.add(optionsPanel, BorderLayout.CENTER);
    }

    private void changeDirection() {
        direction = (String) setDirectionComboBox.getSelectedItem();
        directionLabel.setText("Translation Direction: " + direction);
    }

    private void createMemoryPath() {
        JFileChooser fc = new JFileChooser(".");
        fc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("Translation Memory Files", "tmem");
        fc.addChoosableFileFilter(csvFilter);
        int returnVal = fc.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            String filePath = file.getPath();
            filePath = assertFileType(filePath, ".tmem");
            pathLabel.setText("Memory File Path: " + filePath);
            this.memoryFilePath = filePath;
            createGoButton();
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

    private void createGoButton() {
        goButton = new JButton("Create New Memory");
        goButton.addActionListener((ActionEvent e) -> createNewMemory());

        memoryCreationPanel.add(goButton, BorderLayout.SOUTH);
    }

    private void createNewMemory() {
        TranslationMemory translationMemory = new TranslationMemory(direction);
        try {
            writeMemory(translationMemory);
            JOptionPane.showMessageDialog(this, "This new memory has been created and saved to your computer.", "Success!", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(this, "Unable to Save memory. Please try again later, or verify the file path is correct.", "Unable to Save memory", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void writeMemory(TranslationMemory translationMemory) throws IOException {
        FileOutputStream fos = new FileOutputStream(memoryFilePath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(translationMemory);

        oos.close();
    }

    private void createLabels() {
        labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        pathLabel = new JLabel("Memory File Path: --");
        directionLabel = new JLabel("Translation Direction: English -> Swedish");

        labelPanel.add(pathLabel);
        labelPanel.add(directionLabel);

        memoryCreationPanel.add(labelPanel, BorderLayout.NORTH);
    }

    private void finalizeMemoryCreationUI() {
        this.add(memoryCreationPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        setVisible(true);
    }
}
