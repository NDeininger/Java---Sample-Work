class MyInteger {
    int value = 0;
    MyInteger(int value) {this.value = value;}
    public int getValue() {return this.value;}
    public boolean isEven() {
        if (this.value % 2 == 0) {return true;}
        return false;
    }
    public static boolean isEven(int i) {
        if (i % 2 == 0) {return true;}
        return false;
    }
    public static boolean isEven(MyInteger i) {
        if (i.getValue() % 2 == 0) {return true;}
        return false;
    }
    public boolean isOdd() {
        if (this.value % 2 == 0) {return false;}
        return true;
    }
    public static boolean isOdd(int i) {
        if (i % 2 == 0) {return false;}
        return true;
    }
    public static boolean isOdd(MyInteger i) {
        if (i.getValue() % 2 == 0) {return false;}
        return true;
    }
    public boolean isPrime() {
        if (this.value <= 1) {return false;}
        if (isEven(this.value)) {return false;}
        for (int i = 3; i < this.value; i++) {
            if (this.value % i == 0) {return false;}
        }
        return true;
    }
    public static boolean isPrime(int i) {
        if (i <= 1) {return false;}
        if (isEven(i)) {return false;}
        for (int k = 3; k < i; k++) {
            if (i % k == 0) {return false;}
        }
        return true;
    }
    public static boolean isPrime(MyInteger i) {
        if (i.getValue() <= 1) {return false;}
        if (isEven(i.getValue())) {return false;}
        for (int k = 3; k < i.getValue(); k++) {
            if (i.getValue() % k == 0) {return false;}
        }
        return true;
    }
    public boolean equals(int i) {
        if (i == this.value) {return true;}
        return false;
    }
    public boolean equals(MyInteger i) {
        if (this.value == i.getValue()) {return true;}
        return false;
    }
    public static int parseInt(char[] input) {
        int num = 0;
        for (int i = 0; i < input.length; i++) {
            num = num * 10 + (input[i] - '0');
        }
        return num;
    }
    public static int parseInt(String input) {
        int num = 0;
        for (int i = 0; i < input.length(); i++) {
            num = num * 10 + (input.charAt(i) - '0');
        }
    return num;
    }
}


public class MyIntegerTest {
    public static void main(String[] args) {
        MyInteger n1 = new MyInteger(5);
        System.out.println("n1 is even? " + n1.isEven());
        System.out.println("n1 is prime? " + n1.isPrime());
        System.out.println("15 is prime? " + MyInteger.isPrime(15));

        char[] chars = {'3', '5', '3', '9'};
        System.out.println(MyInteger.parseInt(chars));

        String s = "3539";
        System.out.println(MyInteger.parseInt(s));

        MyInteger n2 = new MyInteger(24);
        System.out.println("n2 is odd? " + n2.isOdd());
        System.out.println("45 is odd? " + MyInteger.isOdd(45));
        System.out.println("n1 is equal to n2? " + n1.equals(n2));
        System.out.println("n1 is equal to 5? " + n1.equals(5));
    }
}
/* Output:
n1 is even? false
n1 is prime? true
15 is prime? false
3539
3539
n2 is odd? false
45 is odd? true
n1 is equal to n2? false
n1 is equal to 5? true
 */