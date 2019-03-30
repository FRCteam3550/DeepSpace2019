/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import java.util.ArrayList;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;


public class GrimpeurManualFront extends Command {
  private ArrayList<Float> timelist;
  private ArrayList<Double> gyroAnglelist;

  public GrimpeurManualFront() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_grimpeur);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    timelist = new ArrayList<>();
    gyroAnglelist = new ArrayList<>();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
   //Robot.m_grimpeur.setBack(-Robot.m_oi.getGamePad().getY());
   if ( Robot.m_oi.getGamePad().getRawButton(4)){
    Robot.m_grimpeur.setFront(Robot.m_oi.getGamePad().getY());
   }
   else {

  if (Robot.m_grimpeur.getPositionDroit() < 0 && Robot.m_oi.getGamePad().getY() < 0 ) {
    Robot.m_grimpeur.setFront(0);
  }
  else {
    Robot.m_grimpeur.setFront(Robot.m_oi.getGamePad().getY());
  }
}

  // Robot.m_grimpeur.setPusher(Robot.m_oi.returnCoPilote().getX());
  }


  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {

   // recordPathingData(timelist, gyroAnglelist, "/home/lvuser/gyrolistTime.txt", "/home/lvuser/GyrolistTime.txt");
    
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end(); 
  }

  public static void recordPathingData(List<Float> dataTime, List<Double> data, String filename1, String filename2)
  {

  try{
    FilterOutputStream fileOutputStream1 = new FilterOutputStream(new BufferedOutputStream(new FileOutputStream(filename1)));
    FilterOutputStream fileOutputStream = new FilterOutputStream(new BufferedOutputStream(new FileOutputStream(filename2)));
     
    PrintStream printStream1 = new PrintStream(fileOutputStream1);
    PrintStream printStream = new PrintStream(fileOutputStream);

    for (int i=0; i<data.size();i++){
      printStream1.println(dataTime.get(i));
      printStream1.println("");
      printStream.println(dataTime.get(i));
      printStream.println("");

    }
    printStream1.close();
    printStream.close();

    } catch (IOException e) {
    System.out.println("Can't create newDatafile");
      e.printStackTrace();
    }
    System.err.println("Data saved");

  }

}
