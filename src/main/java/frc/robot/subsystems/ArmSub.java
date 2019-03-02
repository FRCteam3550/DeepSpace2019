/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.PIDsettings.Constants;
import frc.robot.commands.MoveArmManualCom;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
/**
 * Add your docs here.
 */
public class ArmSub extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public TalonSRX m_armMotor = RobotMap.narmMotor;

  public ArmSub(){
    m_armMotor.configFactoryDefault();
    m_armMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative , Constants.kPIDLoopIdArmSub0, Constants.kTimeoutMsArmSub0);
    
    m_armMotor.setSensorPhase(true);
    m_armMotor.setInverted(false);

    m_armMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, Constants.kTimeoutMsArmSub0);
    m_armMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, Constants.kTimeoutMsArmSub0);
    
    m_armMotor.configNominalOutputForward(0, Constants.kTimeoutMsArmSub0);
    m_armMotor.configNominalOutputReverse(0, Constants.kTimeoutMsArmSub0);
    m_armMotor.configPeakOutputForward(1, Constants.kTimeoutMsArmSub0);
    m_armMotor.configPeakOutputReverse(-1, Constants.kTimeoutMsArmSub0);

    m_armMotor.selectProfileSlot(Constants.kSlotIdArmSub0, Constants.kPIDLoopIdArmSub0);
    m_armMotor.config_kF(Constants.kSlotIdArmSub0, Constants.kGainsArmSub0.kF, Constants.kTimeoutMsArmSub0);
    m_armMotor.config_kP(Constants.kSlotIdArmSub0, Constants.kGainsArmSub0.kP, Constants.kTimeoutMsArmSub0);
    m_armMotor.config_kI(Constants.kSlotIdArmSub0, Constants.kGainsArmSub0.kI, Constants.kTimeoutMsArmSub0);
    m_armMotor.config_kD(Constants.kSlotIdArmSub0, Constants.kGainsArmSub0.kD, Constants.kTimeoutMsArmSub0);

    m_armMotor.configMotionCruiseVelocity(Constants.kCruiseVelocitArmSub0,Constants.kTimeoutMsArmSub0);
    m_armMotor.configMotionAcceleration(Constants.kAccelerationArmSub0, Constants.kTimeoutMsArmSub0);

    m_armMotor.setSelectedSensorPosition(0, Constants.kPIDLoopIdArmSub0, Constants.kTimeoutMsArmSub0);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new MoveArmManualCom());
    
  }
  public void resetArmPosition(){
    m_armMotor.setSelectedSensorPosition(0, Constants.kPIDLoopIdArmSub0, Constants.kTimeoutMsArmSub0); 
  }

  public void setArmPosition(int position){
    m_armMotor.set(ControlMode.MotionMagic, position);
  }

  public int getArmPosition(){
   return m_armMotor.getSelectedSensorPosition(Constants.kSlotIdArmSub0);
  }

  public void stopArm(){
    m_armMotor.set(ControlMode.PercentOutput, 0);
  }

  public void setManualControl(double speed){
    m_armMotor.set(ControlMode.PercentOutput, speed);
  }

}
