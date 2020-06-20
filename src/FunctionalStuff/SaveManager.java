package FunctionalStuff;

import java.io.*;

public class SaveManager
{
    private FileOutputStream fout;
    private ObjectOutputStream oout;

    private FileInputStream fin;
    private ObjectInputStream oin;

    private Manager manager;
    private String currentFileName;
    private File currentFile;

    public SaveManager(Manager manager) {
        this.manager = manager;
    }

    public void changeName(String name)
    {
        this.currentFileName = name;
    }

    public void configure() throws IOException {
        if(currentFileName != null)
        {
            currentFile = new File("." + File.separator + "saves" + File.separator + currentFileName + ".ser");
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
            System.out.println("Please enter a file name first.");
        }
    }

    public void save(Object o) throws IOException {
        fout = new FileOutputStream(currentFile);
        oout = new ObjectOutputStream(fout);

        oout.writeObject(o);
        System.out.println("Successfully saved.");

        oout.close();
        fout.close();
    }

    public Object load() throws IOException, ClassNotFoundException {
        fin = new FileInputStream(currentFile);
        oin = new ObjectInputStream(fin);

        Object o = oin.readObject();
        System.out.println("Successfully loaded.");

        oin.close();
        fin.close();
        return o;
    }

    public void delete() throws IOException {
        if(currentFileName != null)
        {
            currentFile = new File("." + File.separator + "saves" + File.separator + currentFileName + ".ser");
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
}
