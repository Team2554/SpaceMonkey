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
public class BackClimbActuator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  Victor backClimbActuator = new Victor(RobotMap.CLIMB_ACTUATORS_IDS[1]);
  Victor seatMotor = new Victor(RobotMap.SEAT_MOTOR_ID);
  public static DigitalInput limitSwitchBack = new DigitalInput(1);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void RetractBackActuator() {
    backClimbActuator.set(1);
  }

  public void ExtendBackActuator() {
    backClimbActuator.set(-1);
  }

  public void SeatMotorForward() {
    seatMotor.set(1);
  }

  public void SeatMotorBackward() {
    seatMotor.set(-1);
  }

  public void StopBackActuator() {
    backClimbActuator.set(0);
  }

  public void StopSeatMotor() {
    seatMotor.set(0);
  }

  public void SetBackClimbActuator(double speed) {
    backClimbActuator.set(speed);
  }

}
