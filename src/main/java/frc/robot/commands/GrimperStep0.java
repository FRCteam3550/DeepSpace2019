/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class GrimperStep0 extends Command {

  private double v00 = 0; //seul element qui va bouger (doit etre negatif)
  private double v01 = 0;
  private double v02 = 0;
  private double v03 = 0;

  public GrimperStep0() {
    // Use requires() here to declare subsystem dependencies
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
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    //utiliser un delay
    // ou utiliser la position de lencodeur. 
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
