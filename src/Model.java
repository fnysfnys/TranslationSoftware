public class Model {
    public Model() { }

    public void loadData(String fileName) {
        DataLoader dataLoader = new DataLoader(gui, fileName);
    }
}
