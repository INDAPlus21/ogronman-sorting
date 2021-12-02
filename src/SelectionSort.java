import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.Timer;

/**
 *
 * @author ozkar
 */
public class SelectionSort extends Sort {

    private int size;
    private Thread t;

    public SelectionSort(ArrayList<Integer> valueList) {
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
        for (int i = 0; i < size - 1; i++) {
            try {
                int min_idx = i;
                Thread.sleep(200);
                for (int j = i + 1; j < size; j++) {
                    if (valueList.get(j) < valueList.get(min_idx)) {
                        min_idx = j;
                    }
                }

                int temp = valueList.get(min_idx);
                valueList.set(min_idx, valueList.get(i));
                valueList.set(i, temp);
                pcs.firePropertyChange("SelectionSort", 1, 2);
            } catch (InterruptedException ex) {

            } catch (java.lang.IndexOutOfBoundsException e) {

            }
        }
        try {
            pcs.firePropertyChange("SelectionSort", 1, 2);
        } catch (java.lang.IndexOutOfBoundsException e) {

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
