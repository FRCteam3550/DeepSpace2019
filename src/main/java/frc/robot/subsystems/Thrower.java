/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Spark;
import frc.robot.RobotMap;
import frc.robot.commands.*;

/**
 * Add your docs here.
 */
public class Thrower extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.\


  public static Spark m_thrower = RobotMap.Thrower;

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ThrowerManual());
  }

  public void set(double x){
    m_thrower.set(x);
  }

  public void throwBall(){
    set(0.5);
  }

  public void catchBall(){
    set(-0.5);
  }

  public void stop(){
    set(0);
  }


}
