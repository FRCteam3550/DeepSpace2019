/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.Timer;

public class TestDashboardCmd extends Command {

  private boolean m_finished = false;

  public TestDashboardCmd() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    System.out.println("----------Running TestDashboardCdm----------");
    SmartDashboard.putBoolean("TestBoolean", true);
    for(int TestNumber = 0; TestNumber <= 100; TestNumber++) {
      SmartDashboard.putNumber("TestNumber", TestNumber);
      Timer.delay(0.5);
    }
    m_finished = true;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return m_finished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    m_finished = false;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
