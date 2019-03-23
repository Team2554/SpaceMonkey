/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Winch extends Subsystem {
  public Victor winch = new Victor(RobotMap.WINCH_MOTOR_ID);

  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public void winchUp() {
    winch.set(-1);
  }

  public void winchDown() {
    winch.set(1);
  }

  // if losing, dont
  public void winchHoldingPower() {
    winch.set(Constants.WINCH_HOLDING_POWER);
  }

  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());

  }
}
