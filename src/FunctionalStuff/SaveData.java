package FunctionalStuff;

import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SaveData implements Serializable {
    private Map<AttributeNames, Integer> currentAttributes = new HashMap<>();
    private ArrayList<DiceProp> dps = new ArrayList<>();
    private String characterName;
    private Image characterImage;

    public SaveData()
    {
        for (AttributeNames a : AttributeNames.values()) {
            //Default init of Attribute List
            currentAttributes.put(a, 10);
        }
        //Initialize dice with default sides
        dps.add(new DiceProp(2, 0));
        dps.add(new DiceProp(4, 0));
        dps.add(new DiceProp(8, 0));
        dps.add(new DiceProp(10, 0));
        dps.add(new DiceProp(12, 0));
        dps.add(new DiceProp(20, 0));
        dps.add(new DiceProp(100, 0));
        dps.add(new DiceProp(0, 0)); //Custom sides, used for rolls that dont use default dice

        //Default Values for init
        characterName = "Darius Marno";
        characterImage = new Image("sample.png");
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public Image getCharacterImage() {
        return characterImage;
    }

    public void setCharacterImage(Image characterImage) {
        this.characterImage = characterImage;
    }

    public void setAttribute(AttributeNames a, int value)
    {
        currentAttributes.put(a, value);
    }

    public int getAttribute(AttributeNames a)
    {
        return currentAttributes.getOrDefault(a, -1);
    }

    public void addDiceProp(DiceProp d)
    {
        dps.add(d);
    };

    public Map<AttributeNames, Integer> getCurrentAttributes() {
        return currentAttributes;
    }

    public void setCurrentAttributes(Map<AttributeNames, Integer> currentAttributes) {
        this.currentAttributes = currentAttributes;
    }

    public ArrayList<DiceProp> getDps() {
        return dps;
    }

    public void setDps(ArrayList<DiceProp> dps) {
        this.dps = dps;
    }
}
