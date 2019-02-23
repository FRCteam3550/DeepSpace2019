package frc.robot.PIDsettings;

public class Constants {

	/**
	 * Which PID slot to pull gains from. Starting 2018, you can choose from
	 * 0,1,2 or 3. Only the first two (0,1) are visible in web-based
	 * configuration.
	 */



	/**
	 * Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops. For
	 * now we just want the primary one.
	 */



	/**
	 * set to zero to skip waiting for confirmation, set to nonzero to wait and
	 * report to DS if action fails.
	 */


    /**
	 * set Cruise Velocity and Acceleration
	 */

	 //slot number 0 
	public static final int kSlotId0 = 0;
	//pid loop number 0
	public static final int kPIDLoopId0 = 0;
	//same for all the slots
	public static final int kTimeoutMs0 = 30;

	//acceleration and velocity for slot 0
	public static final int kCruiseVelocity0 = 20000;
	public static final int kAcceleration0 = 9000;

	//gains for slot0
	public static final Gains kGains0 = new Gains(0.5, 0.0, 0.0, 0.1, 0, 1.0);


	//position 
	public static final double ELEVATORBOTTOM = 200;

//**************************************************************************************************** */

	 //slot number 1 
	public static final int kSlotId1 = 1;
	//pid loop number 1
	public static final int kPIDLoopId1 = 1;
	//same for all the slots
	public static final int kTimeoutMs1 = 30;

	//acceleration and velocity for slot 1
	public static final int kCruiseVelocity1 = 20000;
	public static final int kAcceleration1 = 10000;

	//gains for slot1
	public static final Gains kGains1 = new Gains(2, 0.0, 0.0, 0.1, 0, 1.0);

	public static final double ELEVATORMIDDLE = 4096 * 1.5;

//**************************************************************************************************** */

	 //slot number 2 
	 public static final int kSlotId2 = 2;
	 //pid loop number 2
	 public static final int kPIDLoopId2 = 2;
	 //same for all the slots
	 public static final int kTimeoutMs2 = 30;
 
	 //acceleration and velocity for slot 2
	 public static final int kCruiseVelocity2 = 25000;
	 public static final int kAcceleration2 = 10000;
 
	 //gains for slot2
	 public static final Gains kGains2 = new Gains(2, 0.0, 0.0, 0.1, 0, 1.0);
 
	 public static final double ELEVATORUP = 4096 * 3;



	 //**************************************************************************************************** */

	 //slot number 1 for the arm
	 public static final int kSlotIdArm1 = 1;
	 //pid loop number 2
	 public static final int kPIDLoopIdArm1 = 1;
	 //same for all the slots
	 public static final int kTimeoutMsArm1 = 30;
 
	 //acceleration and velocity for slot 1
	 public static final int kCruiseVelocityArm1 = 25000;
	 public static final int kAccelerationArm1 = 10000;
 
	 //gains for slot1
	 public static final Gains kGainsArm1 = new Gains(2, 0.0, 0.0, 0.1, 0, 1.0);
 
	 public static final double ArmUp = 4096 * 3;



	 	 //**************************************************************************************************** */

	 //slot number 2 for the arm
	 public static final int kSlotIdArm2 = 2;
	 //pid loop number 2
	 public static final int kPIDLoopIdArm2 = 2;
	 //same for all the slots
	 public static final int kTimeoutMsArm2 = 30;
 
	 //acceleration and velocity for slot 2
	 public static final int kCruiseVelocityArm2 = 25000;
	 public static final int kAccelerationArm2 = 10000;
 
	 //gains for slot2
	 public static final Gains kGainsArm2 = new Gains(2, 0.0, 0.0, 0.1, 0, 1.0);
 
	 public static final double ArmDown = 500;


	 	 	 //**************************************************************************************************** */

	 //slot number 1 for the wedger
	 public static final int kSlotIdWedger1 = 1;
	 //pid loop number 1
	 public static final int kPIDLoopIdWedger1  = 1;
	 //same for all the slots
	 public static final int kTimeoutMsWedger1  = 30;
 
	 //acceleration and velocity for slot 1
	 public static final int kCruiseVelocityWedger1  = 25000;
	 public static final int kAccelerationVWedger1  = 10000;
 
	 //gains for slot1
	 public static final Gains kGainsWedger1  = new Gains(2, 0.0, 0.0, 0.1, 0, 1.0);
 
	 public static final double Wedger1  = 0;


//**************************************************************************************************** */

	 //slot number 2 for the wedger
	 public static final int kSlotIdWedger2 = 2;
	 //pid loop number 2
	 public static final int kPIDLoopIdWedger2  = 2;
	 //same for all the slots
	 public static final int kTimeoutMsWedger2  = 30;
 
	 //acceleration and velocity for slot 2
	 public static final int kCruiseVelocityWedger2  = 25000;
	 public static final int kAccelerationVWedger2  = 10000;
 
	 //gains for slot2
	 public static final Gains kGainsWedger2  = new Gains(2, 0.0, 0.0, 0.1, 0, 1.0);
 
	 public static final double Wedger2  = 0;

	 
	 
//**************************************************************************************************** */

	 //slot number 0 for the wedger
	 public static final int kSlotIdWedger0 = 0;
	 //pid loop number 0
	 public static final int kPIDLoopIdWedger0  = 0;
	 //same for all the slots
	 public static final int kTimeoutMsWedger0  = 30;
 
	 //acceleration and velocity for slot 0
	 public static final int kCruiseVelocityWedger0  = 25000;
	 public static final int kAccelerationVWedger0  = 10000;
 
	 //gains for slot0
	 public static final Gains kGainsWedger0  = new Gains(2, 0.0, 0.0, 0.1, 0, 1.0);
 
	 public static final double Wedger0  = 0;


	 
	/**
	 * Gains used in Motion Magic, to be adjusted accordingly
     * Gains(kp, ki, kd, kf, izone, peak output);
     */
}

