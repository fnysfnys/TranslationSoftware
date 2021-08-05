import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class ProjectUI extends JFrame {

    private Model model;

    private JPanel translationTablePanel;
    private JTable translationTable;
    private JScrollPane translationTableScrollPane;

    private DefaultTableModel tableModel;

    private String[][] tableData;

    public ProjectUI(){
        super("Row Project");

        model = ModelFactory.getModel();
        model.loadDocumentToFrame();

        createProjectUI();
        finalizeProjectUI();
    }

    private void createProjectUI() {
        createTablePanel();
    }

    private void createTablePanel(){
        translationTablePanel = new JPanel();
        createTable();
        translationTableScrollPane = new JScrollPane(translationTable);
        translationTablePanel.add(translationTableScrollPane);
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
        setVisible(true);
    }
}
