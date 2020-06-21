package FunctionalStuff;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Consumer;

public class SaveManager
{
    private FileOutputStream fout;
    private ObjectOutputStream oout;

    private FileInputStream fin;
    private ObjectInputStream oin;

    private Manager manager;
    private String currentFileName;
    private File currentFile;
    private String currentSaveDirectory = "." + File.separator + "saves" + File.separator; //Default Save Directory
    private ArrayList<File> files = new ArrayList<>();

    public SaveManager(Manager manager) {
        this.manager = manager;
    }

    public void refresh()
    {
        files = new ArrayList<>();
        updateFiles(new File(getCurrentSaveDirectory()), f -> {
            files.add(f);
            System.out.println("File " + f + " detected.");
        });
    }

    public void updateFiles(File dir, Consumer<File> consumer)
    {
        if (dir.isDirectory()) {
            for (File file1 : Objects.requireNonNull(dir.listFiles())) {
                updateFiles(file1, consumer);
            }
        } else {
            consumer.accept(dir);
        }
    }

    public void changeName(String name)
    {
        this.currentFileName = name;
    }

    public void configure() {
        if(currentFileName != null)
        {
            currentFile = new File(currentSaveDirectory + currentFileName);
        }
        else
        {
            System.out.println("Please enter a file name first.");
        }
    }
    public void createFile() throws IOException {
        if(currentFile != null)
        {
            if(currentFile.createNewFile())
            {
                System.out.println("File " + currentFile.getName() + " created.");
            }
            else
            {
                System.out.println("File already exists.");
            }
        }
        else
        {
            System.out.println("No file selected.");
        }
    }

    public void save(Object o) throws IOException {
        if(currentFileName == null){
            System.out.println("Please enter a file name first.");
        }
        else
        {
            if(currentFile == null || !currentFile.exists()) configure();
            fout = new FileOutputStream(currentFile);
            oout = new ObjectOutputStream(fout);

            oout.writeObject(o);
            System.out.println("Successfully saved.");

            oout.close();
            fout.close();
        }
    }

    public Object load() throws IOException, ClassNotFoundException {
        Object o;
        if(currentFile != null && currentFile.exists())
        {
            fin = new FileInputStream(currentFile);
            oin = new ObjectInputStream(fin);

            o = oin.readObject();
            System.out.println("Successfully loaded.");

            oin.close();
            fin.close();
        }
        else
        {
            o = null;
            System.out.println("File not found.");
        }
        return o;
    }

    public void delete() throws IOException {
        if(currentFileName != null)
        {
            currentFile = new File(currentSaveDirectory + currentFileName);
            if(currentFile.delete())
            {
                System.out.println("File " + currentFile.getName() + " deleted successfully.");
            }
            else
            {
                System.out.println("Failed to delete the file. '" + currentFile.getName() + "' probably doesn't exist!");
            }
        }
        else
        {
            System.out.println("Please enter a file name first.");
        }
    }

    public ArrayList<File> getFiles()
    {
        return files;
    }

    public String getCurrentSaveDirectory() {
        return currentSaveDirectory;
    }

    public void setCurrentSaveDirectory(String currentSaveDirectory) {
        this.currentSaveDirectory = currentSaveDirectory;
    }
}
