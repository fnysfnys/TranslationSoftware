import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ProjectUI extends JFrame {

    private Model model;

    private JPanel translationTablePanel;
    private JTable translationTable;
    private JScrollPane translationTableScrollPane;
    private JMenuBar menuBar;

    private DefaultTableModel tableModel;

    private String[][] tableData;

    public ProjectUI(){
        super("Smart Translate Project (AUTO SAVE: ON)");

        model = ModelFactory.getModel();
        model.loadDocumentToFrame();

        createProjectUI();
        finalizeProjectUI();
    }

    private void createProjectUI() {
        createMenu();
        createTablePanel();
    }

    private void createMenu() {
        menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        JMenuItem saveProjectItem = new JMenuItem("Save Project");
        JMenuItem exportItem = new JMenuItem("Export Translation");

        saveProjectItem.addActionListener((ActionEvent e) -> saveProject());
        exportItem.addActionListener((ActionEvent e) -> exportTranslation());

        fileMenu.add(saveProjectItem);
        fileMenu.add(exportItem);

        menuBar.add(fileMenu);

        this.setJMenuBar(menuBar);
    }

    private void exportTranslation() {
        System.out.println("EXPORTED");
    }

    private void saveProject() {
        model.autoSave(translationTable);
        System.out.println("Saved");
    }

    private void createTablePanel(){
        translationTablePanel = new JPanel(new BorderLayout());
        createTable();
        translationTableScrollPane = new JScrollPane(translationTable);
        translationTablePanel.add(translationTableScrollPane, BorderLayout.CENTER);
        this.add(translationTablePanel);
    }

    private void createTable() {
        createTableModel();

        tableData = model.getTableData();

        tableModel.setDataVector(tableData, new String[]{"Original", "Row"});
        translationTable = new JTable(tableModel);

        translationTable.setDefaultRenderer(String.class, new MultiLineTableCellRenderer());
        createAutoSaveActionListener();
    }

    private void createTableModel() {
        tableModel = new DefaultTableModel() {
            public Class<String> getColumnClass(int columnIndex) {
                return String.class;
            }
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };
    }

    private void createAutoSaveActionListener() {
        translationTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (translationTable.getSelectedRow() > -1) {
                    model.autoSave(translationTable);
                }
            }
        });
    }

    private void finalizeProjectUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }
}
