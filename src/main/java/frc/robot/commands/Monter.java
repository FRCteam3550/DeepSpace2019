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

public class Monter extends Command {

  private boolean RobotPret = false;
  private double timer = 0;
  
  public Monter() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.m_grimpeur);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    timer++;
    
    if(timer <= 200){ // 4 secondes
      Robot.m_grimpeur.Monter();
    }else if(timer <= 400 && timer > 200){ // entre 4 et 8 secondes.
      Robot.m_grimpeur.stopFront();
      Robot.m_grimpeur.setBack(0.50);
      RobotPret = true;
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
    SmartDashboard.putBoolean("position robot PRET", RobotPret );
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
