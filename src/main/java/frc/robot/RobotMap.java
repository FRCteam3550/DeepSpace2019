/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  //public static int LeftFrontMotor = 1;
  //public static int LeftBackMotor = 2;
  
  //public static int RightFrontMotor = 3;
  //public static int RightBackMotor = 4; TalonSRX(1);
  public static SpeedController LeftFrontMotor;
  public static SpeedController LeftBackMotor ;
  public static SpeedController RightFrontMotor;
  public static SpeedController RightBackMotor;

  //encodeur du drivetrain
  public static Encoder Left;
  public static Encoder Right;

  public static DigitalInput DriveTrainLightSensorL;
  public static DigitalInput DriveTrainLightSensorR;

  public static TalonSRX elevatorMotor;
  public static Encoder elevatoEncoder;

  public static SpeedController armMotor;
 
  public static Encoder armEncoder;

    //Wrist
  public static SpeedController wristMotor;
 // public static DigitalInput wrsistLimit1;
 // public static DigitalInput wrsistLimit2;

  public static DigitalInput LightSensorWrist;


 // public static DigitalInput limitSwitch1;
  //public static DigitalInput limitSwitch2;

 // public static DigitalInput armLimit1;
  //public static DigitalInput armLimit2;

public RobotMap() {
    // Drive Train
  LeftFrontMotor  = new WPI_VictorSPX(2); //2 WPI_VictorSPX (tous)
  LeftBackMotor   = new WPI_VictorSPX(1); //1
  RightFrontMotor = new WPI_VictorSPX(4); //4
  RightBackMotor  = new WPI_VictorSPX(3); //3 

  //encodeur
  Left = new Encoder(1 , 2); // (1,2)
  Right = new Encoder(3 , 4); //(3,4)

  DriveTrainLightSensorL = new DigitalInput(8); //8
  DriveTrainLightSensorR = new DigitalInput(9);  //9

    //elevator
  elevatorMotor = new TalonSRX(10);
 // elevatoEncoder = new Encoder(4 , 5 , false , EncodingType.k4X);
    
  
  //aArm
  armMotor = new WPI_TalonSRX(6);
 
  //armEncoder = new Encoder(6 , 7 , false , EncodingType.k4X);

    //Wrist
  wristMotor = new WPI_TalonSRX(7);
 // public static DigitalInput wrsistLimit1;
 // public static DigitalInput wrsistLimit2;

  LightSensorWrist = new DigitalInput(5);

  //public static DoubleSolenoid wristPiston = new DoubleSolenoid(8, 7);
  

  //public static int NavX

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}
}
