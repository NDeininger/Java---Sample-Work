/*
 Nathan Deininger, Zach Sargent, Will Pond
 December 12th, 2023
 CSC 364 Assignment 6

 Summary: This travel agent program performs the following actions for above flight connection graph:
		  - Asks the client for their starting city and destination city
		  - The client then plans a round trip, which begins from the given start city and ends in the same city, with
	  	  transit cities, if there are no direct connections available.
	  	  The round trip is planned as follows:
		  - The planned forward trip finds the cheapest path from the starting city to the destination
			city, with transit cities in the forward trip, if there are no direct connections available.
		  - Next, the planned return trip finds the cheapest path from the destination city to the starting
			city, with transit cities in the return trip, where none of the transit cities in the forward trip
			(if any), are common with the transit cities on the return trip.
		  - If the start and destination cities are directly connected, the return trip will not be on the
			same directly connected path.
*/



import java.util.Arrays;
import java.util.List;

public class RoundTripPlannerSoln {
	// user inputs for the source and destination
	private int startCityIndex;
	private int endCityIndex;

	// Graph created using the following vertices and edges
	private WeightedGraph<String> flightNetwork;

	// array of vertices
	private String[] cities;
	// array of weighted edges [source][dest][weight]
	private int[][] connections;

	// forward and return route cities lists and cost of trip
	private List<String> forwardRoute;
	private double forwardRouteCost;
	private List<String> returnRoute;
	private double returnRouteCost;

	/*
	 * Constructor:
	 * - Assigns class variables
	 * - Invokes generateRoundTrip() method
	 */
	public RoundTripPlannerSoln(String[] cities, int[][] connections, int startCityIndex, int endCityIndex) {
		//Assign provided input variables to class
		this.cities = cities;
		this.connections = connections;
		this.startCityIndex = startCityIndex;
		this.endCityIndex = endCityIndex;
		//Call generateRoundTrip
		this.generateRoundTrip();
	}


	/*
	 * Round trip generator:
	 * - Creates flight network graph
	 * - Updates forward trip path variable and forward trip cost
	 * - Performs necessary actions for return trip planning
	 * - Updates return trip path variable and return trip cost
	 */
	public void generateRoundTrip() {

		//use the weighted graph class to create a weighted graph with the cities and connections
		WeightedGraph<String> forwardCityGraph = new WeightedGraph<>(this.cities, this.connections);

		//use the weighted graph's getShortestPath method to generate shortestPaths tree
		WeightedGraph<String>.ShortestPathTree forwardShortestPaths = forwardCityGraph.getShortestPath(this.startCityIndex);
		//Store forwardRoute and forwardRouteCost in class variables
		this.forwardRoute = forwardShortestPaths.getPath(this.endCityIndex);
		this.forwardRouteCost = forwardShortestPaths.getCost(this.endCityIndex);

		//System.out.println(this.forwardRoute);
		//System.out.println(this.forwardRouteCost);

		//Get the indicies of the cities in the forward route
		int[] forwardIndicies = new int[this.forwardRoute.size()];
		for (int i = 0; i < this.forwardRoute.size(); i++) {
			for (int j = 0; j < this.cities.length; j++) {
				if (cities[j] == forwardRoute.get(i)) {
					forwardIndicies[i] = j;
					break;
				}
			}
		}

		//Use forwardIndicies to set the returnRoute edge costs to MAX_VALUE so the same route can't be taken home
		for (int i = forwardIndicies.length - 1; i > 0; i--) {
			for (int j = 0; j < connections.length; j++) {
				if (connections[j][0] == forwardIndicies[i] && connections[j][1] == forwardIndicies[i - 1]) {
					connections[j][2] = Integer.MAX_VALUE;
					//System.out.println(forwardIndicies[i]);
					//System.out.println(forwardIndicies[i - 1]);
					//System.out.println(connections[j][2]);
				}
			}
		}

		//use the weighted graph class to create a new weighted graph for the return trip with updated connections
		WeightedGraph<String> returnCityGraph = new WeightedGraph<>(this.cities, this.connections);

		//use the weighted graph's getShortestPath method to generate a new shortestPaths tree for return trip
		WeightedGraph<String>.ShortestPathTree returnShortestPaths = returnCityGraph.getShortestPath(this.endCityIndex);
		//Store returnRoute and returnRouteCost in class variables
		this.returnRoute = returnShortestPaths.getPath(this.startCityIndex);
		this.returnRouteCost = returnShortestPaths.getCost(this.startCityIndex);

		//System.out.println(this.returnRoute);
		//System.out.println(this.returnRouteCost);
	}


	/*
	 * Trip viewer:
	 * - prints forward trip in the format:
	 * "Forward trip from A to B: A �> P �> Q �> R �> B"
	 * - prints return trip in the same format:
	 * "Return trip from B to A: B �> S �> T �> U �> A"
	 * - prints the costs for the forward trip, return trip, and total trip in the format:
	 *  "Forward route cost: 200.0"
	 *  "Return route cost: 300.0"
	 *  "Total trip cost: 500.0"
	 */
	public void printRoundTrip() {
		//print out the forward route
		System.out.print("Forward trip from " + cities[startCityIndex] + " to " + cities[endCityIndex] + ": ");
		for (int i = 0; i < forwardRoute.size() - 1; i++) {
			System.out.print(forwardRoute.get(i) + " -> ");
		}
		System.out.println(forwardRoute.get(forwardRoute.size() - 1));

		//print out the return route
		System.out.print("Return trip from " + cities[endCityIndex] + " to " + cities[startCityIndex] + ": ");
		for (int i = 0; i < returnRoute.size() - 1; i++) {
			System.out.print(returnRoute.get(i) + " -> ");
		}
		System.out.println(returnRoute.get(returnRoute.size() - 1));

		//print out the costs of each trip and total cost
		System.out.println("Forward route cost: " + this.forwardRouteCost);
		System.out.println("Return route cost: " + this.returnRouteCost);
		System.out.println("Total trip cost: " + (this.forwardRouteCost + this.returnRouteCost));

	}

	// Returns the forwardRoute class variable
	public List<String> getForwardRoute() {
		return forwardRoute;
	}

	// Returns the returnRoute class variable
	public List<String> getReturnRoute() {
		return returnRoute;
	}

	// Returns the forwardRouteCost class variable
	public double getForwardRouteCost() {
		return forwardRouteCost;
	}

	// Returns the returnRouteCost class variable
	public double getReturnRouteCost() {
		return returnRouteCost;
	}



}
