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
import frc.robot.commands.Rejeter;
import frc.robot.commands.WedgerPos1;
import frc.robot.commands.WedgerPos2;
import frc.robot.commands.Initial;
import frc.robot.commands.MoveArmD;
import frc.robot.commands.MoveArmU;
import frc.robot.commands.Position1;
import frc.robot.commands.Position2;
import frc.robot.PIDsettings.*;
import frc.robot.commands.WedgerPos1;
import frc.robot.commands.WedgerPos2;



/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

Joystick pilote;
Joystick coPilote;
Joystick gamePad;


  public OI(){
    pilote = new Joystick(1);
    coPilote = new Joystick(0);
    gamePad = new Joystick(2);


    

    //elevator
    Button b1 = new JoystickButton(gamePad , 1);
    b1.whenPressed(new Initial(Constants.ELEVATORBOTTOM));

    Button b2 = new JoystickButton(gamePad, 2);
    b2.whenPressed(new Position1(Constants.ELEVATORMIDDLE));

    Button b3 = new JoystickButton(gamePad, 3);
    b3.whenPressed(new Position2(Constants.ELEVATORUP));

    // arm 
    //Button b4 = new JoystickButton(co Pilote, 4);
    //b4.whenPressed(new MoveArmU(Constants.ArmUp));

    //Button b5 = new JoystickButton(coPilote, 5);
    //b5.whenPressed(new MoveArmD(Constants.ArmDown));

    //grabber
    Button b6 = new JoystickButton(gamePad, 6);
    b6.whileHeld(new Aspirer());

    Button b7 = new JoystickButton(gamePad, 7);
    b7.whileHeld(new Rejeter());

    Button b8 = new JoystickButton(gamePad, 8);
    b8.whenPressed(new WedgerPos1(Constants.Wedger1));
    
    Button b9 = new JoystickButton(gamePad, 9);
    b9.whenPressed(new WedgerPos2(Constants.Wedger2));

  }

  public Joystick returnPilote(){
    return pilote;
  }

  public Joystick returnCoPilote(){
    return coPilote;
  }

  public double CoPiloteY(){
    return coPilote.getY();
  }

  public double getGamePad(){
    return gamePad.getRawAxis(1);
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
