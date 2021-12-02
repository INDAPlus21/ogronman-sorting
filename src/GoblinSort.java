import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ozkar
 */
public class GoblinSort extends Sort {

    private int size;
    private Thread t;

    public GoblinSort(ArrayList<Integer> valueList) {
        super(valueList);
        this.type = 0;
        size = valueList.size();
        t = new Thread(this);
    }

    public void start() {
        t.start();
    }

    public void stop() {
        valueList = new ArrayList<>();
        size = 0;
        t.interrupt();
        t.interrupt();
    }

    @Override
    public void run() {
        sort();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void sort() {
        int i = 0;
        try{
        while (i < size) {
            if (i == 0) {
                i++;
            }
            if (valueList.get(i) >= valueList.get(i - 1)) {
                i++;
            } else {
                int temp = 0;
                temp = valueList.get(i);
                valueList.set(i, valueList.get(i-1));
                valueList.set(i-1, temp);
                i--;
                pcs.firePropertyChange("GoblinSort", 1, 2);
                Thread.sleep(50);
            }
        }
        }catch(java.lang.IndexOutOfBoundsException e){
            
        }catch (InterruptedException ex) {
            
        }
    }

    @Override
    public void addProperty(PropertyChangeListener pcl) {
        pcs.addPropertyChangeListener(pcl);
    }

    @Override
    public void removeProperty(PropertyChangeListener pcl) {
        pcs.removePropertyChangeListener(pcl);
    }

}
