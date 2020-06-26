package FunctionalStuff;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Savestate {
    //default serialVersion id
    private static final long serialVersionUID = 1L;

    //Create SaveData to be modified
    private SaveData savedata = new SaveData();
    private int modifier = 0;
    private SaveManager saveManager;
    private Manager manager;

    public Savestate(Manager manager, boolean isNew) throws IOException {
        this.manager = manager;
    }

    public Map<AttributeNames, Integer> getCurrentAttributes() {
        return savedata.getCurrentAttributes();
    }

    public void setCurrentAttributes(Map<AttributeNames, Integer> currentAttributes) {
        savedata.setCurrentAttributes(currentAttributes);
    }

    public ArrayList<DiceProp> getDps() {
        return savedata.getDps();
    }

    public void setDps(ArrayList<DiceProp> dps) {
        savedata.setDps(dps);
    }

    public void setSaveManager(SaveManager saveManager) {
        this.saveManager = saveManager;
    }

    public SaveManager getSaveManager() {
        return saveManager;
    }

    public int getModifier() {
        return modifier;
    }

    public void setModifier(int modifier) {
        this.modifier = modifier;
    }

    public SaveData getSaveData() {
        return savedata;
    }

    public void setSaveData(SaveData savedata) {
        this.savedata = savedata;
    }
}
