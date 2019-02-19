/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import java.io.ObjectInputFilter.Config;

import javax.management.loading.PrivateMLet;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.StopRobot;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class ElevateurSub extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private static TalonSRX m_elevator = RobotMap.elevatorMotor;


 public ElevateurSub(){
  m_elevator.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
 // m_elevator.set(ControlMode.Position , 0);
 }


 public double getSensorValue(){
   return m_elevator.getSensorCollection().getPulseWidthPosition();
 } 


  //limit switch en bas
 // boolean Value1 = m_limitSwitch1.get();

  //limit switch en haut
 // boolean Value2 = m_limitSwitch2.get();

 /* public void DistanceHatch1(Encoder m_Encoder){
    m_Encoder.setDistancePerPulse(0);
  } */

 /* public int getPosition(){
    return m_encodeur.get();
  } */

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
     setDefaultCommand(new StopRobot());
  }

 // public boolean getLimit1(){
  //  return  m_limitSwitch1.get();
 // }
//
 // public boolean getLimit2(){
  //  return  m_limitSwitch2.get();
  //}
  
  
  public void stop(){
   // m_elevator.set(ControlMode.Position , 0);
   m_elevator.set(ControlMode.PercentOutput , 0);

  }

  //elevator up and when it hits the top limitSwitch it stops the motor
  public void elevatorUpHatch(){
    m_elevator.set(ControlMode.PercentOutput , 0.7);

   // boolean LimitSwitchOpen = true;

    //if (getLimit1()== LimitSwitchOpen ){
    //  stop();
   // }

    //else if  (getPosition() == 0) {
    //  if (getPosition() != 50){
       // m_elevator.set(0.5);
      //}else{
     //   stop();
     // }
    }

    //else if  (getPosition() == 50) {
    //  if (getPosition() != 100){
     //   m_elevator.set(0.5);}
    //  else{
    //  stop();
   // }
  //}
  //}

  public void elevatorUpBalls(){
    m_elevator.set(ControlMode.PercentOutput , 0.5);
  }
    /*boolean LimitSwitchOpen = true;

    if (getLimit1()== LimitSwitchOpen ){
      stop();
    }

    else if  (getPosition() == 0) {
      if (getPosition() !=20){
        m_elevator.set(0.5);
      }else{
        stop();
      }
    }

    else if  (getPosition() == 20) {
      if (getPosition() != 80){
        m_elevator.set(0.5);}
      else{
      stop();
    }
  }

  else if  (getPosition() == 80) {
    if (getPosition() != 130){
      m_elevator.set(0.5);}
    else{
    stop();
  }
}


}*/
  //when the elevator is down and it hits the limit switch is hit, stop the motor
  public void elevatorDown(){
   // boolean LimitSwitchClosed = false;

   //if (LimitSwitchClosed != getLimit2()){
    m_elevator.set(ControlMode.PercentOutput ,-0.7);
  // }else{
   //  stop();
   // }

  }



}