import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Display extends JFrame {

    BufferedImage image = null;

    JPanel panel = new JPanel(){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(image != null)
                g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        }
    };

    public Display(int width, int height){
        add(panel);
        setSize(width, height);
        panel.setSize(width, height);
        setDefaultCloseOperation(3);
        setVisible(true);
        panel.setVisible(true);
    }

    public void render(BufferedImage bi){
        image = bi;
        repaint();
    }

}