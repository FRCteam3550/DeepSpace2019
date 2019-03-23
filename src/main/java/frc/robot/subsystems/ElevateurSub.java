// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.robot.subsystems;
// import java.io.ObjectInputFilter.Config;

// import javax.management.loading.PrivateMLet;
// import frc.robot.PIDsettings.Gains;
// import frc.robot.PIDsettings.Constants;

// import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.FeedbackDevice;
// import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
// import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
// import com.ctre.phoenix.motorcontrol.can.TalonSRX;
// import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;


// import edu.wpi.first.wpilibj.command.Subsystem;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import frc.robot.RobotMap;
// //import frc.robot.commands.moveElevator;

// /**
//  * An example subsystem.  You can replace me with your own Subsystem.
//  */
// public class ElevateurSub extends Subsystem {
//   // Put methods for controlling this subsystem
//   // here. Call these from Commands.

//   private static TalonSRX m_elevator = RobotMap.elevatorMotor;


//  public ElevateurSub(){

//   m_elevator.configFactoryDefault();
//   m_elevator.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, Constants.kTimeoutMs0);

// 		/* Configure Sensor Source for Pirmary PID */
// 		m_elevator.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,
// 											Constants.kPIDLoopId0, 
//                       Constants.kTimeoutMs0);
                      
                  
// 		/**
// 		 * Configure Talon SRX Output and Sesnor direction accordingly
// 		 * Invert Motor to have green LEDs when driving Talon Forward / Requesting Postiive Output
// 		 * Phase sensor to have positive increment when driving Talon Forward (Green LED)
// 		 */
// 		m_elevator.setSensorPhase(true); //false on the tests robot and True on the Year's robot
// 		m_elevator.setInverted(true);

// 		/* Set relevant frame periods to be at least as fast as periodic rate */
// 		m_elevator.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs0);
// 		m_elevator.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs0);

// 		/* Set the peak and nominal outputs */
// 		m_elevator.configNominalOutputForward(0, Constants.kTimeoutMs0);
// 		m_elevator.configNominalOutputReverse(0, Constants.kTimeoutMs0);
// 		m_elevator.configPeakOutputForward(1, Constants.kTimeoutMs0);
// 		m_elevator.configPeakOutputReverse(-1, Constants.kTimeoutMs0);


// 		/* Set Motion Magic gains in slot0 - see documentation */
// 		m_elevator.selectProfileSlot(Constants.kSlotId0, Constants.kPIDLoopId0);
// 		m_elevator.config_kF(Constants.kSlotId0, Constants.kGains0.kF, Constants.kTimeoutMs0);
// 		m_elevator.config_kP(Constants.kSlotId0, Constants.kGains0.kP, Constants.kTimeoutMs0);
// 	  m_elevator.config_kI(Constants.kSlotId0, Constants.kGains0.kI, Constants.kTimeoutMs0);
//     m_elevator.config_kD(Constants.kSlotId0, Constants.kGains0.kD, Constants.kTimeoutMs0);

// 	  m_elevator.configMotionCruiseVelocity(Constants.kCruiseVelocity0, Constants.kTimeoutMs0);
// 	  m_elevator.configMotionAcceleration(Constants.kAcceleration0, Constants.kTimeoutMs0);


// 	  m_elevator.setSelectedSensorPosition(0, Constants.kPIDLoopId0, Constants.kTimeoutMs0);
// 	}

//  public double getSensorValue(){
//    return m_elevator.getSensorCollection().getPulseWidthPosition();
//  } 

//  public void setSensorAt0(){
//   m_elevator.setSelectedSensorPosition(0, Constants.kPIDLoopId0, Constants.kTimeoutMs0);
//  }


//   @Override
//   public void initDefaultCommand() {
//     // Set the default command for a subsystem here.
//     // setDefaultCommand(new moveElevator());
//   }
//   public void stop(){
//     m_elevator.set(ControlMode.Position , 0);
//   }

//   public static double initialPosition = 0;
//   public static double position2 = 1 * 4096 * 8;


//   public void moveElevator(double percentageOutput){
//     m_elevator.set(ControlMode.PercentOutput ,percentageOutput);
//   }
 
//   public void configInitial(){
// 		m_elevator.selectProfileSlot(Constants.kSlotId0, Constants.kPIDLoopId0);
// 		m_elevator.config_kF(Constants.kSlotId0, Constants.kGains0.kF, Constants.kTimeoutMs0);
// 		m_elevator.config_kP(Constants.kSlotId0, Constants.kGains0.kP, Constants.kTimeoutMs0);
// 	  m_elevator.config_kI(Constants.kSlotId0, Constants.kGains0.kI, Constants.kTimeoutMs0);
//     m_elevator.config_kD(Constants.kSlotId0, Constants.kGains0.kD, Constants.kTimeoutMs0);

// 	  m_elevator.configMotionCruiseVelocity(Constants.kCruiseVelocity0, Constants.kTimeoutMs0);
//     m_elevator.configMotionAcceleration(Constants.kAcceleration0, Constants.kTimeoutMs0);
//   }

//   public void goInitial(double position){
//        m_elevator.set(ControlMode.MotionMagic, position);
//   }
 
//   public void ConfigPos1(){   
//     m_elevator.selectProfileSlot(Constants.kSlotId1, Constants.kPIDLoopId1);
//     m_elevator.config_kF(Constants.kSlotId1, Constants.kGains1.kF, Constants.kTimeoutMs0);
//     m_elevator.config_kP(Constants.kSlotId1, Constants.kGains1.kP, Constants.kTimeoutMs0);
//     m_elevator.config_kI(Constants.kSlotId1, Constants.kGains1.kI, Constants.kTimeoutMs0);
//     m_elevator.config_kD(Constants.kSlotId1, Constants.kGains1.kD, Constants.kTimeoutMs0);  
//   }

//   public void goPos1(double position1){
//     m_elevator.set(ControlMode.MotionMagic , position1);
//   }

//   public void ConfigPos2(){   
//     m_elevator.selectProfileSlot(Constants.kSlotId2, Constants.kPIDLoopId2);
//     m_elevator.config_kF(Constants.kSlotId2, Constants.kGains2.kF, Constants.kTimeoutMs0);
//     m_elevator.config_kP(Constants.kSlotId2, Constants.kGains2.kP, Constants.kTimeoutMs0);
//     m_elevator.config_kI(Constants.kSlotId2, Constants.kGains2.kI, Constants.kTimeoutMs0);
//     m_elevator.config_kD(Constants.kSlotId2, Constants.kGains2.kD, Constants.kTimeoutMs0);  
//   }
  

//  public void goPos2(double position){
//    m_elevator.set(ControlMode.MotionMagic , position);
//  }
 

//  public void getMotorInfo(){
    
//    SmartDashboard.putNumber("elevatorVel", m_elevator.getSelectedSensorVelocity());
//    SmartDashboard.putNumber("elevMotorOuput", m_elevator.getMotorOutputPercent());
//    SmartDashboard.putNumber("elevator position", m_elevator.getSelectedSensorPosition());
//   SmartDashboard.putBoolean("elevatorLimitSwitch", m_elevator.getSensorCollection().isRevLimitSwitchClosed());
  
//  }

//  public void goPosition3(double position){
//    m_elevator.set(ControlMode.MotionMagic, position);
//  }

//  public double getSensorPosition(){
//     return m_elevator.getSelectedSensorPosition();
//  }

// }