package FunctionalStuff;

import java.io.*;

public class SaveManager
{
    private FileOutputStream fout;
    private ObjectOutputStream oout;

    private FileInputStream fin;
    private ObjectInputStream oin;

    public SaveManager() throws IOException {

    }

    public void configure(String name) throws IOException {
        fout = new FileOutputStream("saves/" + name + ".ser");
        oout = new ObjectOutputStream(fout);

        fin = new FileInputStream("saves/" + name + ".ser");
        oin = new ObjectInputStream(fin);
    }

    public void save(Object o) throws IOException {
        oout.writeObject(o);
    }

    public Object load() throws IOException, ClassNotFoundException {
        return oin.readObject();
    }
}
