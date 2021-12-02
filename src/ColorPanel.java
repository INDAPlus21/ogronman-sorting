import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author ozkar
 */
public class ColorPanel extends JPanel {

    ArrayList<Color> colorList = new ArrayList<>();

    public ColorPanel() {
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
            g.setColor(colorList.get(i));
            g.fillRect(i*50 % 500, j*50, 50, 50);
        }

    }

}
