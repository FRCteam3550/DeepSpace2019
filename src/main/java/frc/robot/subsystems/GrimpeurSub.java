/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import com.ctre.phoenix.motorcontrol.LimitSwitchSource;

import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.GrimpeurManualFront;
import frc.robot.commands.StopGrimpeur;
import frc.robot.PIDsettings.*;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;


/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class GrimpeurSub extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  //front part of the grimpeur
public static TalonSRX m_grimpeurBrasGauche =  RobotMap.GrimpeurBrasGauche;
public static TalonSRX m_grimpeurBrasDroit =  RobotMap.GrimpeurBrasDroit;
public static VictorSPX m_pusher = RobotMap.Pusher;


//back part of the grimpeur
private static TalonSRX m_grimpeurArriere = RobotMap.grimpeurArriere;


  public GrimpeurSub(){
 m_grimpeurBrasDroit.configFactoryDefault();
 m_grimpeurBrasDroit.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, Constants.kTimeoutMs0);
  
       /* Configure Sensor Source for Pirmary PID */
    m_grimpeurBrasDroit.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,
        Constants.kPIDLoopId0, 
        Constants.kTimeoutMs0);
  
                  m_grimpeurBrasDroit.setNeutralMode(NeutralMode.Brake);    
//       /**
       // * Configure Talon SRX Output and Sesnor direction accordingly
       // * Invert Motor to have green LEDs when driving Talon Forward / Requesting Postiive Output
     //  * Phase sensor to have positive increment when driving Talon Forward (Green LED)
       
  m_grimpeurBrasDroit.setSensorPhase(false); //false on the tests robot and True on the Year's robot
   m_grimpeurBrasDroit.setInverted(false);
  
      /* Set relevant frame periods to be at least as fast as periodic rate */
   m_grimpeurBrasDroit.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs0);
   m_grimpeurBrasDroit.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs0);
  
      /* Set the peak and nominal outputs */
   m_grimpeurBrasDroit.configNominalOutputForward(0, Constants.kTimeoutMs0);
   m_grimpeurBrasDroit.configNominalOutputReverse(0, Constants.kTimeoutMs0);
   m_grimpeurBrasDroit.configPeakOutputForward(1, Constants.kTimeoutMs0);
   m_grimpeurBrasDroit.configPeakOutputReverse(-1, Constants.kTimeoutMs0);
  
  
      /* Set Motion Magic gains in slot0 - see documentation */
  m_grimpeurBrasDroit.selectProfileSlot(Constants.kSlotId0, Constants.kPIDLoopId0);
   m_grimpeurBrasDroit.config_kF(Constants.kSlotId0, Constants.kGains0.kF, Constants.kTimeoutMs0);
   m_grimpeurBrasDroit.config_kP(Constants.kSlotId0, Constants.kGains0.kP, Constants.kTimeoutMs0);
   m_grimpeurBrasDroit.config_kI(Constants.kSlotId0, Constants.kGains0.kI, Constants.kTimeoutMs0);
   m_grimpeurBrasDroit.config_kD(Constants.kSlotId0, Constants.kGains0.kD, Constants.kTimeoutMs0);
  
   m_grimpeurBrasDroit.configMotionCruiseVelocity(Constants.kCruiseVelocity0, Constants.kTimeoutMs0);
   m_grimpeurBrasDroit.configMotionAcceleration(Constants.kAcceleration0, Constants.kTimeoutMs0);
  
      //Zeroes the Sensor
   m_grimpeurBrasDroit.setSelectedSensorPosition(0, Constants.kPIDLoopId0, Constants.kTimeoutMs0);

   //****************************************************** *//

   m_grimpeurBrasGauche.configFactoryDefault();
   m_grimpeurBrasGauche.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, Constants.kTimeoutMs0);
 
      /* Configure Sensor Source for Pirmary PID */
      m_grimpeurBrasGauche.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,
       Constants.kPIDLoopId0, 
       Constants.kTimeoutMs0);
 
                    m_grimpeurBrasGauche.setNeutralMode(NeutralMode.Brake);    
