import java.math.BigDecimal;

public class ApproximateE {
    public static void getE() {
        BigDecimal e = BigDecimal.ONE;
        BigDecimal item = BigDecimal.ONE;
        for (int i = 1; i <= 1000; i++) {
            item = item.divide(new BigDecimal(i + ""), 25, BigDecimal.ROUND_UP);
            e = e.add(item);
            if (i % 100 == 0) {
                System.out.println("The e is " + e + " for i = " + i);
            }
        }
    }

    public static void main(String[] args) {
        getE();
    }
}

/* Output:
The e is 2.7182818284590452353602960 for i = 100
The e is 2.7182818284590452353603060 for i = 200
The e is 2.7182818284590452353603160 for i = 300
The e is 2.7182818284590452353603260 for i = 400
The e is 2.7182818284590452353603360 for i = 500
The e is 2.7182818284590452353603460 for i = 600
The e is 2.7182818284590452353603560 for i = 700
The e is 2.7182818284590452353603660 for i = 800
The e is 2.7182818284590452353603760 for i = 900
The e is 2.7182818284590452353603860 for i = 1000
*/