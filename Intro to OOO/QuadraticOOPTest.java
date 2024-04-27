import java.util.Scanner;

class QuadraticOOP {

    // member fields 
    private int a, b, c;
    
    // constructor
    QuadraticOOP(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getDiscriminant() {
        return(this.b * this.b - 4 * this.a * this.c);
    }

    public double getSolution1() {
        return((-this.b + Math.pow(this.b * this.b - 4 * this.a * this.c, 0.5)) / (2 * this.a));
    }

    public double getSolution2() {
        return((-this.b - Math.pow(this.b * this.b - 4 * this.a * this.c, 0.5)) / (2 * this.a));
    }
}

public class QuadraticOOPTest {
    public static void main(String[] args) {
        QuadraticOOP q = new QuadraticOOP(1, 3, 1);
        System.out.println(q.getDiscriminant());
        System.out.println(q.getSolution1());
        System.out.println(q.getSolution2());
    }
}

/** Copy output here:
 5.0
 -0.3819660112501051
 -2.618033988749895
 */
