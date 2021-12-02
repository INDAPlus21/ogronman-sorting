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
public class MergeSort extends Sort {

    private Timer mainTimer;
    private Timer secondaryTimer;
    private int size;
    private int counter;
    private int innerCounter;
    private boolean doInnerLoop = false;
    private Thread t;
    private ArrayList<Integer> inArray;

    public MergeSort(ArrayList<Integer> valueList) {
        super(valueList);
        this.type = 3;
        mainTimer = new Timer(100, this);
        secondaryTimer = new Timer(100, this);
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

    public void merge(int left, int rigjt) {

        if (left < rigjt && (rigjt - left) >= 1) {
            int mid = (rigjt + left) / 2;
            merge(left, mid);
            merge(mid + 1, rigjt);

            combine(left, mid, rigjt);
        }
    }

    public void combine(int left, int middle, int right) {
        try {
            ArrayList<Integer> mergedSortedArray = new ArrayList<Integer>();

            int leftIndex = left;
            int rightIndex = middle + 1;

            while (leftIndex <= middle && rightIndex <= right) {
                if (inArray.get(leftIndex) <= inArray.get(rightIndex)) {
                    mergedSortedArray.add(inArray.get(leftIndex));
                    leftIndex++;
                    pcs.firePropertyChange("MergeSort", 1, 2);
                } else {
                    mergedSortedArray.add(inArray.get(rightIndex));
                    rightIndex++;
                    pcs.firePropertyChange("MergeSort", 1, 2);
                }
            }

            while (leftIndex <= middle) {
                mergedSortedArray.add(inArray.get(leftIndex));
                leftIndex++;
                pcs.firePropertyChange("MergeSort", 1, 2);
            }

            while (rightIndex <= right) {
                mergedSortedArray.add(inArray.get(rightIndex));
                rightIndex++;
                pcs.firePropertyChange("MergeSort", 1, 2);
            }

            int i = 0;
            int j = left;
            valueList = inArray;
            pcs.firePropertyChange("MergeSort", 1, 2);
            while (i < mergedSortedArray.size()) {
                valueList.set(j, mergedSortedArray.get(i++));
                pcs.firePropertyChange("MergeSort", 1, 2);
                j++;
                try {
                    Thread.sleep(55);
                } catch (InterruptedException ex) {
                }
            }
        } catch (java.lang.IndexOutOfBoundsException e) {
        }
        pcs.firePropertyChange("MergeSort", 1, 2);
    }

    public void stop() {
        valueList = new ArrayList<>();
        size = 0;
        t.interrupt();
    }

    @Override
    public void sort() {
        inArray = new ArrayList<>(valueList);
        merge(0, valueList.size() - 1);
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
