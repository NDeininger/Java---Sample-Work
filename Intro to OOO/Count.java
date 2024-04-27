import java.util.Random;

class CountUtil {
    int[][] values;
    int min = 99;
    int max = 0;
    int countEven = 0;
    int countOdd = 0;
    int sum = 0;
    int[] partialSum;
    int[] histValues;

    CountUtil(int row, int column) {
        values = new int[row][column];
        partialSum = new int[3];
        histValues = new int[10];
    }

    public int generate0To99() {
        int randomNum = 0 + (int)(100 * Math.random());
        return randomNum;
    }
    public void compute() {
        //fill values with random ints
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 100; j++) {
                values[i][j] = generate0To99();
            }
        }

        //traverse all elements in values, collect and compute all required information
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[i].length; j++) {
                //fil histValues with information to generate histogram
                if (values[i][j] >= 0 && values[i][j] <= 9) {
                    histValues[0] += 1;
                } else if (values[i][j] >= 10 && values[i][j] <= 19) {
                    histValues[1] += 1;
                } else if (values[i][j] >= 20 && values[i][j] <= 29) {
                    histValues[2] += 1;
                } else if (values[i][j] >= 30 && values[i][j] <= 39) {
                    histValues[3] += 1;
                } else if (values[i][j] >= 40 && values[i][j] <= 49) {
                    histValues[4] += 1;
                } else if (values[i][j] >= 50 && values[i][j] <= 59) {
                    histValues[5] += 1;
                } else if (values[i][j] >= 60 && values[i][j] <= 69) {
                    histValues[6] += 1;
                } else if (values[i][j] >= 70 && values[i][j] <= 79) {
                    histValues[7] += 1;
                } else if (values[i][j] >= 80 && values[i][j] <= 89) {
                    histValues[8] += 1;
                } else if (values[i][j] >= 90 && values[i][j] <= 99) {
                    histValues[9] += 1;
                }

                //compute other information
                if (values[i][j] < min) {
                    min = values[i][j];
                }
                if (values[i][j] > max) {
                    max = values[i][j];
                }

                partialSum[i] += values[i][j];
                sum += values[i][j];

                if (values[i][j] % 2 == 0) {
                    countEven++;
                }
                else if (values[i][j] % 2 != 0) {
                    countOdd++;
                }
            }
        }
    }

    public void displayOneHistogram(String title, int value) {
        String bar = "";
        for (int i = 0; i < value; i++) {
            bar += "*";
        }
        System.out.printf("%s : %s\n", title, bar);

    }
    public void displayHistogram() {
        String title = "";
        for (int i = 0; i < histValues.length; i++) {
            title = String.format("%02d - %02d", i * 10, i * 10 + 9);
            displayOneHistogram(title, histValues[i]);
        }
    }
}
class BetterCountUtil extends CountUtil {
    BetterCountUtil(int row, int column) {
        super(row, column);
    }
    @Override
    public int generate0To99() {
        Random r = new Random();
        int rand = r.nextInt(100);
        return rand;
    }
    public int squareValues() {
        int squared_sum = 0;
        for (int i = 0; i < this.values.length; i++) {
            for (int j = 0; j < this.values[i].length; j++) {
                squared_sum += this.values[i][j] * this.values[i][j];
            }
        }
        return squared_sum;
    }
}
public class Count {
    public static void main(String[] args) {
        BetterCountUtil c = new BetterCountUtil(3, 100);
        c.compute();
        System.out.println("Squared sum is : " + c.squareValues());
        System.out.println("The smallest value is : " + c.min);
        System.out.println("The largest value is : " + c.max);
        System.out.println("The count of even values : " + c.countEven);
        System.out.println("The count of odd values is : " + c.countOdd);
        System.out.println("----------");
        System.out.println("The sum0 : " + c.partialSum[0]);
        System.out.println("The sum1 : " + c.partialSum[1]);
        System.out.println("The sum2 : " + c.partialSum[2]);
        System.out.println("The total sum : " + c.sum);
        System.out.println("----------");
        c.displayHistogram();
    }
}

/**
        Squared sum is : 1011776
        The smallest value is : 0
        The largest value is : 99
        The count of even values : 152
        The count of odd values is : 148
        ----------
        The sum0 : 5222
        The sum1 : 4720
        The sum2 : 5094
        The total sum : 15036
        ----------
        00 - 09 : ********************************
        10 - 19 : ***************************
        20 - 29 : ****************************
        30 - 39 : *********************************
        40 - 49 : *************************************
        50 - 59 : *******************
        60 - 69 : ******************************
        70 - 79 : *****************************
        80 - 89 : ****************************
        90 - 99 : *************************************
*/