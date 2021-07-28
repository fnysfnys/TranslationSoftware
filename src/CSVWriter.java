import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {
    public CSVWriter(){ }

    public void createMemoryFile(String newMemoryFilePath) {
        try{
            FileWriter fw = new FileWriter(newMemoryFilePath);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("To, From\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
