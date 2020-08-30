import java.util.*;
//import java.io.*;
//import java.math.*;

class Player {
	public static void main(String args[]) {
		Player myPlayer = new Player(); 
		myPlayer.run();
	}
	
	public Player() {
		me = new Car(-1, -1, true, "-1");
		opponent = new Car(-1, -1, false, "-10");
		nextCheckpoint = new Checkpoint(-1, -1);
		
		nextCheckpointOld = new Checkpoint(-2, -2);
		
		checkpoints = new ArrayList<Checkpoint>();
		
		iGameLoop = 0;
		in = new Scanner(System.in);
		
		test_activatedTime = 0;
	}
	
	int test_activatedTime;
	boolean test_IactivatedShield;
	Car me;
	Car opponent;
	Checkpoint nextCheckpoint; // x, y position of the next check point
	int nextCheckpointDistance = -1; // distance to the next checkpoint
	int nextCheckpointAngle = -1; // angle between your pod orientation and the direction of the next checkpoint
	
	Checkpoint nextCheckpointOld; // x, y position of the last next check point  //ERROR: Switches every gameloop to the values of next checkpoint of last gameloop(only one time different than next checkpoint)

	List<Checkpoint> checkpoints;
	
	int iGameLoop;
	Scanner in;
		
	public void run() {
		
		for (; true; iGameLoop++) {

			getInputs();
			
			String output = calculateOutput();

			System.err.println("My distance to next checkpoint: " + me.distanceTo(nextCheckpoint));		
			
			System.out.println(output);	//output for CodinGame 

			currentToOldVariables();
		}
	}
	
	public String calculateOutput() {
		if(Checkpoint.allKnown) {
			System.err.println("All Checkpoints known!");
			if(nextCheckpointAngle > 90 || nextCheckpointAngle < -90) {
				me.thrust = "0";
			} else {
				me.thrust = "100";
			}
			
			return nextCheckpoint.getX() + " " + nextCheckpoint.getY() + " " + me.thrust;			
		} else {
			if (iGameLoop == 0)
				me.thrust = "100";	//BOOST?
			else if(nextCheckpointAngle > 90 || nextCheckpointAngle < -90 || ((nextCheckpointAngle > 40 || nextCheckpointAngle < -40) && nextCheckpointDistance < 3000))
				me.thrust = "20";
			if (nextCheckpointAngle > 90 || nextCheckpointAngle < -90 || ((nextCheckpointAngle > 40 || nextCheckpointAngle < -40) && nextCheckpointDistance < 2000))
				me.thrust = "0";
			else
				me.thrust = "100";

			return nextCheckpoint.getX() + " " + nextCheckpoint.getY() + " " + me.thrust;
		}
	}

	public void getInputs() {
		me.setPos(in.nextInt(), in.nextInt());
		nextCheckpoint = new Checkpoint(in.nextInt(), in.nextInt()); // x position of the next check point y position of the next check point
		nextCheckpointDistance = in.nextInt(); // distance to the next checkpoint
		nextCheckpointAngle = in.nextInt(); // angle between your pod orientation and the direction of the next
											// checkpoint
		opponent.setPos(in.nextInt(), in.nextInt());

		me.isDirectedToNextCheckpoint = false;

		if (checkpointChanged()) {
			System.err.println("The next Checkpoint changed!");
			if (!Checkpoint.allKnown) {
				System.err.println("It is a new one!");
				if (checkpoints.isEmpty() || !checkpoints.get(0).isSameLocationAs(nextCheckpoint)) { // If not the finish-checkpoint
					checkpoints.add(nextCheckpoint); // Add new checkpoint to my checkpoint-list
				} else {
					Checkpoint.allKnown = true;
				}
			} else {
				//Checkpoints elegant ankurven
			}
		}

	}

	public boolean checkpointChanged() {
		return nextCheckpoint.getX() != nextCheckpointOld.getX() || nextCheckpoint.getY() != nextCheckpointOld.getY();
	}

	public void currentToOldVariables() {
		nextCheckpointOld = new Checkpoint (nextCheckpoint.getX(), nextCheckpoint.getY());
	}
}

class RaceSubject {
	public RaceSubject() {
	}

	public RaceSubject(int x, int y) {
		this.setPos(x, y);
	}
	
	protected int x;
	protected int y;
	
	public void copyFrom(RaceSubject inputThing) {
		this.setPos(inputThing);
	}
	
	public boolean isSameLocationAs(RaceSubject inputThing) {
		return (inputThing.getX() == this.getX() && inputThing.getY() == this.getY()) ;
	}
	
	public int distanceTo(RaceSubject inputThing) {
		return (int) Math.sqrt((Math.pow(inputThing.getX() - this.getX(), 2)) + Math.pow((inputThing.getY() - this.getY()), 2));
	}
	
	public int distanceTo(int targetX, int targetY) {
		return (int) Math.sqrt((Math.pow(targetX - this.getX(), 2)) + Math.pow((targetY - this.getY()), 2));
	}
	
	public void setPos(int newX, int newY) {
		this.setX(newX);
		this.setY(newY);
	}
	
	public void setPos(RaceSubject inputThing) {
		this.setX(inputThing.getX());
		this.setY(inputThing.getY());
	}
	
	protected void setX(int newX) {
		this.x = newX;
	}
	
	public int getX() {
		return this.x;
	}
	
	protected void setY(int newY) {
		this.y = newY;
	}
	
	public int getY() {
		return this.y;
	}
}

class Checkpoint extends RaceSubject {
	public static boolean allKnown = false;
	//static boolean allCheckpointsKnown = false;
	
	public Checkpoint() {
		super();
	}

	public Checkpoint(int x, int y) {
		super(x, y);
	}
}

class Car extends RaceSubject {
	public Car() {
		super();
	}

	public Car(int x, int y, boolean isMe, String thrust) {
		super(x, y);
		this.isDirectedToNextCheckpoint = true;
		this.isMe = isMe;
		this.thrust = thrust;
	}
	
	boolean isMe;
	boolean isDirectedToNextCheckpoint;
	String thrust;
	protected int lastX;
	protected int lastY;
	
	public boolean isOn(Checkpoint checkpoint) {
		return this.distanceTo(checkpoint) <= 600;
	}
	
	public void copyFrom(Car inputCar) {
		super.copyFrom(inputCar);
		this.isMe = inputCar.isMe;
		this.isDirectedToNextCheckpoint = inputCar.isDirectedToNextCheckpoint;
	}
	
	protected void setX (int newX) {
		this.lastX = this.x;
		this.x = newX;
	}
	
	protected void setY(int newY) {
		this.lastY = this.y;
		this.y = newY;
	}
	
	public int getSpeed(){
		return (this.distanceTo(this.lastX, this.lastY));
	}
}