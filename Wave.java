import java.awt.*;
import java.util.ArrayList;

public class Wave {

    Particle[] particles;
    static SpeedProfile profile = new SpeedProfile();
    static double delta = 0.01;
    int ttl = 1500;
    Wave(Particle o, int num) {
        this(o.x, o.y, num);
    }

    Wave(double x, double y, int num) {
        particles = new Particle[num];
        double step = 2*Math.PI/num;
        double s = profile.speed(y);
        for(int i = 0; i<num; i++){
            particles[i] = new Particle(x, y, s*Math.cos(step*i), s*Math.sin(step*i));
        }
    }

    public void step(){
        ttl-=1;
        for(int i = 0; i<particles.length; i++){
            particles[i].update(profile, delta);
        }
    }

    public static void setDelta(double d){
        delta = d;
    }

    public static void setSpeedProfile(SpeedProfile sp){
        profile = sp;
    }

    public void render(Graphics g, boolean lines){
        //we assume list is sorted
        //((Graphics2D)g).setStroke(new BasicStroke(3));

        Particle l0 = particles[0];
        int x1 = l0.rX();
        int y1 = l0.rY();
        for(int i = 1; i < particles.length; i++){
            int x2 = particles[i].rX();
            int y2 = particles[i].rY();
            //g.drawLine(x1, y1, x2, y2);
            g.drawLine(x1,y1,x1,y1);
            x1 = x2;
            y1 = y2;
        }
        //g.drawLine(x1, y1, l0.rX(), l0.rY());
        g.drawLine(x1,y1,x1,y1);
    }
}
