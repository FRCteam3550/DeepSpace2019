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

import TronixLib.TuningPilotAxis;


import frc.robot.commands.ArmUp;
import frc.robot.commands.Aspirer;
import frc.robot.commands.ElevatorDown;
import frc.robot.commands.ElevatorUpBalls;
import frc.robot.commands.ElevatorUpHatch;
import frc.robot.commands.FirstLevel;
import frc.robot.commands.Rejeter;
import frc.robot.commands.commandsGroup.TestCmdGroup;
import frc.robot.commands.SecondLevel;
import frc.robot.commands.JoystickSettings.pilotForwardTuningNoneCmd;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

Joystick pilote;
TuningPilotAxis m_pilotforwardtuning;
TuningPilotAxis m_pilotrotatetuning;

Joystick copilote;
TuningPilotAxis m_copilotelevatortuning;
TuningPilotAxis m_copilotarmtuning;

  public OI(){
    pilote = new Joystick(1);
    m_pilotforwardtuning = new TuningPilotAxis();  // Still mode 0 : no tuning
    m_pilotrotatetuning  = new TuningPilotAxis();  // Still mode 0 : no tuning

    copilote = new Joystick(2);

    Button b11 = new JoystickButton(pilote , 1);
    b11.whenPressed(new SecondLevel());

    Button b12 = new JoystickButton(pilote, 2);
    b12.whenPressed(new ArmUp());

    Button b13 = new JoystickButton(pilote, 3);
    b13.whenPressed(new ElevatorUpBalls());

    Button b14 = new JoystickButton(pilote, 4);
    b14.whileHeld(new Aspirer());

    Button b15 = new JoystickButton(pilote, 5);
    b15.whileHeld(new Rejeter());

    Button b16 = new JoystickButton(pilote, 6);
    b16.whenPressed(new TestCmdGroup());

    Button b21 = new JoystickButton(copilote , 1);
    b21.whenPressed(new pilotForwardTuningNoneCmd());
  }


  public Joystick returnPilote(){
    return pilote;
  }

  public TuningPilotAxis getPilotForwardTuning(){
      return m_pilotforwardtuning;
  }

  public TuningPilotAxis getPilotRotationTuning(){
      return m_pilotrotatetuning;
  }


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
