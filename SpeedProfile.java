public class SpeedProfile {
    public final double[] sxs = new double[]{0, 10, 20, 30, 50, 75, 100, 125, 150, 200, 250, 300, 400, 500, 600, 700, 800, 900, 1000, 1100, 1200, 1300, 1400, 1500, 1750, 2000};
    public final double[] sys = new double[]{1540.4, 1540.5, 1540.7, 1534.4, 1523.3, 1519.6, 1518.5, 1517.9, 1517.3, 1516.6, 1516.5, 1516.2, 1516.4, 1517.2, 1518.2, 1519.5, 1521, 1522.6, 1524.1, 1525.7, 1527.3, 1529, 1530.7, 1532.4, 1536.7, 1541};
    private double[] xs, ys;
    public SpeedProfile(double[] xs, double[] ys) {
        this.xs = xs;
        this.ys = ys;
    }
    public SpeedProfile() {
        this.xs = sxs;
        this.ys = sys;
    }
    public double speed(int d) {
        int u = 1;
        for (int i = 1; i < xs.length; i++) {
            if (d < xs[i]) {
                u = i;
                break;
            }
        }
        return ys[u-1] + (d-ys[u-1])*(ys[u] - ys[u-1])/(xs[u] - xs[u-1]);
    }
}
