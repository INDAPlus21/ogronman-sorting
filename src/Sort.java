import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

/**
 *
 * @author ozkar
 */
public abstract class Sort implements Runnable, ActionListener {

    protected ArrayList<Integer> valueList;
    protected int type;
    protected PropertyChangeSupport pcs;

    /**
     *
     * @param valueList
     */
    public Sort(ArrayList<Integer> valueList) {
       this.pcs = new PropertyChangeSupport(this);
       this.valueList = new ArrayList<>(valueList);
    }

    public abstract void addProperty(PropertyChangeListener pcl);

    public abstract void removeProperty(PropertyChangeListener pcl);

    public ArrayList<Integer> getValueList() {
        return valueList;
    }
    


    public int getType() {
        return type;
    }

    public abstract void sort();

}
