import java.util.Date;

public class DateTest {
    public static void main(String[] args) {
        Date date = new Date();
        int count = 1;
        long time = 10000;

        while (count <= 8) {
            date.setTime(time);
            System.out.println(date.toString());
            count++;
            time *= 10;
        }
        
    }
}
/** output
 Wed Dec 31 19:00:10 EST 1969
 Wed Dec 31 19:01:40 EST 1969
 Wed Dec 31 19:16:40 EST 1969
 Wed Dec 31 21:46:40 EST 1969
 Thu Jan 01 22:46:40 EST 1970
 Mon Jan 12 08:46:40 EST 1970
 Sun Apr 26 13:46:40 EDT 1970
 Sat Mar 03 04:46:40 EST 1973
*/

