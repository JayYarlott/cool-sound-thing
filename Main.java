import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args){
        int w = 500, h = 500;
        int rwidth = 2000, rheight = 500;
        var thing = new Display(w, h);
        var parts = testset(100);
        thing.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                parts.addAll(spawnAt((e.getX()-250)*100, (e.getY()-250)*100, 100));
            }
        });

        for(int i = 0; i<100000; i++){
            Graphics g = thing.strategy.getDrawGraphics();
            //long time = System.nanoTime();
            render(parts, g);
//            System.out.println("Elapsed millis:" + (System.nanoTime()-time)/1000000.0);
            g.dispose();
            thing.strategy.show();
            //System.out.println("hi"+i);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private static ArrayList<Particle> spawnAt(int x, int y, int n){
        var ret = new ArrayList<Particle>(n);
        double step = 2*Math.PI/n;
        for(int i = 0; i<n; i++){
            ret.add(new Particle(x, y, Math.cos(step*i), Math.sin(step*i)));
        }
        return ret;
    }

    private static ArrayList<Particle> testset(int n){
        var ret = new ArrayList<Particle>(n);
        double step = 2*Math.PI/n;
        for(int i = 0; i<n; i++){
            ret.add(new Particle(0,300, Math.cos(step*i), Math.sin(step*i)));
        }
        return ret;
    }

    private static void render(ArrayList<Particle> list, Graphics g){
        //we assume list is sorted
        ((Graphics2D)g).setStroke(new BasicStroke(3));
        SpeedProfile sp = new SpeedProfile();
        for(int i = 0; i<list.size(); i++){
            list.get(i).update(sp, 0.01);
        }
        g.clearRect(0,0,500,500);
        g.setColor(Color.BLACK);
        Particle l0 = list.get(0);
        int x1 = l0.rX();
        int y1 = l0.rY();
        //System.out.println(x1 + " " + y1);
        for(int i = 1; i<list.size(); i++){
            int x2 = list.get(i).rX();
            int y2 = list.get(i).rY();
            //g.drawLine(x1, y1, x2, y2);
            g.drawLine(x1,y1,x1,y1);
            x1 = x2;
            y1 = y2;
        }
        //g.drawLine(x1, y1, l0.rX(), l0.rY());
        g.drawLine(x1,y1,x1,y1);
    }

}
