import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author ozkar
 */
public class OgronmanSorting extends JFrame implements PropertyChangeListener {

    private class keyDispatcher implements KeyEventDispatcher {

        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                if (e.getKeyCode() == 82) {
                    restart();
                    addColorListPanel();
                    multiPanel.repaint();
                } else if (e.getKeyCode() == 73) {
                    red.doClick();
                    addColorListPanel();
                    multiPanel.repaint();
                } else if (e.getKeyCode() == 83) {
                    green.doClick();
                    addColorListPanel();
                    multiPanel.repaint();
                } else if (e.getKeyCode() == 77) {
                    blue.doClick();
                    addColorListPanel();
                    multiPanel.repaint();
                } else if (e.getKeyCode() == 65) {
                    all.doClick();
                    addColorListPanel();
                    multiPanel.repaint();
                } else if (e.getKeyCode() == 78) {
                    if (newFrame == false) {
                        newFrame = true;
                        JFrame allSorts = new JFrame();
                        allSorts.setTitle("Side by side");
                        allSorts.setResizable(false);
                        sortingPanel = new SortsPanel();
                        allSorts.add(sortingPanel);

                        allSorts.setVisible(true);
                        allSorts.pack();
                        addColorListPanel();
                        multiPanel.repaint();
                        
                        allSorts.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowOpened(WindowEvent e) {
                                
                            }

                            @Override
                            public void windowClosing(WindowEvent e) {
                                newFrame = false;
                            }
                        });
                        
                    }
                } else if (e.getKeyCode() == 71) {
                    if (newGoblin == false) {
                        newGoblin = true;
                        createGoblin();
                        JFrame goblinFrame = new JFrame();
                        goblinFrame.setResizable(false);
                        goblinFrame.setTitle("Gnome sort");
                        gobPanel = new GoblinPanel();
                        goblinFrame.add(gobPanel);

                        goblinFrame.setVisible(true);
                        goblinFrame.pack();
                        addColorListPanel();
                        multiPanel.repaint();
                        
                        goblinFrame.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowOpened(WindowEvent e) {
                                
                            }

                            @Override
                            public void windowClosing(WindowEvent e) {
                                newGoblin = false;
                            }
                        });
                        
                    }
                }
            }
            addColorListPanel();
            multiPanel.repaint();
            return false;
        }

    }

    private ArrayList<Integer> list = new ArrayList<>();
    private SelectionSort selection;
    private InsertionSort insertion;
    private MergeSort merge;
    private GoblinSort goblin;

    private ColorPanel multiPanel;
    private GoblinPanel gobPanel;

    private SortsPanel sortingPanel;

    private boolean newFrame = false;
    private boolean newGoblin = false;

    private int showColor = 4;

    private JButton red;
    private JButton green;
    private JButton blue;
    private JButton all;

    public OgronmanSorting() throws HeadlessException {

        this.setPreferredSize(new Dimension(1000, 540));
        this.setLayout(new GridLayout(1, 2));
        this.setResizable(false);

        KeyboardFocusManager keyManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        keyManager.addKeyEventDispatcher(new keyDispatcher());

        red = new JButton("Show only Insertion-sort");
        red.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showColor = 0;
                addColorListPanel();
                multiPanel.repaint();
            }
        });
        green = new JButton("Show only Selection-sort");
        green.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showColor = 1;
                addColorListPanel();
                multiPanel.repaint();
            }
        });
        blue = new JButton("Show only Merge-sort");
        blue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showColor = 2;
                addColorListPanel();
                multiPanel.repaint();
            }
        });
        all = new JButton("Show all colours");
        all.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showColor = 3;
                addColorListPanel();
                multiPanel.repaint();
            }
        });

        JPanel buttonPanel = new JPanel();
        
        this.setTitle("Main window");

        buttonPanel.setLayout(new GridLayout(2, 2));

        buttonPanel.add(red);
        buttonPanel.add(green);
        buttonPanel.add(blue);
        buttonPanel.add(all);

        multiPanel = new ColorPanel();
        add(multiPanel);
        addColorListPanel();
        multiPanel.repaint();

        add(buttonPanel);

        createLists();
        selection = new SelectionSort(list);
        insertion = new InsertionSort(list);
        merge = new MergeSort(list);

        selection.addProperty(this);
        insertion.addProperty(this);
        merge.addProperty(this);

        selection.start();
        insertion.start();
        merge.start();

        pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            OgronmanSorting o = new OgronmanSorting();
    }

    public void createLists() {
        Random tempR = new Random();
        long seed = tempR.nextLong();
        Random r = new Random(seed);

        for (int i = 0; i < 100; i++) {
            int value = r.nextInt(255);
            list.add(value);
        }
    }

    public void createGoblin() {
        goblin = new GoblinSort(list);
        goblin.addProperty(this);
        goblin.start();
        addColorListPanel();
    }

    public void restart() {

        selection.stop();
        insertion.stop();
        merge.stop();

        list = new ArrayList<>();

        createLists();
        selection = new SelectionSort(list);
        insertion = new InsertionSort(list);
        merge = new MergeSort(list);

        addColorListPanel();
        multiPanel.repaint();

        selection.start();
        insertion.start();
        merge.start();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        addColorListPanel();
        multiPanel.repaint();
    }

    private synchronized void addColorListPanel() {
        ArrayList<Color> colorList = new ArrayList<>();
        ArrayList<Color> colorList2 = new ArrayList<>();
        ArrayList<Color> colorListGob = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Color c = null;
            if (showColor == 0) {
                c = new Color(insertion.getValueList().get(i), 0, 0);
            } else if (showColor == 1) {
                c = new Color(0, selection.getValueList().get(i), 0);
            } else if (showColor == 2) {
                c = new Color(0, 0, merge.getValueList().get(i));
            } else {
                c = new Color(insertion.getValueList().get(i), selection.getValueList().get(i), merge.getValueList().get(i));
            }
            Color c2 = new Color(insertion.getValueList().get(i), selection.getValueList().get(i), merge.getValueList().get(i));
            if (goblin != null) {
                Color c3 = new Color(0, goblin.getValueList().get(i), goblin.getValueList().get(i));
                colorListGob.add(c3);
            }
            colorList.add(c);
            colorList2.add(c2);

        }
        multiPanel.setColorList(colorList);
        multiPanel.repaint();
        if (newFrame == true && sortingPanel != null) {
            sortingPanel.setColorList(colorList2);
            sortingPanel.repaint();
        }
        if (newGoblin == true && gobPanel != null) {
            gobPanel.setColorList(colorListGob);
            gobPanel.repaint();
        }
    }

}
