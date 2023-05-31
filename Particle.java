public class Particle {
    public double x, y, vx, vy;
    public Particle(double x, double y, double vx, double vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }
    public void update() {
        x += 0.1*vx;
        y += 0.1*vy;
    }
    public void update(SpeedProfile speedcalc, double dt) {
        double s1 = speedcalc.speed(y);
        x += vx;
        y += vy;
        double angle = Math.atan2(vy,vx);
        double s2 = speedcalc.speed(y);
        double aangle = (s2/s1)*Math.sin(angle);
        vx = Math.cos(angle)*s2;
        vy = Math.sin(angle)*s2;


    }

    public int rX(){
        return (int)x;
    }
    public int rY(){
        return (int)y;
    }

}