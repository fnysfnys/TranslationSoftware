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
    private JMenuBar menuBar;
    private JMenu fileMenu;

    public GUI(){
        super("Translate");
        model = new Model();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        createFileChooser();
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
        else{ displayErrorMessage("can not read this file", "file error"); }
        updateFrame();
    }

    private void updateFrame() {
        dataTablePanel.remove(dataTableScrollPane);
        createTableModel();
        dataTable.setAutoCreateRowSorter(true);
        dataTableScrollPane = new JScrollPane(dataTable);
        dataTableScrollPane.setPreferredSize(new Dimension(1920, 1080));
        dataTablePanel.add(dataTableScrollPane);
        dataTablePanel.revalidate();
        dataTablePanel.validate();
        dataTablePanel.repaint();
    }

    private void createTableModel() {
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
    }

    private void createGUI() {
        createMenu();
        createTable();
    }

    private void createMenu(){
        menuBar = new JMenuBar();
        createFileMenu();
        this.setJMenuBar(menuBar);
    }

    private void createFileMenu() {
        fileMenu = new JMenu("File");
        createLoadMenu();
        createSaveAsMenu();
        createExportMenu();
        menuBar.add(fileMenu);
    }

    private void createLoadMenu() {
        JMenu saveAsMenu = new JMenu("Load...");
        JMenuItem savedProjectItem = new JMenuItem("saved project");
        JMenuItem loadMemoryItem = new JMenuItem("translation memory");
        JMenuItem loadDocumentItem = new JMenuItem("new document");

        saveAsMenu.add(savedProjectItem);
        saveAsMenu.add(loadMemoryItem);
        saveAsMenu.add(loadDocumentItem);

        savedProjectItem.addActionListener((ActionEvent e) -> loadProject());
        loadMemoryItem.addActionListener((ActionEvent e) -> loadMemory());
        loadDocumentItem.addActionListener((ActionEvent e) -> loadDocument());
        fileMenu.add(saveAsMenu);
    }

    private void loadDocument() {
        attemptFileLoad();
    }

    private void loadProject() {
    }

    private void loadMemory() {
        
    }

    private void createExportMenu() {
        JMenu saveAsMenu = new JMenu("Export as...");
        JMenuItem exportAsDocxItem = new JMenuItem("docx file");

        saveAsMenu.add(exportAsDocxItem);
        exportAsDocxItem.addActionListener((ActionEvent e) -> exportAsDocx());
        fileMenu.add(saveAsMenu);
    }

    private void exportAsDocx() {
        System.out.println("exported");
    }

    private void createSaveAsMenu() {
        JMenu saveAsMenu = new JMenu("Save As...");
        JMenuItem writeToJSONItem = new JMenuItem("Translation Project");

        saveAsMenu.add(writeToJSONItem);
        writeToJSONItem.addActionListener((ActionEvent e) -> saveAsTranslationProject());
        fileMenu.add(saveAsMenu);
    }

    private void saveAsTranslationProject() {
        System.out.println("saved.");
    }

    private void createTable() {
        dataTablePanel = new JPanel();

        createTableModel();
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
