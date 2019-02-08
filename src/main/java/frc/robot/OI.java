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
import frc.robot.commands.Aspirer;
import frc.robot.commands.ElevatorDown;
import frc.robot.commands.ElevatorUpBalls;
import frc.robot.commands.ElevatorUpHatch;
import frc.robot.commands.Rejeter;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

Joystick pilote;
//TuningPilotAxis m_pilotforwardtuning;
//TuningPilotAxis m_pilotrotatetuning;

  public OI(){
    pilote = new Joystick(1);
//    m_pilotforwardtuning = new TuningPilotAxis  // Still mode 0 : no tuning
//    m_pilotrotatetuning  = new TuningPilotAxis  // Still mode 0 : no tuning

    Button b1 = new JoystickButton(pilote , 1);
    b1.whenPressed(new ElevatorUpHatch());

    Button b2 = new JoystickButton(pilote, 2);
    b2.whenPressed(new ElevatorDown());

    Button b3 = new JoystickButton(pilote, 3);
    b3.whenPressed(new ElevatorUpBalls());

    Button b4 = new JoystickButton(pilote, 4);
    b4.whileHeld(new Aspirer());

    Button b5 = new JoystickButton(pilote, 5);
    b5.whileHeld(new Rejeter());

  }


  public Joystick returnPilote(){
    return pilote;
  }

//  public Joystick returnPiloteForwardTuning(){
//    return m_pilotforwardtuning;
//  }

//  public Joystick returnPiloteRotationTuning(){
//    return m_pilotrotationtuning;
//  }


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
