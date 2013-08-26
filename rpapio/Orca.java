package rpapio;
import robocode.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;
import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * Orca - a robot by (Daniel Bastos)
 */
public class Orca extends AdvancedRobot
{
	double energy,distance;
	double gun;
	int direction;
	private byte moveDirection = 1;
	/**
	 * run: Orca's default behavior
	 */
	public void run() {
		// Initialization of the robot should be put here

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		 setColors(Color.black,Color.white,Color.black); // body,gun,radar

		// Robot main loop
		direction=1;
		setAdjustRadarForRobotTurn(true);
		setAdjustGunForRobotTurn(true);
		energy=0;
		distance=0;
		while(true) {
			// Replace the next 4 lines with any behavior you would like
		//	setTurnLeft(10000);
		setTurnRadarRight(360);
		    //turnRadarRightRadians(Double.POSITIVE_INFINITY);
	//	doMove();
		execute();	
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		
		gun=normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getGunHeading()));

		setTurnGunRight(gun);
		execute();
		setTurnRadarRight(normalRelativeAngleDegrees(getHeading() - getRadarHeading() + e.getBearing()));
		//setTurnRight(e.getBearing() + 90);
		fire(3);
		doMove(e.getBearing());
		
	
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		back(10);
		
	}
	

	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		//moveDirection *= -1;
		
	}	
	
public void doMove(double bearing) {

	// switch directions if we've stopped
	if (getVelocity() == 0)
		moveDirection *= -1;

	// circle our enemy
	setTurnRight(bearing+ 90);
	setAhead(500 * moveDirection);
}

}
