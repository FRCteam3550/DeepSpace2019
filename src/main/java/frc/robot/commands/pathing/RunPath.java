package frc.robot.commands.pathing;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import javax.print.attribute.standard.PrinterName;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import TronixLib.RecordDataTronix;

/**
 *
 */
/**
 * @author Robotics
 *
 */
public class RunPath extends Command {
	private final double arcDivisor = 5*15;

	private double leftSpeed = 0;
	private double rightSpeed = 0;
	private double startTime = 0;
	
	private double length = -1;
	
	private boolean reset = true;
	
	private ArrayList<Double> tparameterlist;
	private ArrayList<Double> pathCompletedlist;
	private ArrayList<Double>derivativelist;
	private ArrayList<Float> timelist;
	private float startCounter;
	
    private ArrayList<Double> nextSlopelist;
    private ArrayList<Float> timegyrolist;
    private float startGyroCounter;
    
    private ArrayList<Double> gyroAnglelist;
    private ArrayList<Double> gyroSlopelist;
    private ArrayList<Float> timeBezierlist;
    private float startBezierCounter;
    
    private ArrayList<Double> speedList;
    
	private Path path;
	
	private  PrintWriter dataPath;  
	private Function<Double, Double> speed;
	
	//private Animation animation; //added on july 26 th
	
