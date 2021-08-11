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

        JMenuItem saveTranslationsToMemoryItem = new JMenuItem("Save Translations To Memory");

        saveTranslationsToMemoryItem.addActionListener((ActionEvent e) -> saveTranslationsToMemory());

        menuBar.add(saveTranslationsToMemoryItem);

        createAlignmentMenu();

        this.setJMenuBar(menuBar);
    }

    private void createAlignmentMenu() {
        JMenu alignMenu = new JMenu("Align...");

        JMenuItem deleteOriginalItem = new JMenuItem("Delete Original From Selected Row");
        JMenuItem deleteTranslatedItem = new JMenuItem("Delete Translation From Selected Row");

        deleteOriginalItem.addActionListener((ActionEvent e) -> deleteOriginalFromSelectedRow());
        deleteTranslatedItem.addActionListener((ActionEvent e) -> deleteTranslatedFromSelectedRow());

        alignMenu.add(deleteOriginalItem);
        alignMenu.add(deleteTranslatedItem);

        menuBar.add(alignMenu);

    }

    private void deleteTranslatedFromSelectedRow() {
        model.updateFrame(translationTable);
        model.deleteTranslatedFromRow(translationTable.getSelectedRow());
        this.tableData = model.getTableData();
        updateTable();
    }

    private void deleteOriginalFromSelectedRow() {
        model.updateFrame(translationTable);
        model.deleteOriginalFromRow(translationTable.getSelectedRow());
        this.tableData = model.getTableData();
        updateTable();
    }

    private void updateTable() {
        translationTablePanel.remove(translationTableScrollPane);
        createTable();
        translationTableScrollPane = new JScrollPane(translationTable);
        translationTablePanel.add(translationTableScrollPane, BorderLayout.CENTER);

        translationTablePanel.revalidate();
        translationTablePanel.validate();
        translationTablePanel.repaint();
    }


    private void saveTranslationsToMemory() {
        model.saveTranslationsToMemory(translationTable);
        JOptionPane.showMessageDialog(this, "This translation has been saved to your selected memory file.", "Success!", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
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
