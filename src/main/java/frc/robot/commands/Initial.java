/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Initial extends Command {

  private double PosititonInitial;

  public Initial(double PosititonInitial) {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_elevateur);
    this.PosititonInitial = PosititonInitial;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    Robot.m_elevateur.configInitial();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_elevateur.goInitial(PosititonInitial);
    if(Robot.m_elevateur.getSensorPosition() < 200){
      Robot.m_elevateur.setSensorAt0();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_elevateur.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
