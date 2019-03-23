/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.Victor;
import frc.robot.Constants;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class BallShooter extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  Victor leftMotorShooter = new Victor(RobotMap.BALL_SHOOTER_MOTOR_IDS[0]);
  Victor rightMotorShooter = new Victor(RobotMap.BALL_SHOOTER_MOTOR_IDS[1]);

  public Victor ballAcutator = new Victor(RobotMap.BALL_SHOOTER_ACTUATOR_ID);

  WaitCommand wait = new WaitCommand(1.5);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void captureBall() {
    leftMotorShooter.set(0.2);
    rightMotorShooter.set(0.2);
  }

  public void shootBall() {
    leftMotorShooter.set(-0.2);
    rightMotorShooter.set(-0.2);

  }

  public void ShootBallWithoutActuator() {
    leftMotorShooter.set(-.05);
    rightMotorShooter.set(.05);
  }

  public void ballActuatorIn() {
    ballAcutator.set(0.3);
  }

  public void ballActuatorOut() {
    ballAcutator.set(-0.5);
  }

  public void stopballActuator() {
    ballAcutator.set(0);
  }

  public void stopShooter() {
    leftMotorShooter.set(0);
    rightMotorShooter.set(0);
  }
}
