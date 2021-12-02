import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author ozkar
 */
public class SortsPanel extends JPanel {

    ArrayList<Color> colorList = new ArrayList<>();

    public SortsPanel() {
        this.setPreferredSize(new Dimension(400, 400));
    }

    public void setColorList(ArrayList<Color> colorList) {
        this.colorList = colorList;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int j = -1;
        for (int i = 0; i < colorList.size(); i++) {
            if (i % 10 == 0) {
                j++;
            }
            Color newC = new Color(colorList.get(i).getRed(), 0, 0);
            g.setColor(newC);
            g.fillRect(i * 20 % 200, j * 20, 20, 20);
        }
        j = -1;
        for (int i = 0; i < colorList.size(); i++) {
            if (i % 10 == 0) {
                j++;
            }
            Color newC = new Color(0, colorList.get(i).getGreen(), 0);
            g.setColor(newC);
            g.fillRect(i * 20 % 200 + 200, j * 20, 20, 20);
        }
        j = -1;
        for (int i = 0; i < colorList.size(); i++) {
            if (i % 10 == 0) {
                j++;
            }
            Color newC = new Color(0, 0, colorList.get(i).getBlue());
            g.setColor(newC);
            g.fillRect(i * 20 % 200, j * 20 + 200, 20, 20);
        }
        j = -1;
        for (int i = 0; i < colorList.size(); i++) {
            if (i % 10 == 0) {
                j++;
            }
            g.setColor(colorList.get(i));
            g.fillRect(i * 20 % 200 + 200, j * 20 + 200, 20, 20);
        }

    }

}
