import java.util.ArrayList;

public class DataFrame {
    private ArrayList<Translation> translations;
    private String selectedMemoryPath;
    private String sourcePath;

    public DataFrame(){
        translations = new ArrayList<>();
    }

    public String getSelectedMemoryPath() {
        return selectedMemoryPath;
    }

    public void setSelectedMemoryPath(String selectedMemoryPath) {
        this.selectedMemoryPath = selectedMemoryPath;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }
}
