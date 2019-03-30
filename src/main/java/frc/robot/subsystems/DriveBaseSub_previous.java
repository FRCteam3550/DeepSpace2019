// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.robot.subsystems;

// import edu.wpi.first.wpilibj.DigitalInput;
// import edu.wpi.first.wpilibj.DriverStation;
// import edu.wpi.first.wpilibj.command.Subsystem;
// import edu.wpi.first.wpilibj.drive.DifferentialDrive;
// import edu.wpi.first.wpilibj.Encoder;
// import frc.robot.Robot;
// import frc.robot.RobotMap;
// import frc.robot.commands.ArcadeDrive;

// import com.ctre.phoenix.motorcontrol.can.TalonSRX;
// import com.kauailabs.navx.frc.AHRS;

// import edu.wpi.first.wpilibj.SPI;
// import edu.wpi.first.wpilibj.SpeedController;
// import edu.wpi.first.wpilibj.SpeedControllerGroup;


// /**
//  * An example subsystem.  You can replace me with your own Subsystem.
//  */
// public class DriveBaseSub extends Subsystem {
//   // Put methods for controlling this subsystem
//   // here. Call these from Commands.
//   AHRS ahrs;

//   private SpeedControllerGroup m_LeftControlGroup;
//   private SpeedControllerGroup m_RightControlGroup;

//   private DifferentialDrive m_DiffDrive;

//   private static SpeedController m_leftFront = RobotMap.LeftFrontMotor;
//   private static SpeedController m_leftBack = RobotMap.LeftBackMotor;
//   private static SpeedController m_RightFront = RobotMap.RightFrontMotor;
//   private static SpeedController m_RightBack = RobotMap.RightBackMotor;

//   //encodeur 
//   public static Encoder m_Right = RobotMap.Right;
//   public static Encoder m_Left = RobotMap.Left;

//   //photo switch
//   private static DigitalInput m_driveTrainLightSensorL = RobotMap.DriveTrainLightSensorL;
//   private static DigitalInput m_driveTrainLightSensorR = RobotMap.DriveTrainLightSensorR;

  

//   public DriveBaseSub() {
//     try {
//       /* Communicate w/navX-MXP via the MXP SPI Bus.                                     */
//       /* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
//       /* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */
//       ahrs = new AHRS(SPI.Port.kMXP); 
//     } catch (RuntimeException ex ) {
//       DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
//     }
//     m_leftFront.setInverted(true);
//     m_leftBack.setInverted(true);
//     //m_LeftBackMotor.setInverted(false);
//     m_RightBack.setInverted(true);
//     m_RightFront.setInverted(true);
//     m_LeftControlGroup = new SpeedControllerGroup(m_leftFront, m_leftBack);
//     m_RightControlGroup = new SpeedControllerGroup(m_RightFront, m_RightBack);

//     m_DiffDrive = new DifferentialDrive(m_LeftControlGroup, m_RightControlGroup);

//     m_DiffDrive.setMaxOutput(1);
//     //m_DiffDrive.setMaxOutput(0.85);



//     m_Left.setMaxPeriod(.1);
//     m_Left.setMinRate(.1);
//     m_Left.setDistancePerPulse(0.0223);
//     m_Left.setReverseDirection(false);
//     m_Left.setSamplesToAverage(7);
    

//     addChild( "left encoder", m_Left);

//     m_Right.setMaxPeriod(.1);
//     m_Right.setMinRate(.1);
//     m_Right.setDistancePerPulse(0.0223);
//     m_Right.setReverseDirection(true);
//     m_Right.setSamplesToAverage(7);

//     addChild( "right encoder", m_Right);

//   }

//   @Override
//   public void initDefaultCommand() {
//     // Set the default command for a subsystem here.
//      setDefaultCommand(new ArcadeDrive());
//   }

//   public void set(double speed){
//     m_leftFront.set(speed);
//   }


//   public double getLeftDistance(){
//     return m_Left.getDistance();
//   }

//   public double getRightDistance(){
//     return m_Right.getDistance();
//   }

//   public void resetLeftEncoder(){
//     m_Left.reset();
//   }

//   public void resetRightEncoder(){
//     m_Right.reset();
//   }

//   public void stopRobot(){
//     m_LeftControlGroup.set(0);
//     m_RightControlGroup.set(0);
//   }



//   public void arcadeDrive(double xSpeed, double zRotation) {
//     m_DiffDrive.arcadeDrive(xSpeed, zRotation);
//   }

//   public void arcadeDrive(double xSpeed, double zRotation , boolean squareInputs) {
//     m_DiffDrive.arcadeDrive(xSpeed, zRotation, squareInputs);
//   }

//   public void curvatureDrive(double xSpeed, double zRotation, boolean isQuickTurn) {
//     m_DiffDrive.curvatureDrive(xSpeed, zRotation, isQuickTurn);
//   }

//   public void tankDrive(double LeftSpeed , double RightSpeed){
//     m_DiffDrive.tankDrive(LeftSpeed, RightSpeed);
//   }


  
// }