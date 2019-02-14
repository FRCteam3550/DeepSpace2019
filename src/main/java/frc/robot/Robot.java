/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.GrabberPrendreCmd;
import frc.robot.subsystems.Arm;
//import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.DriveBaseSub;
import frc.robot.subsystems.ElevateurSub;
import frc.robot.subsystems.GrabberSub;
//import frc.robot.subsystems.LidarSub;
//import edu.wpi.first.wpilibj.I2C;
//import edu.wpi.first.wpilibj.I2C.Port;
//import java.nio.ByteBuffer;
//import java.nio.IntBuffer;



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
  public static ElevateurSub m_elevateur;
  public static GrabberSub m_grabberSub;
  public static Arm m_arm;
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
    m_grabberSub = new GrabberSub();
    m_elevateur = new ElevateurSub();
    m_arm = new Arm();
    m_DriveBaseSub = new DriveBaseSub();
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

  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

    Scheduler.getInstance().run();
    SmartDashboard.putNumber("Speed", m_oi.returnPilote().getY());
   // SmartDashboard.putNumber("Distance", m_Lidar.getDistance());
   
  SmartDashboard.putNumber("leftEncoder", m_DriveBaseSub.getLeftDistance());
  SmartDashboard.putNumber("RightEncoder", m_DriveBaseSub.getRightDistance());

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
