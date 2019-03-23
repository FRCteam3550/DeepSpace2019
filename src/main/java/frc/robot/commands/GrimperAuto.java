/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.*;

public class GrimperAuto extends CommandGroup {
  /**
   * Add your docs here.
   */

  //en seconde. 
   private double dureeTemps0 = 5;
   private double dureeTemps1 = 0;
   private double dureeTemps2 = 0;
   private double dureeTemps3 = 0;
   private double dureeTemps4 = 0;
   private double dureeTemps5 = 0;

  public GrimperAuto() {
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.
    addSequential(new GrimperStep0() , dureeTemps0);
    addSequential(new GrimperStep1() , dureeTemps1);
    addSequential(new GrimperStep2() , dureeTemps2);
    addSequential(new GrimperStep3() , dureeTemps3);
    addSequential(new GrimperStep4() , dureeTemps4);
    addSequential(new GrimperStep5() , dureeTemps5);

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}
