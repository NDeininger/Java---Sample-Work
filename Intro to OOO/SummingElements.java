public class SummingElements {
    public static int summingColumns(int[][] matrix, int column) {
        int sum = 0;
        for (int row = 0; row < matrix.length; row++) {
            sum += matrix[row][column];
        }
        return sum;
    }

    public static int summingRows(int[][] matrix, int row) {
		int sum = 0;
        for (int col = 0; col < matrix[row].length; col++) {
            sum += matrix[row][col];
        }
        return sum;
    }
	
    public static int summingAllElements(int[][] matrix) {
		// you need a nested for loop to get the summation of all elements
		int sum = 0;
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[i].length; j++){
                sum += matrix[i][j];
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[][] matrix1 = {{1,2}, {3,4}, {5,6}, {7, 8}, {9, 10}};
        int result = summingAllElements(matrix1);
        System.out.println(result == (1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 + 10));
        // sum of 0th column elements
        result = summingColumns(matrix1, 0);
        System.out.println(result == (1 + 3 + 5 + 7 + 9));
        // sum of 0th row elements
        result = summingRows(matrix1, 0);
        System.out.println(result == (1 + 2));
    }
}
/* output
true
true
true
 */