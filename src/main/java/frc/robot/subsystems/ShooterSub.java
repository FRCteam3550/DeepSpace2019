/*----------------------------------------------------------------------------*/
/*
 Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.StopShooter;


/**
 * Add your docs here.
 */
public class ShooterSub extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

public void set(double speed)
{
  RobotMap.RightShooter.set(speed);
  RobotMap.LeftShooter.set(-speed);
}

public void intake()
{
    set(0.5);
}

public void outtake()
{
  set(-0.5);
}

public void stop()
{
  set(0);
}
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new StopShooter());
  }
}
