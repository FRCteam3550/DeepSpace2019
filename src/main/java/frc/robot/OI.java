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
import frc.robot.PIDsettings.*;
import frc.robot.commands.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

Joystick pilote;
Joystick coPilote;
Joystick gamePad;
Joystick Wedger;


  public OI(){
    pilote = new Joystick(1);
    coPilote = new Joystick(0); // usb 0 for competition
    gamePad = new Joystick(2);  
   // Wedger = new Joystick(3);
    
    // wedger
    Button b8 = new JoystickButton(gamePad, 1);
    b8.whenPressed(new WadgerInitial(Constants.Wedger0));

    Button b9 = new JoystickButton(gamePad, 2);
    b9.whenPressed(new WedgerPos1(Constants.Wedger1));

    Button b10 = new JoystickButton(gamePad, 3);
    b10.whileHeld(new WedgerPos2(Constants.Wedger2));

    Button b11 = new JoystickButton(gamePad, 4);
    b11.whileHeld(new WedgerMobileBack());

    Button b12 = new JoystickButton(gamePad, 5);
    b12.whileHeld(new WegerMobileFront());


    //Grimpeuer 
   // Button b3 = new JoystickButton(gamePad, 3);
    //b3.whenPressed(new GrimperAuto());


    //CoPilote force LED Color

    // Button b4 = new JoystickButton(gamePad, 4);
    // b4.whileHeld(new Intake());
  }

  public Joystick returnWedgerJoy(){
    return Wedger;
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

  public Joystick getGamePad(){
    return gamePad;
  }

  public double getGamePadY(){
    return gamePad.getY();
  }

  public void getJoyInfo(){
    SmartDashboard.putNumber("JoyValue", coPilote.getY());
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
