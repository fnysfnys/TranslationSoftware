import java.util.ArrayList;

public class DataFrame {
    private ArrayList<Row> dataFrame;


    public DataFrame(){
        dataFrame = new ArrayList<>();
    }

    public void addRow(String swedishValue, String englishValue){
        Row row = new Row(swedishValue, englishValue);
        dataFrame.add(row);
    }

    public int getRowCount() {
        return dataFrame.size();
    }

    public Row getRow(int index){
        return dataFrame.get(index);
    }
}
