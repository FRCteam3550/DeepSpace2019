/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
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
  
  public static SpeedController LeftFrontMotor;
  public static SpeedController LeftBackMotor ;
  public static SpeedController RightFrontMotor;
  public static SpeedController RightBackMotor;
  //thrower
  public static Spark Thrower;
  //encodeur du drivetrain
  public static Encoder Left;
  public static Encoder Right;
  public static DigitalInput DriveTrainLightSensorL;
  public static DigitalInput DriveTrainLightSensorR;
  //elevator
  public static TalonSRX grimpeurArriere;
  public static TalonSRX grimpeurRoue;
  public static Encoder elevatoEncoder;
  //wedger
  public static TalonSRX wedgerMotor;
  //Grabber
  public static TalonSRX wedgerMobile;
  public static DigitalInput lightSensorGrabber;
  //Led
  public static PWM ledController;
  //Grimpeur   
  public static TalonSRX GrimpeurBrasDroit;
  public static TalonSRX GrimpeurBrasGauche;
  //public static VictorSPX Pusher;
  public static DigitalInput HomePositionLeft;
  public static DigitalInput HomePositionRight;

public RobotMap() {

  ledController = new PWM(0);

    // Drive Train Competition
  LeftFrontMotor  = new WPI_VictorSPX(1); // can bus
  LeftBackMotor   = new WPI_VictorSPX(2); //can bus
  RightFrontMotor = new WPI_VictorSPX(3); //can bus
  RightBackMotor  = new WPI_VictorSPX(4); //

  //Drive train Tests
  //LeftFrontMotor  = new WPI_TalonSRX(1); // can bus
  //LeftBackMotor   = new WPI_TalonSRX(2); //can bus
  //RightFrontMotor = new WPI_TalonSRX(3); //can bus
  //RightBackMotor  = new WPI_TalonSRX(4); //


  //encodeur
  Left = new Encoder(1 , 2);
  Right = new Encoder(3, 4);

  DriveTrainLightSensorL = new DigitalInput(8);
  DriveTrainLightSensorR = new DigitalInput(9);

    //elevator
  grimpeurArriere = new TalonSRX(5);  //4 on 2019 robot 
  grimpeurRoue = new TalonSRX(13);
  
    //grabber
  wedgerMobile = new TalonSRX(7);  // 7 on 2019 robot
// lightSensorGrabber = new DigitalInput(5);

  //grimpeur 
GrimpeurBrasDroit = new TalonSRX(6);
GrimpeurBrasGauche = new TalonSRX(8);
//Pusher = new VictorSPX(9);
HomePositionLeft = new DigitalInput(5);
HomePositionRight = new DigitalInput(6);
 //Thrower
 Thrower = new Spark(15);


  //Wedger
 wedgerMotor = new TalonSRX(12);  //8 o n 2019 robot

}
}
