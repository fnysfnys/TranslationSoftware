import java.awt.*;
import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.io.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class GUI extends JFrame {
    private final JPanel dataViewPanel;
    private JPanel dataTablePanel;
    private JTable dataTable;
    private JScrollPane dataTableScrollPane;
    private JFileChooser fc;
    private String[] columnNames = {"English", "Swedish"};
    private String[][] data;
    private Model model;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JPanel infoDisplayPanel;
    private JLabel lastSaveLabel;

    public GUI(){
        super("Translate");
        model = new Model();
        dataViewPanel = new JPanel(new BorderLayout());
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
            model.loadDataFromDoc(filePath);
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

        addAutoSaveActionListener();

        dataTablePanel.revalidate();
        dataTablePanel.validate();
        dataTablePanel.repaint();
    }

    private void addAutoSaveActionListener() {
        dataTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (dataTable.getSelectedRow() > -1) {
                    if(model.autoSavable()){
                        
                    }
                }
            }
        });
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
        addAutoSaveActionListener();
    }

    private void createGUI() {
        createMenu();
        createTable();
        createInfoDisplay();
    }

    private void createInfoDisplay() {
        infoDisplayPanel = new JPanel(new BorderLayout());
        lastSaveLabel = new JLabel("Last Saved: " + getCurrentDateTime());
        JLabel memoryLabel = new JLabel("Active Translation Memory: --");
        infoDisplayPanel.add(lastSaveLabel, BorderLayout.WEST);
        infoDisplayPanel.add(memoryLabel, BorderLayout.EAST);
        dataViewPanel.add(infoDisplayPanel, BorderLayout.SOUTH);
    }

    private String getCurrentDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    private void createMenu(){
        menuBar = new JMenuBar();
        createFileMenu();
        this.setJMenuBar(menuBar);
    }

    private void createFileMenu() {
        fileMenu = new JMenu("File");
        createNewMenu();
        createLoadMenu();
        createSaveAsMenu();
        createExportMenu();
        menuBar.add(fileMenu);
    }

    private void createNewMenu() {
        JMenu newMenu = new JMenu("new...");
        JMenuItem newDocumentItem = new JMenuItem("new document");
        JMenuItem newTranslationMemoryItem = new JMenuItem("new translation memory");

        newMenu.add(newTranslationMemoryItem);
        newMenu.add(newDocumentItem);

        newDocumentItem.addActionListener((ActionEvent e) -> loadDocument());
        newTranslationMemoryItem.addActionListener((ActionEvent e) -> createNewMemory());

        fileMenu.add(newMenu);
    }

    private void createNewMemory() {
        JFileChooser fc = new JFileChooser(".");
        fc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("CSV Files", "csv");
        fc.addChoosableFileFilter(csvFilter);
        int returnVal = fc.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            String newMemoryFilePath = file.getPath();
            model.createMemory(newMemoryFilePath);
        }
    }

    private void createLoadMenu() {
        JMenu loadMenu = new JMenu("Load...");
        JMenuItem savedProjectItem = new JMenuItem("saved project");
        JMenuItem loadMemoryItem = new JMenuItem("translation memory");

        loadMenu.add(savedProjectItem);
        loadMenu.add(loadMemoryItem);

        savedProjectItem.addActionListener((ActionEvent e) -> loadProject());
        loadMemoryItem.addActionListener((ActionEvent e) -> loadMemory());

        fileMenu.add(loadMenu);
    }

    private void loadDocument() {
        attemptFileLoad();
    }

    private void loadProject() {
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION){
            File file = fc.getSelectedFile();
            String filePath = file.getPath();
            model.loadDataFromProj(filePath);
            data = model.getData();
        }
        else{
            displayErrorMessage("can not read this file", "file error");
        }
        updateFrame();
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
        JFileChooser fc = new JFileChooser(".");
        fc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter csvFilter = new FileNameExtensionFilter("TMP Files", "tmp");
        fc.addChoosableFileFilter(csvFilter);
        int returnVal = fc.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            String filePathToSave = file.getPath();
            model.saveProject(filePathToSave, dataTable);
        }
        updateSaveDate();
    }

    private void updateSaveDate() {
        infoDisplayPanel.remove(lastSaveLabel);
        lastSaveLabel = new JLabel("Last Save: " + getCurrentDateTime());
        infoDisplayPanel.add(lastSaveLabel, BorderLayout.WEST);

    }

    private void createTable() {
        dataTablePanel = new JPanel();

        createTableModel();
        dataTableScrollPane = new JScrollPane(dataTable);
        dataTableScrollPane.setPreferredSize(new Dimension(1920, 1080));
        dataTablePanel.add(dataTableScrollPane);
        dataViewPanel.add(dataTablePanel, BorderLayout.CENTER);
    }

    private void finalizeGUI() {
        this.add(dataViewPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
