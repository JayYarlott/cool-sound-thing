import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Display extends JFrame {

    BufferedImage image = null;
    BufferStrategy strategy = null;
//    JPanel panel = new JPanel(){
//        @Override
//        protected void paintComponent(Graphics g) {
//            super.paintComponent(g);
//            if(image != null)
//                g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
//        }
//    };

    public Display(int width, int height){
        //add(panel);
        setSize(width, height);
        //panel.setSize(width, height);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        createBufferStrategy(2);
        strategy = getBufferStrategy();
//        panel.setVisible(true);
    }

    public void render(BufferedImage bi){
        Graphics g = strategy.getDrawGraphics();
        g.drawImage(bi, 0, 0, bi.getWidth(), bi.getHeight(), this);
        g.dispose();
        strategy.show();
    }

}
