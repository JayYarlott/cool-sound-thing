import static java.lang.Math.*;

public class Particle {
    public double x, y, vx, vy;
    public Particle(double x, double y, double vx, double vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }
    public void update() {
        update(new SpeedProfile(new double[]{0, 10}, new double[]{1500, 1500}), 0.001);
        //update(new SpeedProfile(), 0.001);
    }
    public void tupdate(){
        x+=vx*0.001;
        y+=vy*0.001;
    }
    public void update(SpeedProfile speedcalc, double dt) {

        double s1 = speedcalc.speed(y);
        x += vx*dt;
        y += vy*dt;
        double s2 = speedcalc.speed(y);
        double th1 = atan2(vy,vx);
        double th2;
        double temp = s2* sin(th1)/s1;
        System.out.println(cos(asin(temp)));
        if (temp > 1) {
            th2 = PI-th1;
        } else {
            th2 = asin(temp);
        }
        vx = s2*cos(th2);
        vy = s2*sin(th2);
    }

    public int rX(){
        return (int)(x/100) + 250;
    }
    public int rY(){
        return (int)(-y/100) + 250;
    }

    @Override
    public String toString() {
        return "Particle{" +
                "x=" + x +
                ", y=" + y +
                ", vx=" + vx +
                ", vy=" + vy +
                '}';
    }
}