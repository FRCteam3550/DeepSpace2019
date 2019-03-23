/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/*package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.MoveArm;

import frc.robot.PIDsettings.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;


/**
 * Add your docs here.
 */
/*public class Arm extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands<.
  private static TalonSRX m_armMotor = RobotMap.armMotor;


  public Arm(){
	m_armMotor.configFactoryDefault();
    m_armMotor.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, Constants.kTimeoutMs0);

		/* Configure Sensor Source for Pirmary PID */
		/*m_armMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,
											Constants.kPIDLoopId0, 
											Constants.kTimeoutMs0);


											m_armMotor.setNeutralMode(NeutralMode.Brake);
		/**
		 * Configure Talon SRX Output and Sesnor direction accordingly
		 * Invert Motor to have green LEDs when driving Talon Forward / Requesting Postiive Output
		 * Phase sensor to have positive increment when driving Talon Forward (Green LED)
		 */
		/*m_armMotor.setSensorPhase(false); //false on the tests robot and True on the Year's robot
		m_armMotor.setInverted(false); //true on the real robot

		/* Set relevant frame periods to be at least as fast as periodic rate */
		/*m_armMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs0);
		m_armMotor.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs0);

		/* Set the peak and nominal outputs */
		/*m_armMotor.configNominalOutputForward(0, Constants.kTimeoutMs0);
		m_armMotor.configNominalOutputReverse(0, Constants.kTimeoutMs0);
		m_armMotor.configPeakOutputForward(1, Constants.kTimeoutMs0);
		m_armMotor.configPeakOutputReverse(-1, Constants.kTimeoutMs0);


		/* Set Motion Magic gains in slot0 - see documentation */
		/*m_armMotor.selectProfileSlot(Constants.kSlotId0, Constants.kPIDLoopId0);
		m_armMotor.config_kF(Constants.kSlotId0, Constants.kGains0.kF, Constants.kTimeoutMs0);
		m_armMotor.config_kP(Constants.kSlotId0, Constants.kGains0.kP, Constants.kTimeoutMs0);
	  m_armMotor.config_kI(Constants.kSlotId0, Constants.kGains0.kI, Constants.kTimeoutMs0);
    m_armMotor.config_kD(Constants.kSlotId0, Constants.kGains0.kD, Constants.kTimeoutMs0);

	  m_armMotor.configMotionCruiseVelocity(Constants.kCruiseVelocity0, Constants.kTimeoutMs0);
	  m_armMotor.configMotionAcceleration(Constants.kAcceleration0, Constants.kTimeoutMs0);


	  m_armMotor.setSelectedSensorPosition(0, Constants.kPIDLoopId0, Constants.kTimeoutMs0);

  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
     setDefaultCommand(new MoveArm());
  }

  public void resetArm(){
	m_armMotor.setSelectedSensorPosition(0, Constants.kPIDLoopId0, Constants.kTimeoutMs0);

  }

  public double getPosition(){
	  return m_armMotor.getSelectedSensorPosition();
  }

  public void setMotorPercent(double percent){
	m_armMotor.set(ControlMode.PercentOutput, percent);
  }

  public void stopArm(){
    m_armMotor.set(ControlMode.PercentOutput , 0);
	}
	
	public void moveArm(double yaxis){
		m_armMotor.set(ControlMode.PercentOutput , yaxis);
	}

	public double getMotorPercentage(){
		return m_armMotor.getMotorOutputPercent();
	}
	
  public void configArmUp(){
	
	m_armMotor.selectProfileSlot(Constants.kSlotIdWedger1, Constants.kPIDLoopIdWedger1);
    m_armMotor.config_kF(Constants.kSlotIdWedger1, Constants.kGainsWedger1.kF, Constants.kTimeoutMs0);
    m_armMotor.config_kP(Constants.kSlotIdWedger1, Constants.kGainsWedger1.kP, Constants.kTimeoutMs0);
    m_armMotor.config_kI(Constants.kSlotIdWedger1, Constants.kGainsWedger1.kI, Constants.kTimeoutMs0);
    m_armMotor.config_kD(Constants.kSlotIdWedger1, Constants.kGainsWedger1.kD, Constants.kTimeoutMs0);

  }

  public void moveArmUp(double position){
      m_armMotor.set(ControlMode.MotionMagic, position);
}
	
	public void ConfigArmDown(){
		m_armMotor.selectProfileSlot(Constants.kSlotIdWedger1, Constants.kPIDLoopIdWedger1);
		m_armMotor.config_kF(Constants.kSlotIdWedger1, Constants.kGainsWedger1.kF, Constants.kTimeoutMs0);
		m_armMotor.config_kP(Constants.kSlotIdWedger1, Constants.kGainsWedger1.kP, Constants.kTimeoutMs0);
		m_armMotor.config_kI(Constants.kSlotIdWedger1, Constants.kGainsWedger1.kI, Constants.kTimeoutMs0);
		m_armMotor.config_kD(Constants.kSlotIdWedger1, Constants.kGainsWedger1.kD, Constants.kTimeoutMs0);
	}

	public void moveArmDown(double position){
		m_armMotor.set(ControlMode.MotionMagic, position);
	}

	public void configManuel(){
		m_armMotor.configFactoryDefault();
	  }

	public void moveArmManual(double speed){
		m_armMotor.set(ControlMode.PercentOutput , speed);
	}

	public void armInfo(){
		SmartDashboard.putNumber("ArmPosition", m_armMotor.getSelectedSensorPosition());
		SmartDashboard.putNumber("ArmOutputPercent", m_armMotor.getMotorOutputPercent());
	}

}
*/
