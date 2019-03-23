/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class FrontClimbActuator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public Victor frontClimbActuator = new Victor(RobotMap.CLIMB_ACTUATORS_IDS[0]);
  public static DigitalInput limitSwitchFront = new DigitalInput(2);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void RetractFrontActuator() {
    frontClimbActuator.set(1);
  }

  public void ExtendFrontActuator() {
    frontClimbActuator.set(-1);
  }

  public void StopFrontActuator() {
    frontClimbActuator.set(0);
  }

  public void SetFrontActuatorSpeed(double speed) {
    frontClimbActuator.set(speed);

  }

  public double actuatorSpeed() {
    return frontClimbActuator.get();
  }

}
