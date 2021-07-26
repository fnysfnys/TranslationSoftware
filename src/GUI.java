import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class GUI extends JFrame {
    private JPanel dataTablePanel;
    private JTable dataTable;
    private JScrollPane dataTableScrollPane;
    private JFileChooser fc;
    private String[] columnNames = {"English", "Swedish"};
    private String[][] data;
    private Model model;

    public GUI(){
        super("Translate");
        model = new Model();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
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
            model.loadData(filePath);
            data = model.getData();
        }
        else{ System.exit(0); }
    }

    private void createGUI() {
        dataTablePanel = new JPanel();

        //dataTable = new JTable(data, columnNames);

        DefaultTableModel dm = new DefaultTableModel() {
            public Class<String> getColumnClass(int columnIndex) {
                return String.class;
            }
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };

        dm.setDataVector(data, new String[]{"From", "To"});
        dataTable = new JTable(dm);

        dataTable.setDefaultRenderer(String.class, new MultiLineTableCellRenderer());
        dataTableScrollPane = new JScrollPane(dataTable);
        dataTableScrollPane.setPreferredSize(new Dimension(1920, 1080));
        dataTablePanel.add(dataTableScrollPane);
    }

    private void finalizeGUI() {
        add(dataTablePanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
