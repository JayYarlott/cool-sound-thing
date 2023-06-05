import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class Simulation {
    Display display;
    ArrayList<Wave> waves = new ArrayList<Wave>();
    SpeedProfile sp = new SpeedProfile();
    int[] gradient;
    Simulation(int width, int height){
        display = new Display(width, height);
        gradient = calculateGradient();
        var parts = testset(100);
        display.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent e) {
                waves.add(new Wave(screenToSim(e.getX()), screenToSim(e.getY()), 100));
            }
        });

        for(int i = 0; i<100000; i++){
            Graphics g = display.strategy.getDrawGraphics();
            //long time = System.nanoTime();
            //render(parts, g);
            render(g);
//            System.out.println("Elapsed millis:" + (System.nanoTime()-time)/1000000.0);
            g.dispose();
            display.strategy.show();
            //System.out.println("hi"+i);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    int[] calculateGradient(){
        double[] vals = new double[display.getHeight()];
        for(int i=0; i<vals.length;i++){
            vals[i] = sp.speed(screenToSim(i));
        }
        double max = Arrays.stream(vals).max().getAsDouble();
        double min = Arrays.stream(vals).min().getAsDouble();
        int[] ret = new int[vals.length];
        for(int i=0; i<ret.length;i++){
            ret[i] = (int)((vals[i]-min) / (max-min) * 255);
        }
        return ret;
    }

    public static void main(String[] args){
        new Simulation(500,500);
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

    private void render(Graphics g){
        int w = display.getWidth();
        int h = display.getHeight();
        //we assume list is sorted
        Graphics2D g2 = (Graphics2D) g;
        //g2.setStroke(new BasicStroke(3));
        g2.clearRect(0,0,w,h);
        g2.fill(new Rectangle(0,0,w, h));
        for(int i = 0; i < h; i++){
            g.setColor(new Color(255-gradient[i], 255-gradient[i], 255));
            g.drawLine(0, i,w,i);
        }
        g2.setStroke(new BasicStroke(3));
        g.setColor(Color.BLACK);
        ArrayList<Wave> dead = new ArrayList<Wave>();
        for(int i = 0; i < waves.size(); i++){
            waves.get(i).step();
            waves.get(i).render(g, false);
            if(waves.get(i).ttl<1)
                dead.add(waves.get(i));
        }
        waves.removeAll(dead);
    }

    double screenToSim(double screen){
        return (screen-250)*100;
    }
    double simToScreen(double sim){
        return (sim/100)+250;
    }

}
