class Fan {
	// set constant values
    public static int SLOW = 1;
	public static int MEDIUM = 2;
    public static int FAST = 3;

	// member fields 
    private int speed = SLOW;
    private boolean on = false;
	private double radius  = 5;
    private String color = "blue";

	// default constructor
	Fan() {

    }

	// accessor methods 
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int newSpeed) {
        speed = newSpeed;
    }
    public boolean getOn() {return on;}
    public void setOn(boolean newOn) {on = newOn;}
    public double getRadius() {return radius;}
    public void setRadius(double newRadius) {radius = newRadius;}
    public String getColor() {return color;}
    public void setColor(String newColor) {color = newColor;}

}

public class FanTest {
    public static void main(String[] args) {
        Fan fan1 = new Fan();
        fan1.setSpeed(Fan.FAST);
        System.out.println(fan1.getSpeed() == Fan.FAST);
        fan1.setRadius(10);
        System.out.println(fan1.getRadius() == 10);
        fan1.setColor("yellow");
        System.out.println(fan1.getColor().equals("yellow"));
        fan1.setOn(true);
        System.out.println(fan1.getOn() == true);
    }
}

/** output
 true
 true
 true
 true
*/ 