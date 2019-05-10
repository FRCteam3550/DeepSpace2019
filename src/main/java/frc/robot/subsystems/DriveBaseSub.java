/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.ArcadeDrive;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import com.ctre.phoenix.motorcontrol.NeutralMode;


/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveBaseSub extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  AHRS ahrs;

  private SpeedControllerGroup m_LeftControlGroup;
  private SpeedControllerGroup m_RightControlGroup;

  private DifferentialDrive m_DiffDrive;

  private static WPI_VictorSPX m_leftFront = RobotMap.LeftFrontMotor;
  private static WPI_VictorSPX m_leftBack = RobotMap.LeftBackMotor;
  private static WPI_VictorSPX m_RightFront = RobotMap.RightFrontMotor;
  private static WPI_VictorSPX m_RightBack = RobotMap.RightBackMotor;

  //encodeur 
  public static Encoder m_Right = RobotMap.Right;
  public static Encoder m_Left = RobotMap.Left;

  //photo switch
  private static DigitalInput m_driveTrainLightSensorL = RobotMap.DriveTrainLightSensorL;
  private static DigitalInput m_driveTrainLightSensorR = RobotMap.DriveTrainLightSensorR;

   private static boolean m_boostMode = false;
   private static boolean m_accelLimit = false;


   private static double m_PrevRightOutput = 0;
   private static double m_PrevLeftOutput = 0;

   public static double m_previousXspeedOuput = 0;



  public DriveBaseSub() {

    try {
      /* Communicate w/navX-MXP via the MXP SPI Bus.                                     */
      /* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
      /* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */
      ahrs = new AHRS(SPI.Port.kMXP); 
    } catch (RuntimeException ex ) {
      DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
    }
    m_leftFront.setInverted(true);
    m_leftBack.setInverted(true);
    //m_LeftBackMotor.setInverted(false);
    m_RightBack.setInverted(true);
    m_RightFront.setInverted(true);
    m_LeftControlGroup = new SpeedControllerGroup(m_leftFront, m_leftBack);
    m_RightControlGroup = new SpeedControllerGroup(m_RightFront, m_RightBack);

    m_DiffDrive = new DifferentialDrive(m_LeftControlGroup, m_RightControlGroup);
    m_DiffDrive.setMaxOutput(0.9);

    


    //m_DiffDrive.setMaxOutput(0.70);
    //m_DiffDrive.setMaxOutput(0.85);


   // m_RightBack.setNeutralMode(NeutralMode.Brake);    

    m_Left.setMaxPeriod(.1);
    m_Left.setMinRate(.1);
    m_Left.setDistancePerPulse(0.0223);
    m_Left.setReverseDirection(false);
    m_Left.setSamplesToAverage(7);
    

    addChild( "left encoder", m_Left);

    m_Right.setMaxPeriod(.1);
    m_Right.setMinRate(.1);
    m_Right.setDistancePerPulse(0.0223);
    m_Right.setReverseDirection(true);
    m_Right.setSamplesToAverage(7);

    addChild( "right encoder", m_Right);

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
     setDefaultCommand(new ArcadeDrive());
  }

  public void set(double speed){
    m_leftFront.set(speed);
  }


  public double getLeftDistance(){
    return m_Left.getDistance();
  }

  public double getRightDistance(){
    return m_Right.getDistance();
  }

  public void resetLeftEncoder(){
    m_Left.reset();
  }

  public void resetRightEncoder(){
    m_Right.reset();
  }

  public void stopRobot(){
    m_LeftControlGroup.set(0);
    m_RightControlGroup.set(0);
  }

  public void moveDriver(){
    m_LeftControlGroup.set(0.3);
    m_RightControlGroup.set(0.3);
  }

  public double getAngle(){
    return ahrs.getAngle();
  }



  public void NarcadeDrive(double xSpeed, double zRotation) {

    double outputDiffAccel = 0.1;
    double outputDiffDeccel = 0.3;

    if(m_accelLimit == true){
      if(xSpeed > m_previousXspeedOuput + outputDiffAccel){
        xSpeed = m_previousXspeedOuput + outputDiffAccel;
      }

      if(xSpeed < m_previousXspeedOuput - outputDiffDeccel){
        xSpeed = m_previousXspeedOuput - outputDiffDeccel;
      }
    } 

    m_DiffDrive.arcadeDrive(xSpeed, zRotation);

    m_previousXspeedOuput = xSpeed;
  }

  public void arcadeDrive(double xSpeed, double zRotation , boolean squareInputs) {
    m_DiffDrive.arcadeDrive(xSpeed, zRotation, squareInputs);
  }

  public void curvatureDrive(double xSpeed, double zRotation, boolean isQuickTurn) {
    m_DiffDrive.curvatureDrive(xSpeed, zRotation, isQuickTurn);
  }

  public void tankDrive(double LeftSpeed , double RightSpeed){

    double outputDiffAccel = 0.1;

    double outputDiffDeccel = 0.3;

    if(m_accelLimit == true){



      if(RightSpeed > m_PrevRightOutput + outputDiffAccel){
        RightSpeed = m_PrevRightOutput + outputDiffAccel;
      }

      if(LeftSpeed < m_PrevLeftOutput - outputDiffDeccel){
        LeftSpeed = m_PrevLeftOutput - outputDiffDeccel;
     }

     if(RightSpeed < m_PrevRightOutput - outputDiffDeccel){
       RightSpeed = m_PrevRightOutput - outputDiffDeccel;
     }
     
    }

    m_DiffDrive.tankDrive(LeftSpeed , RightSpeed);
    
    m_PrevLeftOutput = LeftSpeed;
    m_PrevRightOutput = RightSpeed;
  }

  public void toggleBoostMode(){

  
    if(m_boostMode == false){
      m_boostMode = true;

    }else if(m_boostMode == true){
      m_boostMode = false;

    }

    

  }


   public void changeVmx(){

//    boolean accelLimit = false;  
    double cMaxMotors = 1.0f;
    double cLimitedMotors = 0.9f;

    if (m_boostMode == true)
    {
      m_accelLimit = false;
      m_DiffDrive.setMaxOutput(cMaxMotors);
    }
    else
    {
      m_accelLimit = true;
      m_DiffDrive.setMaxOutput(cLimitedMotors);
    }
  }
  
  public void setBoostMode()
  {
    toggleBoostMode();
    changeVmx();
    BoostModeState();
    moveDriver();
  }

   public void BoostModeState(){
     SmartDashboard.putBoolean("boostModeState", m_boostMode);
   }

}
