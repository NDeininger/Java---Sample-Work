// Refer to Liang's text book case study
/*
Given a set of points, the closest-pair problem is to find the two points that are nearest to each other. 
In Figure, for example, points (1, 1) and (2, 0.5) are closest to each other. 
There are several ways to solve this problem. 
An intuitive approach is to compute the distances between all pairs of points and find the one with 
the minimum distance, as implemented in Listing 8.3.
 */

public class FindNearestPoints {
    /** Compute the distance between two points (x1, y1) and (x2, y2)*/
    public static double distance(
            double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow((x2-x1), 2) + Math.pow((y2-y1), 2));
    }

    public static void main(String[] args) {
        // Create an array to store points
        double[][] points = {{-1, 3}, {-1, -1}, {1, 1}, {2, 0.5},
                             {2, -1}, {3, 3}, {4, 2}, {4, -0.5}};
        // p1 and p2 are the indices in the points array
        int p1 = 0, p2 = 1; // Initial two points
        double shortestDistance = Integer.MAX_VALUE; // Initialize shortestDistance
			
		// Compute distance for every two points
		for (int i = 0; i < points.length; i++){
            for (int j = i + 1; j < points.length; j++) {
                double dist = distance(points[i][0], points[i][1], points[j][0], points[j][1]);
                if (dist < shortestDistance) {
                    p1 = i;
                    p2 = j;
                    shortestDistance = dist;
                }
            }
        }

        // Display results
        String result = "The closest two points are " +
                "(" + points[p1][0] + ", " + points[p1][1] + ") and (" +
                points[p2][0] + ", " + points[p2][1] + ")";
        System.out.println(result);
	}
}

/* output
The closest two points are (1.0, 1.0) and (2.0, 0.5)
 */