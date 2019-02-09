/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.StopGrabber;
import frc.robot.commands.StopRobot;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class GrabberSub extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  //rotating arm
  //private static SpeedController m_armMotor = RobotMap.armMotor;
 // private static DigitalInput m_armLimit1 = RobotMap.armLimit1;
 // private static DigitalInput m_armLimit2 = RobotMap.armLimit2;
  private static Encoder m_armEncoder = RobotMap.armEncoder;

  //valeur des limit switch for the arm
  //boolean valueDown = m_armLimit1.get();
 // boolean valueUp = m_armLimit2.get(); 

  //Grabbeur
  private static SpeedController m_wristMotor = RobotMap.wristMotor;
 // private static DigitalInput m_wristLimit1 = RobotMap.wrsistLimit1;
 // private static DigitalInput m_wristLimit2 = RobotMap.wrsistLimit2;
 //  private static Encoder m_wristEncoder = RobotMap.wrsistEncoder;
 // private static DoubleSolenoid m_wristPiston = RobotMap.wristPiston; 

  //photoSwitch for the Grabbeur
  private static DigitalInput m_LightSensorWrist = RobotMap.LightSensorWrist;
  //boolean value = m_LightSensorWrist.get();

  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
     setDefaultCommand(new StopGrabber());
  }

 /* public boolean ValueDown(){
    return m_armLimit1.get();
  }

  public boolean ValueUp(){
    return m_armLimit2.get();
  } */

 // public void stopArmMotor(){
   // m_armMotor.set(0);
  //}

  //faire baisser le bras
  //public void rotateArmD(){
   // m_armMotor.set(0.5);
  //}
  
  //faire monter le bras
  //public void rotateArmU(){
   // m_armMotor.set(-0.5);
  //}

  public void stopWristMotor(){
    m_wristMotor.set(0);
  }

  public boolean getLightSensorValue(){
    return m_LightSensorWrist.get();
  }

  //take in a ball and stop the motor when the ball is in
  public void aspirer(){
    m_wristMotor.set(0.5);

   // boolean LightSensor = true;

    //if ( getLightSensorValue() == LightSensor){
      //m_wristMotor.set(0);
    //}

  }

  public void rejeter(){
    m_wristMotor.set(-0.5);
    
  }

  //ouvrir le piston
  //public void forwardPiston(){
    //m_wristPiston.set(DoubleSolenoid.Value.kForward);
  //}

  //fermer le piston
  //public void reversePiston(){
    //m_wristPiston.set(DoubleSolenoid.Value.kReverse);
  //}

  
  
}
