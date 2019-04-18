/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

//import TronixLib.TuningPilotAxis;

/**
 * An example command.  You can replace me with your own command.
 */
public class ArcadeDrive extends Command {
  //TuningPilotAxis m_forwardtuning;
  //TuningPilotAxis m_rotatetuning;

  public ArcadeDrive() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_DriveBaseSub);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_DriveBaseSub.stopRobot();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // Arcade Drive version 1: 1 joystick : un joystick pour avancer et tourner axes y et x
  Robot.m_DriveBaseSub.arcadeDrive(-Robot.m_oi.returnPilote().getY(),Robot.m_oi.returnPilote().getX());
  
  // Arcade Drive version 2: 1 joystick : un joystick pour avancer et tourner axes y et z
  
//  Robot.m_DriveBaseSub.arcadeDrive(-Robot.m_oi.returnPilote().getY(),Robot.m_oi.returnPilote().getZ());
    //Robot.m_DriveBaseSub.set(Robot.m_oi.returnPilote().getY());

    // Curvature Drive : 1 joystick : un joystick pour avancer et tourner axes y et z
  
  //Robot.m_DriveBaseSub.curvatureDrive(-Robot.m_oi.returnPilote().getY(),Robot.m_oi.returnPilote().getZ(),true);
  //Robot.m_DriveBaseSub.set(Robot.m_oi.returnPilote().getY());

   // Tank Drive : 2 joysticks : un joystick pour avancer et un joystick pour tourner
  // Robot.m_DriveBaseSub.tankDrive(-Robot.m_oi.returnPilote().getY() , -Robot.m_oi.returnCoPilote().getY());


  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_DriveBaseSub.stopRobot();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