//       /**
      // * Configure Talon SRX Output and Sesnor direction accordingly
      // * Invert Motor to have green LEDs when driving Talon Forward / Requesting Postiive Output
    //  * Phase sensor to have positive increment when driving Talon Forward (Green LED)
      
    m_grimpeurBrasGauche.setSensorPhase(false); //false on the tests robot and True on the Year's robot
     m_grimpeurBrasGauche.setInverted(false);
 
     /* Set relevant frame periods to be at least as fast as periodic rate */
     m_grimpeurBrasGauche.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs0);
     m_grimpeurBrasGauche.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs0);
 
     /* Set the peak and nominal outputs */
     m_grimpeurBrasGauche.configNominalOutputForward(0, Constants.kTimeoutMs0);
     m_grimpeurBrasGauche.configNominalOutputReverse(0, Constants.kTimeoutMs0);
     m_grimpeurBrasGauche.configPeakOutputForward(1, Constants.kTimeoutMs0);
     m_grimpeurBrasGauche.configPeakOutputReverse(-1, Constants.kTimeoutMs0);
 
 
     /* Set Motion Magic gains in slot0 - see documentation */
    m_grimpeurBrasGauche.selectProfileSlot(Constants.kSlotId0, Constants.kPIDLoopId0);
     m_grimpeurBrasGauche.config_kF(Constants.kSlotId0, Constants.kGains0.kF, Constants.kTimeoutMs0);
     m_grimpeurBrasGauche.config_kP(Constants.kSlotId0, Constants.kGains0.kP, Constants.kTimeoutMs0);
     m_grimpeurBrasGauche.config_kI(Constants.kSlotId0, Constants.kGains0.kI, Constants.kTimeoutMs0);
     m_grimpeurBrasGauche.config_kD(Constants.kSlotId0, Constants.kGains0.kD, Constants.kTimeoutMs0);
 
     m_grimpeurBrasGauche.configMotionCruiseVelocity(Constants.kCruiseVelocity0, Constants.kTimeoutMs0);
     m_grimpeurBrasGauche.configMotionAcceleration(Constants.kAcceleration0, Constants.kTimeoutMs0);
 
     //Zeroes the Sensor
     m_grimpeurBrasGauche.setSelectedSensorPosition(0, Constants.kPIDLoopId0, Constants.kTimeoutMs0);
  


     //************************************************** */

     m_grimpeurArriere.configFactoryDefault();
     m_grimpeurArriere.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, Constants.kTimeoutMs0);
      
           /* Configure Sensor Source for Pirmary PID */
           m_grimpeurArriere.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,
            Constants.kPIDLoopId0, 
            Constants.kTimeoutMs0);
      
            m_grimpeurArriere.setNeutralMode(NeutralMode.Brake);    
    //       /**
           // * Configure Talon SRX Output and Sesnor direction accordingly
           // * Invert Motor to have green LEDs when driving Talon Forward / Requesting Postiive Output
         //  * Phase sensor to have positive increment when driving Talon Forward (Green LED)
           
         m_grimpeurArriere.setSensorPhase(false); //false on the tests robot and True on the Year's robot
      m_grimpeurArriere.setInverted(false);
      
          /* Set relevant frame periods to be at least as fast as periodic rate */
          m_grimpeurArriere.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs0);
       m_grimpeurArriere.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs0);
      
          /* Set the peak and nominal outputs */
          m_grimpeurArriere.configNominalOutputForward(0, Constants.kTimeoutMs0);
          m_grimpeurArriere.configNominalOutputReverse(0, Constants.kTimeoutMs0);
       m_grimpeurArriere.configPeakOutputForward(1, Constants.kTimeoutMs0);
       m_grimpeurArriere.configPeakOutputReverse(-1, Constants.kTimeoutMs0);
      
      
          /* Set Motion Magic gains in slot0 - see documentation */
          m_grimpeurArriere.selectProfileSlot(Constants.kSlotId0, Constants.kPIDLoopId0);
      m_grimpeurArriere.config_kF(Constants.kSlotId0, Constants.kGains0.kF, Constants.kTimeoutMs0);
       m_grimpeurArriere.config_kP(Constants.kSlotId0, Constants.kGains0.kP, Constants.kTimeoutMs0);
       m_grimpeurArriere.config_kI(Constants.kSlotId0, Constants.kGains0.kI, Constants.kTimeoutMs0);
       m_grimpeurArriere.config_kD(Constants.kSlotId0, Constants.kGains0.kD, Constants.kTimeoutMs0);
      
       m_grimpeurArriere.configMotionCruiseVelocity(Constants.kCruiseVelocity0, Constants.kTimeoutMs0);
       m_grimpeurArriere.configMotionAcceleration(Constants.kAcceleration0, Constants.kTimeoutMs0);
      
          //Zeroes the Sensor
          m_grimpeurArriere.setSelectedSensorPosition(0, Constants.kPIDLoopId0, Constants.kTimeoutMs0);
  }


  public void getArmPosition(){
  SmartDashboard.putNumber("brasDroitGrimpeur", m_grimpeurBrasDroit.getSelectedSensorPosition());  
  SmartDashboard.putNumber("brasDroitGrimpeur", m_grimpeurBrasGauche.getSelectedSensorPosition());  
  }



public void setFront(double speed){
RobotMap.GrimpeurBrasGauche.set(ControlMode.PercentOutput, speed); 
SmartDashboard.putNumber("IsEncoderInPhaseFrontLeft", RobotMap.GrimpeurBrasGauche.getSelectedSensorVelocity(Constants.kPIDLoopId0));
  RobotMap.GrimpeurBrasDroit.set(ControlMode.PercentOutput, -speed);
  SmartDashboard.putNumber("IsEncoderInPhaseFrontRight", RobotMap.GrimpeurBrasDroit.getSelectedSensorVelocity(Constants.kPIDLoopId0));
}

public void setBack(double speed){
  m_grimpeurArriere.set(ControlMode.PercentOutput, speed);
  SmartDashboard.putNumber("IsEncoderInPhaseBackClimber", RobotMap.grimpeurArriere.getSelectedSensorVelocity(Constants.kPIDLoopId0));
}

public void setPusher(double speed){
  m_pusher.set(ControlMode.PercentOutput, speed);
}

public void setRoulette(double speed){
  m_pusher.set(ControlMode.PercentOutput, speed);
}

public void setDriveBase(double speed){
  RobotMap.LeftBackMotor.set(speed);
  RobotMap.LeftFrontMotor.set(speed);
  RobotMap.RightBackMotor.set(speed);
  RobotMap.RightFrontMotor.set(speed);
}

public void stopFront(){
  setFront(0);
}

public void Monter(){
  setFront(0.5); //signe a verifier
}

public void descendre(){
  setFront(-0.5); //signe a verifier
}


public void getEncoderPositionBrasDroit(){
  m_grimpeurBrasDroit.getSelectedSensorPosition();
}

public void getEncoderPositionBrasGauce(){
  m_grimpeurBrasGauche.getSelectedSensorPosition();
}

public double getDriveBasePosition(){
  
  double moyenne = 0;

  moyenne = ( (RobotMap.Left.get() + RobotMap.Right.get() ) / 2 );

  return moyenne;
}

public void setDriveBasePosition(){
  RobotMap.Left.reset();
}


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new GrimpeurManualFront());
  }
  
}
