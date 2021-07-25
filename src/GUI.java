import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GUI extends JFrame {
    private JPanel dataTablePanel;
    private JTable dataTable;
    private JScrollPane dataTableScrollPane;
    private JFileChooser fc;
    private String[] columnNames = {"English", "Swedish"};
    private Model model;

    public GUI(){
        super("Translate");
        model = new Model();
        createFileChooser();
        attemptFileLoad();
        createGUI();
        finalizeGUI();
    }

    private void createFileChooser() {
        fc = new JFileChooser(".");
        fc.setAcceptAllFileFilterUsed(false);
    }

    private void attemptFileLoad(){
        try{
            loadFile();
        }
        catch (Exception exp){
            displayErrorMessage("Unable to load file into GUI: Improperly formatted or Contains no data.",
                    "File Error");
            attemptFileLoad();
        }
    }

    private void displayErrorMessage(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }

    private void loadFile(){
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION){
            File file = fc.getSelectedFile();
            String filePath = file.getPath();
            model.loadData(this, filePath);
        }
        else{ System.exit(0); }
    }

    private void createGUI() {
        dataTablePanel = new JPanel();
        String[][] data = {{"hi1", "hi1"}, {"hi2", "hi2"}};
        dataTable = new JTable(model.getData(), columnNames);
        dataTableScrollPane = new JScrollPane(dataTable);
        dataTableScrollPane.setPreferredSize(new Dimension(1000, 600));
        dataTablePanel.add(dataTableScrollPane);
    }

    private void finalizeGUI() {
        add(dataTablePanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
