/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.Victor;

/**
 * Add your docs here.
 */
public class Hatch extends Subsystem {
  public Victor hatchActuator = new Victor(RobotMap.HATCH_ACTUATOR_ID);
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public void hatchIn() {
    hatchActuator.set(Constants.ACTUATOR_SPEED);

  }

  public void hatchOut() {
    hatchActuator.set(-Constants.ACTUATOR_SPEED);

  }

  public void StopHatch() {
    hatchActuator.set(0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());

  }
}
