package FunctionalStuff;

import GUI_Version.GUI;
import javafx.application.Application;
import javafx.collections.FXCollections;

import java.io.IOException;

public class Manager
{
    private SaveState ss;
    private SaveManager sm;
    private static GUI gui;

    public Manager() throws IOException {
        ss = new SaveState(this, true);
        sm = new SaveManager(this);
        ss.setSaveManager(sm);
        gui.setManager(this);
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        new Thread(() -> Application.launch(GUI.class)).start();
        gui = GUI.waitForGUI();
        Manager manager = new Manager();
    }

    public void refresh()
    {
        sm.refresh();
        gui.updateListView(FXCollections.observableList(sm.getFiles()));
    }

    public SaveState getSaveState()
    {
        return ss;
    }

    public String getCurrentSaveDirectory()
    {
        return sm.getCurrentSaveDirectory();
    }

    public void changeName(String name) {
        sm.changeName(name);
    }

    public void save() throws IOException {
        sm.save(ss.getSaveData());
    }

    public void load() throws IOException, ClassNotFoundException {
        Object o = sm.load();
        if(o != null)
        {
            ss.setSaveData((SaveData) o);
        }
    }

    public void configure() {
        sm.configure();
    }

    public void createFile() throws IOException {
        sm.createFile();
        refresh();
    }

    public void delete() throws IOException {
        sm.delete();
        refresh();
    }

    public void printTest()
    {
        System.out.println("Works!");
    }
}
