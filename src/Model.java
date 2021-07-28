import javax.swing.*;
import java.io.*;

public class Model {

    private DataFrame dataFrame;

    public Model() { }

    public void loadDataFromDoc(String fileName) {
        dataFrame = new DataFrame();
        DataLoader dataLoader = new DataLoader(fileName);
        dataLoader.loadDataToFrame(dataFrame);
    }

    public String[][] getData() {
        int rowCount = dataFrame.getRowCount();
        String[][] data = new String[rowCount][2];
        Row currentRow;
        for (int row = 0; row < rowCount; row++) {
            currentRow = dataFrame.getRow(row);
            data[row][0] = currentRow.getFromValue();
            data[row][1] = currentRow.getToValue();
        }

        return data;
    }

    public void saveProject(String filePathToSave, JTable dataTable) {
        getNonSavedValuesFromTable(dataTable);

        try {
            WriteDataFrame(filePathToSave);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void WriteDataFrame(String filePathToSave) throws IOException {
        FileOutputStream fos = new FileOutputStream(filePathToSave);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(this.dataFrame);

        oos.close();
    }

    private void getNonSavedValuesFromTable(JTable dataTable) {
        int rowCount = dataFrame.getRowCount();
        String currentValue;
        Row currentRow;
        for (int row = 0; row < rowCount; row++) {
            currentRow = dataFrame.getRow(row);
            currentRow.setToValue(((String) dataTable.getValueAt(row, 1)));
        }
    }

    public void loadDataFromProj(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);

            this.dataFrame = (DataFrame) ois.readObject();

            ois.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
