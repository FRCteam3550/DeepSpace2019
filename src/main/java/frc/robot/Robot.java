/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser; 
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.cameraserver.*;
//import frc.robot.subsystems.Arm;
import frc.robot.subsystems.DriveBaseSub;
import frc.robot.subsystems.GrimpeurSub;
import frc.robot.subsystems.ShooterSub;
//import frc.robot.subsystems.LidarSub;
//import edu.wpi.first.wpilibj.I2C;
//import edu.wpi.first.wpilibj.I2C.Port;
//import java.nio.ByteBuffer;
//import java.nio.IntBuffer;
import frc.robot.subsystems.WedgerSub;
import edu.wpi.first.wpilibj.DriverStation;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  //public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
  public static OI m_oi;
  public static DriveBaseSub m_DriveBaseSub;
  public static RobotMap m_RobotMap;
 // public static LidarSub m_Lidar;
 public static WedgerSub m_wedger;
 public static ShooterSub shooter;

  public static DriverStation m_driverStation;
  public static GrimpeurSub m_grimpeur;
  public static String alliance;
  //private IntBuffer status;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_RobotMap = new RobotMap();
    m_grimpeur = new GrimpeurSub();
    //m_arm = new Arm();
   // m_armSub = new ArmSub();
    m_DriveBaseSub = new DriveBaseSub();
    m_wedger = new WedgerSub();
    shooter = new ShooterSub();
  

    m_driverStation = DriverStation.getInstance();
    DriverStation.Alliance color = m_driverStation.getAlliance();
    alliance = "invalid";
    
    //for test 
    //color = DriverStation.Alliance.Red;
    //color = DriverStation.Alliance.Blue;

    if (color == Alliance.Invalid) {
      RobotMap.ledController.setSpeed(0.02);
      //pulsating heartbeat pattern (possibly)
      alliance = "none";
    }
    if (color == Alliance.Blue) {
      RobotMap.ledController.setSpeed(0.87);
      //static blue color
      alliance = "blue";
    } 
    else if (color == Alliance.Red) {
      RobotMap.ledController.setSpeed(0.61);
      //static red color
      alliance = "red";
    }

   // else {
   //   RobotMap.ledController.setSpeed(0.02);
   // }

   SmartDashboard.putString("AllianceID", alliance);

    CameraServer.getInstance().startAutomaticCapture();

   
    //Putting subsystem data in SmartDashboard
  //  SmartDashboardSubData();

   // status = ByteBuffer.allocateDirect(4).asIntBuffer();
     
    //try 
     //   {
   //     	m_Lidar = new LidarSub(I2C.Port.kMXP);
    //      m_Lidar.start();
    //	}
    //    catch( Exception ex ) 
    //    {        	
     //   	m_Lidar = null;
     //   }
    m_oi = new OI();


    //m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    //status = ByteBuffer.allocateDirect(4).asIntBuffer();
    //if ( m_Lidar != null )
    //{
    //  m_Lidar.start();
   // }
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    m_DriveBaseSub.resetLeftEncoder();
    m_DriveBaseSub.resetRightEncoder();
    m_wedger.resetWedger();
    m_grimpeur.resetArm();

  }

  /*
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

    Scheduler.getInstance().run();
    SmartDashboard.putNumber("Speed", m_oi.returnPilote().getY());
   // m_grimpeur.resetArm();
   // SmartDashboard.putNumber("Distance", m_Lidar.getDistance());
   
  //m_arm.armInfo();
  SmartDashboard.putNumber("leftEncoder", m_DriveBaseSub.getLeftDistance());
  SmartDashboard.putNumber("RightEncoder", m_DriveBaseSub.getRightDistance());
  SmartDashboard.putNumber("joysticck axe y , CoP", m_oi.CoPiloteY());  
  //SmartDashboard.putBoolean("lightsensor Grabber2", RobotMap.lightSensorGrabber.get());
  m_wedger.getWedgerInfo();
  m_grimpeur.displayArmInfo();

  //  SmartDashboard.putBoolean("wedger front sensor", m_wedgerMobile.getSensorCollection().isRevLimitSwitchClosed());
 // SmartDashboard.putNumber("WedgerActualPosition", m_wedger.getPosition());
  //Uncomment this if it doesn't update, or remove it if it does
  //SmartDashboardSubData();
    SmartDashboard.putNumber("Joystick axe y, Pilote" , m_oi.returnPilote().getY());
    SmartDashboard.putNumber("Joystick axe y , gamePad", m_oi.getGamePad().getY());
  m_grimpeur.getArmPosition();
 // m_elevateur.getMotorInfo();
 // m_wedger.getWedgerInfo();
  m_oi.getJoyInfo();


 // if(m_wedger.getPosition() <= 0 ){
   // m_wedger.resetWedger();
  }
  

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  public void SmartDashboardSubData () {
    //Placing the Subsytem data into SmartDashboard
   // SmartDashboard.putData(m_elevateur);
    SmartDashboard.putData(m_DriveBaseSub);
    SmartDashboard.putData(m_grimpeur);
    
  //  SmartDashboard.putData(m_wedger);
  }
}
