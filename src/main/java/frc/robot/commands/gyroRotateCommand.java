/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class gyroRotateCommand extends Command {

  private double OtherColor;

  private double initialAngle;
  private double lastError;

  private double Kp = -0.03;
  private double Ki = -0.002;
  private double Kd = -0.001;

  private double errorSum = 0;
  private double offset = 0;
  private double targetAngle;

  private double error;

  private double lastVel = 0;

  public final double EPSILON = 0.5;

  public gyroRotateCommand(double offset) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_DriveBaseSub);
    this.offset = offset;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
      offset = SmartDashboard.getNumber("targetAngle", 0);
      offset *= 0.8;
      SmartDashboard.putNumber("Offset", offset);
      lastError = offset;
      targetAngle = Robot.m_DriveBaseSub.getAngle() + offset;
      error = offset;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
        double output = 0;
        error = targetAngle - Robot.m_DriveBaseSub.getAngle();
        output += error*Kp;
        output += errorSum*Ki;
        output += (lastError - error)*Kd;
        SmartDashboard.putNumber("Error", error);
        SmartDashboard.putNumber("Error Sum", errorSum);
        lastError = error;
        errorSum += error;
        lastVel = (lastError-error);
        Robot.m_DriveBaseSub.arcadeDrive(0, output, false);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (Math.abs(error)) <= EPSILON ? true : false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    
    // Si on veut le reset a la couleur de l'alliance
    // (Il ce peut que ce code ne fonctionne pas, mais on espere qu'il fonctionne)
    
    // Si on veut le mettre mauve
    //RobotMap.ledController.setSpeed(speed);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
