import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class OldTranslationsUI extends JFrame {
    private OldTranslationsModel model;

    private JPanel translationTablePanel;
    private JTable translationTable;
    private JScrollPane translationTableScrollPane;
    private JMenuBar menuBar;

    private DefaultTableModel tableModel;

    private String[][] tableData;

    public OldTranslationsUI(){
        super("Old Translation Loader");

        model = OldTranslationsModelFactory.getModel();
        model.loadDocumentsToFrame();

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

        JMenuItem saveTranslationsToMemoryItem = new JMenuItem("Save Translations To Memory");

        saveTranslationsToMemoryItem.addActionListener((ActionEvent e) -> saveTranslationsToMemory());

        menuBar.add(saveTranslationsToMemoryItem);

        this.setJMenuBar(menuBar);
    }

    private void saveTranslationsToMemory() {
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

    private void finalizeProjectUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }
}
