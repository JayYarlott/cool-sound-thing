public class Tester {
    public static void main(String[] args){
        var p1 = new Particle(0,0,1500,0);
        var p2 = new Particle(0,0,-1500,0);
        System.out.println(p1);
        System.out.println(p2);
        p1.update();
        p2.update();
        System.out.println(p1);
        System.out.println(p2);
    }
}
