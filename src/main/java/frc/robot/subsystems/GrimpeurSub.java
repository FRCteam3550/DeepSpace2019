/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;

import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.StopGrimpeur;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class GrimpeurSub extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

public static TalonSRX m_grimpeurBrasGauche =  RobotMap.GrimpeurBrasGauche;
public static TalonSRX m_grimpeurBrasDroit =  RobotMap.GrimpeurBrasDroit;

  public GrimpeurSub(){
      m_grimpeurBrasDroit.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
      m_grimpeurBrasGauche.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
      
      

      m_grimpeurBrasDroit.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);
      m_grimpeurBrasGauche.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);

      m_grimpeurBrasDroit.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
      m_grimpeurBrasGauche.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);

  }


  public void getArmPosition(){
  SmartDashboard.putNumber("brasDroitGrimpeur", m_grimpeurBrasDroit.getSelectedSensorPosition());  
  SmartDashboard.putNumber("brasDroitGrimpeur", m_grimpeurBrasGauche.getSelectedSensorPosition());  
  }

public void set(double speed){
  RobotMap.GrimpeurBrasGauche.set(ControlMode.PercentOutput, speed);
  RobotMap.GrimpeurBrasDroit.set(ControlMode.PercentOutput, speed);
}

public void stop(){
  set(0);
}

public void Monter(){
  set(0.5); //signe a verifier
}

public void descendre(){
  set(-0.5); //signe a verifier
}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
     setDefaultCommand(new StopGrimpeur());
  }
  
}
