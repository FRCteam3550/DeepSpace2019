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

public class BrasPos3 extends Command {

  private double position;

  public BrasPos3(double position) {

    // Use requires() here to declare subsystem dependencies
     //requires(Robot.m_arm);
      this.position = position;
    //  setTimeout(2);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    //Robot.m_arm.ConfigArmDown();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
   //Robot.m_arm.moveArmDown(position);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    //SmartDashboard.putNumber("wedger Position1", Robot.m_arm.getPosition());
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
   // Robot.m_arm.stopArm();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
