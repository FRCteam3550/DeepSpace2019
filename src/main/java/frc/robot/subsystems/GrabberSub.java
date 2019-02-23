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
  private static SpeedController m_GrabberMotor = RobotMap.grabberMotor;

  private static DigitalInput m_LightSensorGrabber = RobotMap.lightSensorGrabber;

  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
     setDefaultCommand(new StopGrabber());
  }


  public void stopGrabberMotor(){
    m_GrabberMotor.set(0);
  }

  public boolean getLightSensorValue(){
    return m_LightSensorGrabber.get();
  }

  
  //take in a ball and stop the motor when the ball is in
  public void aspirer(){
      m_GrabberMotor.set(0.5);
  }

  public void rejeter(){
    m_GrabberMotor.set(-0.5);
    
  }  
}
