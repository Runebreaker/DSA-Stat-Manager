package FunctionalStuff;

import GUI_Version.GUI;
import javafx.application.Application;

import java.io.IOException;

public class Manager
{
    private Savestate ss;
    private SaveManager sm;
    private static GUI gui;

    public Manager() throws IOException {
        ss = new Savestate(this, true);
        sm = new SaveManager(this);
        ss.setSaveManager(sm);
        gui.setManager(this);
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        new Thread(() -> Application.launch(GUI.class)).start();
        gui = GUI.waitForGUI();
        Manager manager = new Manager();
    }

    public void changeName(String name) {
        sm.changeName(name);
    }

    public void save() throws IOException {
        sm.save(ss.getSavedata());
    }

    public Object load() throws IOException, ClassNotFoundException {
        return sm.load();
    }

    public void configure() throws IOException {
        sm.configure();
    }

    public void delete() throws IOException {
        sm.delete();
    }

    public void printTest()
    {
        System.out.println("Works!");
    }
}
