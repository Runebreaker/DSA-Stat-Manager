package FunctionalStuff;

import java.io.*;

public class SaveManager
{
    private FileOutputStream fout;
    private ObjectOutputStream oout;

    private FileInputStream fin;
    private ObjectInputStream oin;

    private Manager manager;
    private File currentFile;

    public SaveManager(Manager manager) {
        this.manager = manager;
    }

    public void configure(String name) throws IOException {
        currentFile = new File("./saves/" + name + ".ser");

        fout = new FileOutputStream(currentFile.getName());
        oout = new ObjectOutputStream(fout);

        fin = new FileInputStream(currentFile.getName());
        oin = new ObjectInputStream(fin);
    }

    public void create() throws IOException {
        if(!currentFile.exists())
        {
            System.out.println("File " + currentFile.getName() + " not found. Creating file...");
            if (currentFile.createNewFile()) {
                System.out.println("File created: " + currentFile.getName());
            }
        }
        else
        {
            System.out.println("File " + currentFile.getName() + " already exists.");
        }
    }

    public void save(Object o) throws IOException {
        oout.writeObject(o);
    }

    public Object load() throws IOException, ClassNotFoundException {
        return oin.readObject();
    }

    public void delete()
    {
        if(currentFile.delete())
        {
            System.out.println("File " + currentFile.getName() + " deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the file. '" + currentFile.getName() + "' probably doesn't exist!");
        }
    }
}
