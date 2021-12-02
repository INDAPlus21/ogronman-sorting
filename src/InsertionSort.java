import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author ozkar
 */
public class InsertionSort extends Sort {

    private int size;
    private Thread t;

    public InsertionSort(ArrayList<Integer> valueList) {
        super(valueList);
        this.type = 1;
        size = valueList.size();
        t = new Thread(this);
    }

    @Override
    public void run() {
        sort();
    }

    public void start() {
        t.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sort() {
        try {
            for (int i = 1; i < size; ++i) {
                try {
                    Thread.sleep(75);
                } catch (InterruptedException ex) {
                }
                int key = valueList.get(i);
                int j = i - 1;

                while (j >= 0 && valueList.get(j) > key) {
                    valueList.set(j + 1, valueList.get(j));
                    j = j - 1;
                    pcs.firePropertyChange("InsertionSort", 1, 2);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                    }
                }
                valueList.set(j + 1, key);
                pcs.firePropertyChange("InsertionSort", 1, 2);
            }
        } catch (java.lang.IndexOutOfBoundsException e) {
        }
        pcs.firePropertyChange("InsertionSort", 1, 2);
    }

    public void stop() {
        valueList = new ArrayList<>();
        size = 0;
        t.interrupt();
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
