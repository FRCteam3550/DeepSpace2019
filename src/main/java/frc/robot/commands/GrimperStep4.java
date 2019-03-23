/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class GrimperStep4 extends Command {

  private double v00 = 0; //a zero
  private double v01 = 0; //a zero
  private double v02 = 0; // a determiner
  private double v03 = 0; // a determiner

  public GrimperStep4() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_grimpeur);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.m_grimpeur.setFront(v00);
    Robot.m_grimpeur.setBack(v01);
    Robot.m_grimpeur.setRoulette(v02);    
    Robot.m_grimpeur.setDriveBase(v03);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_grimpeur.setFront(v00);
    Robot.m_grimpeur.setBack(v01);
    Robot.m_grimpeur.setRoulette(v02);    
    Robot.m_grimpeur.setDriveBase(v03);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
