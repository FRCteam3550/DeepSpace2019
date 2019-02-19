/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;



import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import TronixLib.TuningPilotAxis;
import TronixLib.JoystickTronix;

import frc.robot.commands.ArmUp;
import frc.robot.commands.Aspirer;
import frc.robot.commands.ElevatorDown;
import frc.robot.commands.ElevatorUpBalls;
import frc.robot.commands.ElevatorUpHatch;
import frc.robot.commands.FirstLevel;
import frc.robot.commands.Rejeter;
import frc.robot.commands.SecondLevel;
//import frc.robot.util.JoystickTronix;
//import frc.robot.util.TuningPilotAxis;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

//Joystick m_pilote.getJoystick();

JoystickTronix m_pilote;
//TuningPilotAxis m_pilotforwardTuning;
JoystickTronix dummy;


  public OI(){
   //m_pilote.getJoystick() = new Joystick(0);
   m_pilote = new JoystickTronix(0);

  // m_pilotforwardTuning = new TuningPilotAxis();
  // m_pilotrotationTuning = new TuningPilotAxis();

   

    Button b1 = new JoystickButton(m_pilote.getJoystick() , 1);
    b1.whenPressed(new SecondLevel());

    Button b2 = new JoystickButton(m_pilote.getJoystick(), 2);
    b2.whenPressed(new ArmUp());

    Button b3 = new JoystickButton(m_pilote.getJoystick(), 3);
    b3.whenPressed(new ElevatorUpBalls());

    Button b4 = new JoystickButton(m_pilote.getJoystick(), 4);
    b4.whileHeld(new Aspirer());

    Button b5 = new JoystickButton(m_pilote.getJoystick(), 5);
    b5.whileHeld(new Rejeter());
  }
  
  
  

 //JoystickTronix m_currentJoystick;



  public JoystickTronix returnPilote(){
    return m_pilote;
  }
  
 /* public double filterFwdaxis(){
    return filter(returnPilote().getY());
   }
   
   public double filterSideaxis(){
     return filter(returnPilote().getX());
    }


   public double filter(double input){
     if (input >= .2) return input;
     
     if (input <= -.15) return input;

     SmartDashboard.putNumber(, value);
 
     return 0;
     
   } 
*/


  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());


}
