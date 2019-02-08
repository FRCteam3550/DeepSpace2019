/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
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
    //double forward  = Robot.m_oi.getPilotForwardTuning().getEnhancedAxis(Robot.m_oi.returnPilote().getX());
    //double rotation = Robot.m_oi.getPilotRotationTuning().getEnhancedAxis(Robot.m_oi.returnPilote().getY());
    //Robot.m_DriveBaseSub.arcadeDrive(forward, rotation);

    Robot.m_DriveBaseSub.arcadeDrive(Robot.m_oi.returnPilote().getX(),Robot.m_oi.returnPilote().getY());
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
