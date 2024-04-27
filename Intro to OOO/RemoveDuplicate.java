import java.util.*;

public class RemoveDuplicate {
    public static void eliminateDuplicates(ArrayList<Integer> list) {
		ArrayList<Integer> temp = new ArrayList<>();
        //iterate over each list item
        temp.add(list.get(0));
        for (Integer item : list) {
            boolean duplicate = true;
            //check if the item is a duplicate
            //if the item is not a duplicate (i.e. not already in temp), add to temp
            if (!temp.contains(item)) {
                temp.add(item);
            }
        }
        //clear old list, put temp values in list
        list.clear();
        for (Integer added_item : temp) {
            list.add(added_item);
        }
    }
    
    public void test1() {
        // Liang's book page 433. What's the method to 
        Integer array[] = {35, 5, 3, 5, 6, 4, 33, 2, 2, 4};
        ArrayList<Integer> m = new ArrayList<>(Arrays.asList(array));;
        
        eliminateDuplicates(m);
        
        System.out.println(m.toString().equals("[35, 5, 3, 6, 4, 33, 2]"));
    }
    
    public static void main(String[] args) {
        RemoveDuplicate t = new RemoveDuplicate();
        t.test1();
    }
}

/** Make the code to return true
 true
*/
