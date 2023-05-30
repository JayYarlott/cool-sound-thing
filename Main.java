import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args){
        var thing = new Display(100, 100);
        BufferedImage im = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        var g = im.createGraphics();
        g.setColor(Color.CYAN);
        g.fillRect(0,0,100,100);
        thing.render(im);
    }

    private void render(ArrayList<Particle> list, Graphics2D g){
        //we assume list is sorted
        for(int i = 0; i<list.size(); i++){
            list.get(i).update();
        }
        int x1 = (int) list.get(0).x;
        int y1 = (int) list.get(0).y;
        for(int i = 1; i<list.size(); i++){
            int x2 = (int) list.get(i).x;
            int y2 = (int) list.get(i).y;
            g.drawLine(x1, y1, x2, y2);
            x1 = x2;
            y1 = y2;
        }
        g.drawLine(x1, y1, (int) list.get(0).x, (int) list.get(0).y);
    }

}
