import FunctionalStuff.SaveManager;
import FunctionalStuff.Savestate;
import GUI_Version.GUI;
import javafx.application.Application;

import java.io.IOException;

public class Manager
{
    Savestate ss;
    SaveManager sm;

    public Manager(String[] args) throws IOException {
        Application.launch(GUI.class);
        ss = new Savestate(true);
        sm = new SaveManager();
        sm.configure("sample");
    }

    public static void main(String[] args) {
        new Thread(() -> Application.launch(GUI.class)).start();
        GUI gui = GUI.waitForStartUpTest();
    }
}
