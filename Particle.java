import static java.lang.Math.*;

public class Particle {
    static int number = 0;
    public double x, y, vx, vy, id;
    public Particle(double x, double y, double vx, double vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        id = number++;
    }
    public void update() {
        update(new SpeedProfile(new double[]{0, 300, 600}, new double[]{1500, 100, 1500}), 0.001);
        //update(new SpeedProfile(), 0.001);
    }
    public void tupdate(){
        x+=vx*0.001;
        y+=vy*0.001;
    }
    public void update(SpeedProfile speedcalc, double dt) {
        int mx = vx < 0 ? -1 : 1;
        int my = vy < 0 ? -1 : 1;
        double s1 = speedcalc.speed(y);
        x += vx*dt;
        y += vy*dt;
        double s2 = speedcalc.speed(y);
        double th1 = atan(mx*vx/my/vy);
        if(id > 500)
            System.out.println(th1);
        double th2;
        double temp = s2 * sin(th1)/s1;
        if (temp > 1) {
            vy *= -1;
        } else {
            th2 = asin(temp);
            vx = s2*sin(th2)*mx;
            vy = s2*cos(th2)*my;
        }

    }

    public int rX(){
        return (int)(x/100) + 250;
    }
    public int rY(){
        return (int)(y/100) + 250;
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