import java.lang.Math;
public class Particle {
    public double x, y, vx, vy;
    public Particle(double x, double y, double vx, double vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }
    public void update() {
        update(new SpeedProfile(), 0.001);
    }
    public void update(SpeedProfile speedcalc, double dt) {
        x += vx*dt;
        y += vy*dt;
        
        double s1 = Math.sqrt(vx*vx + vy*vy);
        double s2 = speedcalc.speed(y);
        double th1 = Math.atan(vx/vy);
        double th2;
        double temp = s2*Math.sin(th1)/s1;
        if (temp > 1) {
            th2 = -th1;
        } else {
            th2 = Math.asin(temp);
        }
        vx = s2*Math.sin(th2);
        vy = s2*Math.cos(th2);
    }
}