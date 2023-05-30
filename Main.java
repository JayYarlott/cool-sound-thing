import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {

    public static void main(String[] args){
        var thing = new Display(100, 100);
        BufferedImage im = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        var g = im.createGraphics();
        g.setColor(Color.CYAN);
        g.fillRect(0,0,100,100);
        thing.render(im);
    }

}
