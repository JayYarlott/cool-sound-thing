public class Tester {
    public static void main(String[] args){
        /*
        var p1 = new Particle(0,300,1500,-100);
        var p2 = new Particle(0,0,-1500,0);
        System.out.println(p1);
        //System.out.println(p2);
        for (int i=0; i < 1000;i++) {
            p1.update();
            //p2.update();
            System.out.println(p1);
            //System.out.println(p2);
        }
        */
        SpeedProfile sp = new SpeedProfile(new double[]{0, 10, 20}, new double[]{5, 10, 5});
        for (int i = 0; i < 21; i++) {
            System.out.println(sp.speed(i));
        }
    }
}
