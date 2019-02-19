/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.util.TuningPilotAxis;

//import TronixLib.TuningPilotAxis;

/**
 * An example command.  You can replace me with your own command.
 */
public class ArcadeDrive extends Command {
  //TuningPilotAxis m_forwardtuning;
  //TuningPilotAxis m_rotatetuning;
  //TuningPilotAxis TuningP;

  public ArcadeDrive() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_DriveBaseSub);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_DriveBaseSub.stopRobot();
   // TuningP = new TuningPilotAxis();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    Robot.m_tuningpilotaxis.TuningPilotAxis();

    double forward  = Robot.m_tuningpilotaxis.getEnhancedAxis(Robot.m_oi.returnPilote().getY());
    double turning = Robot.m_tuningpilotaxis.getEnhancedAxis(Robot.m_oi.returnPilote().getX());
    double forwardSpeed = Robot.m_DriveBaseSub.getCurrentForwardSpeed();
    double turningSpeed = Robot.m_DriveBaseSub.getCurrentTurningSpeed();
    //Robot.m_DriveBaseSub.arcadeDrive(forward, rotation);
    //
   // Robot.m_DriveBaseSub.arcadeDrive(Yaxis, Xaxis, forwardSpeed, turningSpeed); 
   Robot.m_DriveBaseSub.arcadeDrive(-forward, turning, forwardSpeed, turningSpeed); 
 /*   SmartDashboard.putNumber("YaxisFiltered",Robot.m_oi.filterYaxis() );
    SmartDashboard.putNumber("XaxisFiltered",Robot.m_oi.filterXaxis() );*/

    //Robot.m_DriveBaseSub.arcadeDrive(-Robot.m_oi.returnPilote().getY(),Robot.m_oi.returnPilote().getX());
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
