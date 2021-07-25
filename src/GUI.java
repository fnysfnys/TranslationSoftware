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

    public GUI(){
        super("Translate");
        createGUI();
        finalizeGUI();
    }

    private void createGUI() {
        dataTablePanel = new JPanel();
        String[][] data = {{"hi1", "hi1"}, {"hi2", "hi2"}};
        String[] columnNames = {"English", "Swedish"};
        dataTable = new JTable(data, columnNames);
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
