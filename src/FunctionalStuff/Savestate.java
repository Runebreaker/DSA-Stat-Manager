package FunctionalStuff;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Savestate implements Serializable
{
    //default serialVersion id
    private static final long serialVersionUID = 1L;

    //New Hashmap where the 1st integer is the Attribute ID and the 2nd is the value
    private Map<Integer, Integer> currentAttributes = new HashMap<>();
    private ArrayList<DiceProp> dps = new ArrayList<>();

    public Savestate(boolean isNew)
    {
        if(isNew)
        {
            for(AttributeNames a : AttributeNames.values())
            {
                //Default init of Attribute List
                currentAttributes.put(a.getValue(), 10);
            }
            //Initialize dice with default sides
            dps.add(new DiceProp(1, 0));
            dps.add(new DiceProp(2, 0));
            dps.add(new DiceProp(4, 0));
            dps.add(new DiceProp(8, 0));
            dps.add(new DiceProp(10, 0));
            dps.add(new DiceProp(12, 0));
            dps.add(new DiceProp(20, 0));
            dps.add(new DiceProp(100, 0));
        }
    }

    public Map<Integer, Integer> getCurrentAttributes() {
        return currentAttributes;
    }

    public void setCurrentAttributes(Map<Integer, Integer> currentAttributes) {
        this.currentAttributes = currentAttributes;
    }

    public ArrayList<DiceProp> getDps() {
        return dps;
    }

    public void setDps(ArrayList<DiceProp> dps) {
        this.dps = dps;
    }
}
