public class Model {

    private DataFrame dataFrame;

    public Model() { }

    public void loadData(String fileName) {
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
            data[row][0] = currentRow.getSwedishValue();
            data[row][1] = currentRow.getEnglishValue();
        }

        return data;
    }
}
