/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

//import java.util.Timer;
import java.util.TimerTask;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.PIDSource;


/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class LidarSub  {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private I2C i2c;
  private byte[] distance;
  private java.util.Timer updater;

  private final int LIDAR_ADDR = 0x58;
  private final int LIDAR_CONFIG_REGISTER = 0x00;
 // private final int LIDAR_DISTANCE_REGISTER = 0x8f;
 private final int LIDAR_DISTANCE_REGISTER = 0x03f;


public LidarSub(Port port){

  i2c = new I2C(port, LIDAR_ADDR);
  distance = new byte[2];
  updater = new java.util.Timer();

}

public int getDistance(){
  return(int)Integer.toUnsignedLong(distance[0] << 8 ) + Byte.toUnsignedInt(distance[1]);

}


public double pidGet(){
  return getDistance();
}

public void start(){
  updater.scheduleAtFixedRate(new LIDARUpdater(), 0, 100);

}

public void start(int period){
  updater.scheduleAtFixedRate(new LIDARUpdater(), 0, period);

}

public void stop(){

  updater.cancel();
  updater = new java.util.Timer();

}

public void update(){
  i2c.write(LIDAR_CONFIG_REGISTER, 0x04); 
  Timer.delay(0.04);
  i2c.read(LIDAR_DISTANCE_REGISTER, 2, distance);
  Timer.delay(0.05); 
}

private class LIDARUpdater extends TimerTask {
  public void run(){
    update();

  }
}





}
