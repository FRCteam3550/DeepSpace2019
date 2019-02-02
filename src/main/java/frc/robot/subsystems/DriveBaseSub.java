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
import frc.robot.RobotMap;
import frc.robot.commands.ArcadeDrive;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;


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

  private SpeedController m_LeftFrontMotor = RobotMap.LeftFrontMotor;
  private SpeedController m_LeftBackMotor = RobotMap.LeftBackMotor;
  private SpeedController m_RightFrontMotor = RobotMap.RightFrontMotor;
  private SpeedController m_RightBackMotor = RobotMap.RightBackMotor;

  //photo switch
  private static DigitalInput m_driveTrainLightSensorL = RobotMap.DriveTrainLightSensorL;
  private static DigitalInput m_driveTrainLightSensorR = RobotMap.DriveTrainLightSensorR;

  

  public DriveBaseSub() {
    try {
      /* Communicate w/navX-MXP via the MXP SPI Bus.                                     */
      /* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
      /* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */
      ahrs = new AHRS(SPI.Port.kMXP); 
    } catch (RuntimeException ex ) {
      DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
    }
    m_LeftFrontMotor.setInverted(true);
    m_LeftBackMotor.setInverted(true);
    //m_LeftBackMotor.setInverted(false);
    m_RightBackMotor.setInverted(true);
    m_RightFrontMotor.setInverted(true);
    m_LeftControlGroup = new SpeedControllerGroup(m_LeftFrontMotor, m_LeftBackMotor);
    m_RightControlGroup = new SpeedControllerGroup(m_RightFrontMotor, m_RightBackMotor);

    m_DiffDrive = new DifferentialDrive(m_LeftControlGroup, m_RightControlGroup);

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
     setDefaultCommand(new ArcadeDrive());
  }

  public void stopRobot(){
    m_LeftControlGroup.set(0);
    m_RightControlGroup.set(0);
  }



  public void arcadeDrive(double xSpeed, double zRotation) {
    m_DiffDrive.arcadeDrive(xSpeed, zRotation);
  }

  public void arcadeDrive(double xSpeed, double zRotation , boolean squareInputs) {
    m_DiffDrive.arcadeDrive(xSpeed, zRotation, squareInputs);
  }

  public void curvatureDrive(double xSpeed, double zRotation, boolean isQuickTurn) {
    m_DiffDrive.arcadeDrive(xSpeed, zRotation, isQuickTurn);

  }

  
  
}
