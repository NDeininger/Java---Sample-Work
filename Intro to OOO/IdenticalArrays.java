/*
The two-dimensional arrays m1 and m2 are strictly identical if their corresponding elements are equal. 
Write a method that returns true if m1 and m2 are strictly identical.
 */

public class IdenticalArrays {
    public static boolean equals(int[][] m1, int[][] m2) {
		// case 1 - when the length of m1 and m2 are different, they are different: return false
        if(m1.length != m2.length) {
            return false;
        }

		// case 2 - when any of the elements are different, they are different: return false
        for (int i = 0; i < m1.length; i++){
            for (int j = 0; j < m1[i].length; j++) {
                if (m1[i][j] != m2[i][j]) {
                    return false;
                }
            }
        }

		// otherwise they are the same: return true
        return true;
    }

    public static void main(String[] args) {
        final int ROW_SIZE = 2;
        final int COLUMN_SIZE = 2;

        int[][] m1 = {{51, 22}, {25, 6}, {1, 4}, {24, 54}};
        int[][] m2 = {{51, 22}, {25, 6}, {1, 4}, {24, 54}};
        int[][] m3 = {{51, 22}, {25, 61}, {11, 4}, {24, 54}};

        System.out.println(equals(m1, m2)); // they are the same, so print true
        System.out.println(equals(m1, m3)); // they are different, so print false
    }
}

/* output
true
false
 */