    public RunPath(Path path, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_DriveBaseSub);
    	this.path = path;
    	this.leftSpeed = -speed;
    	this.rightSpeed = -speed;
    	this.speed = x -> speed;
    	this.setInterruptible(false); //defined in wpilib Command class
    }
    
    public RunPath(Path path, double speed, boolean reset) {
    	this(path, speed);
    	this.reset = reset;
    }
    
    public RunPath(Path path, Function<Double, Double> speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_DriveBaseSub);
    	this.path = path;
    	this.speed = speed;
    	this.leftSpeed = speed.apply(0.0);
    	this.rightSpeed = speed.apply(0.0);
    }
    
    public RunPath(Path path, SpeedGenerator speedGenerator) {
    	this(path, speedGenerator.getSpeedFunction(path));
    }
    
	/*
	public RunPath(Path path, Function<Double, Double> speed, Animation animation) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_DriveBaseSub);
    	this.path = path;
    	this.speed = speed;
    	this.leftSpeed = speed.apply(0.0);
    	this.rightSpeed = speed.apply(0.0);
    	this.animation = animation;
    	this.setInterruptible(false);
    } */
    
    
    public RunPath(Path path, Function<Double, Double> speed, boolean reset) {
    	this(path, speed);
    	this.reset = reset;
    }
    
    /**
	 * Instantiates a new drive path.
	 *
	 * @param startPoint
	 *            The start point
	 * @param controlPoint1
	 *            The control point 1
	 * @param controlPoint2
	 *            The control point 2
	 * @param endPoint
	 *            The end point
	 * @param timeOut
	 *            The time out in seconds
	 * @param speed
	 *            The speed the robot will travel at (0.0 - 1.0)
	 * @param reverse
	 *            True if robot will traverse path in reverse, otherwise false
	 *
	 *dxdy :
	 *finds the PathSegment our robot is currently following and calculates the 
	 *derivative at the given s value, which is the distance we have traveled 
	 *across the entire Path. 
     *            
	 */
    
	public double dydx(double s) {
		System.out.println("Call getPath with distance = " + s);
		
		//timelist.add((float) TimeUnit.NANOSECONDS.toSeconds((long) ((float)(System.nanoTime())-startCounter)));
		timelist.add(((float)(System.nanoTime()))-startCounter);
		PathSegment segment = path.getPathSegmentAtDistance(s); //the method name should be getPathSegmentAtDistance(s) 
		double derivevalue = segment.getDerivative().apply((s - path.getTotalOfCompletedPaths(s))/segment.getLength());
		pathCompletedlist.add(s-path.getTotalOfCompletedPaths(s));
		tparameterlist.add((s - path.getTotalOfCompletedPaths(s))/segment.getLength());
		double chassisAngle = Math.tan((Robot.m_DriveBaseSub.getAhrs().getYaw())* Math.PI / 180); //in radians
		gyroSlopelist.add(chassisAngle);
		derivativelist.add(derivevalue);
		return segment.getDerivative().apply((s - path.getTotalOfCompletedPaths(s))/segment.getLength());
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	startCounter = (float) System.nanoTime();
    	
    	//Robot.driveTrain.tankDrive(leftSpeed, rightSpeed);
    	Robot.m_DriveBaseSub.tankDrive(0, 0);
    	Robot.m_DriveBaseSub.resetEncoders();
    	Robot.m_DriveBaseSub.getAhrs().reset();
    	
    	tparameterlist = new ArrayList<>();
    	derivativelist = new ArrayList<>();
    	timelist =  new ArrayList<>();
    	pathCompletedlist = new ArrayList<>();
    	nextSlopelist = new ArrayList<>();
        timegyrolist = new ArrayList<>();
        
        gyroAnglelist = new ArrayList<>();
        gyroSlopelist = new ArrayList<>();
        timeBezierlist = new ArrayList<>();
        
        speedList      = new ArrayList<>();
        
    	System.out.println("RUNPATH INIT");
    	
    }
    
    private double getDistance() {
    	return Math.abs((Robot.m_DriveBaseSub.getDistance()));
    	//return Math.abs(Robot.driveTrain.getLeftEncoderDistance());
    }
  
    /**
     * We calculate error using the method 
     * deltaAngle
     * 
     * @param currentAngle = gyro yaw as an argument
     * 
     * @return  error
     * currentSlope = Math.tan(currentAngle) 
     *nextSlope = dydx(getDistance())
     *To find the error, in degrees, we use the previously shown equation:
     *angle = Math.atan((nextSlope - currentSlope)/(1 + currentSlope * nextSlope))
     *This angle is what is returned by deltaAngle.
     * 
     * 
     */
    
    private double deltaAngle(double currentAngle) {
    	double currentSlope = Math.tan(currentAngle * Math.PI / 180); //convert the gyro reading into radians for calculations
    	double nextSlope = dydx(getDistance()); ///
    	//derivativelist.add(nextSlope);
    	nextSlopelist.add(nextSlope);
    	timeBezierlist.add(((float)(System.nanoTime()))-startBezierCounter);
    	double angle = Math.atan((nextSlope - currentSlope)/(1 + currentSlope * nextSlope))*180/Math.PI;
    	//gyroSlopelist.add(currentSlope);
    	System.out.println("m1: " + currentSlope + " m2: " + nextSlope + " dTheta: " + angle);
    	System.out.println("Encoder: " + getDistance() + " dydx: " + dydx(getDistance()));
    	return angle;  // in radians
    }
    
    public double speed() {
    System.out.println("RUN Speed Init");
    System.out.println("RUN getTotalLenght in Speed:  " +path.getTotalLength());
    System.out.println("RUN getDistance in Speed:  " +getDistance());
   	System.out.println("RUN speed in Speed:  " + (-speed.apply(getDistance()/path.getTotalLength())));
   	    speedList.add((getDistance()/path.getTotalLength()));
    	return -speed.apply(getDistance()/path.getTotalLength());
    }
    public static void recordPathingData(List<Float> dataTime,List<Double> data, String filename1,String filename2) {

    	try { 
    		//DataOutputStream dos= new DataOutputStream(new BufferedOutputStream(new FileOutputStream(filename1))); 
    		FilterOutputStream fileOutputStream1 = new FilterOutputStream(new BufferedOutputStream(new FileOutputStream(filename1)));
    		FilterOutputStream fileOutputStream = new FilterOutputStream(new BufferedOutputStream(new FileOutputStream(filename2)));
    		
    		PrintStream printStream1 = new PrintStream(fileOutputStream1);
    		PrintStream printStream = new PrintStream(fileOutputStream);
    		
    		for(int i=0;i<data.size();i++){ 
    		//System.out.println("before"+data.get(i)); 
    		printStream1.println(dataTime.get(i));
    		printStream1.println(" ");
    		printStream.println(data.get(i));
    		printStream.println(" ");
    		//dos.writeDouble(data.get(i));
    		//System.out.println("After"+data.get(i)); 
    		} 
    		printStream1.close();
    		printStream.close();
    		//dos.close(); 
    		
    		} catch (IOException e) { 
    			System.out.println("cant create newDatafile");
    		e.printStackTrace(); 
    		} 
        System.out.println("Data saved.");
    	
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber ("AngleinRunPath", Robot.m_DriveBaseSub.getAhrs().getYaw());
    	SmartDashboard.putNumber ("DistanceinRunPath", Robot.m_DriveBaseSub.getLeftDistance());
    	double error = -deltaAngle(Robot.m_DriveBaseSub.getAhrs().getYaw());
    	
    	gyroAnglelist.add((double) Robot.m_DriveBaseSub.getAhrs().getYaw());
    	//timegyrolist.add(((float)(System.nanoTime()))-startGyroCounter);
        //timegyrolist = new ArrayList<>();
    	
    	leftSpeed = speed();
    	rightSpeed = speed();
    	
    	System.out.println("error: " + error);
    	if(Math.abs(getDistance()) > 1) {
    		double speed = leftSpeed;
    		// speedList.add(-speed);
        	Robot.m_DriveBaseSub.tankDrive(
        			(-(leftSpeed+((error)/(arcDivisor/Math.abs(speed))))), 
        			(-(rightSpeed-(((error)/(arcDivisor/Math.abs(speed)))))));
        	// animate based off of distance, from 0.0 to 1.0
        	/*if(animation != null) {
        		animation.animate(getDistance() / path.getTotalLength());
        		
        		for(Keyframe kf : animation) {
//            		addSequential(kf.getCommandConsumer().getCommand());
            	}
        	} */
        	
    	} else {
        	Robot.m_DriveBaseSub.tankDrive(-0.75*leftSpeed, -0.75*rightSpeed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	try {
//        	System.out.println(path.getPathAtDistance(Robot.drive.getRightDistance()).getLength());
            return Math.abs(getDistance()) > (path.getTotalLength());
    	} catch (Exception e) {
    		System.err.println("Unexpected error in RunPath.isFinished()");
    		System.err.println(e);
    		return true;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_DriveBaseSub.stopRobot();
    	//if(animation != null)
    	//	animation.reset();
		Robot.m_RecordDataTronix.RecordDataToFile(timelist,gyroAnglelist,"/home/lvuser/gyrolistTime.txt","/home/lvuser/GyroAnglelist.txt");
    	Robot.m_RecordDataTronix.RecordDataToFile(timelist,gyroSlopelist,"/home/lvuser/gyrolistTime.txt","/home/lvuser/GyroSlopelist.txt");
    	Robot.m_RecordDataTronix.RecordDataToFile(timelist,derivativelist,"/home/lvuser/nextSlopeTime.txt","/home/lvuser/derivativelist.txt");
    	Robot.m_RecordDataTronix.RecordDataToFile(timelist,tparameterlist,"/home/lvuser/tparameterTime.txt","/home/lvuser/tparameterlist.txt");
    	Robot.m_RecordDataTronix.RecordDataToFile(timelist, pathCompletedlist,"/home/lvuser/tparameterTime.txt","/home/lvuser/pathCompletedlist.txt");                  
    	Robot.m_RecordDataTronix.RecordDataToFile(timelist,speedList,"/home/lvuser/speedTime.txt","/home/lvuser/speedlist.txt");
    	System.out.println("FinalAngle: " + Robot.m_DriveBaseSub.getAhrs().getYaw());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("RUNPATH INTERRUPTED");
    	end();
    }
}
