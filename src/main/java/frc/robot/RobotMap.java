/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.drive.MecanumDrive;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reducS
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  // Talon SRX Motor IDs
  final public static int FRONT_RIGHT_ID = 1;
  final public static int FRONT_LEFT_ID = 2;
  final public static int BACK_RIGHT_ID = 3;
  final public static int BACK_LEFT_ID = 4;

  // Pigeon IMU ID
  final public static int PIGEON_IMU_ID = 5;

  // Ball Shooter Motors IDs
  // Two Motors (1 for each side)
  public final static int[] BALL_SHOOTER_MOTOR_IDS = { 5, 7 };

  // Shooter Actuator ID
  // This Actuator pushes out the ball
  public final static int BALL_SHOOTER_ACTUATOR_ID = 3;

  // Winch Actuator ID
  // This actuator controls the angle of the shooting mechanismw
  public final static int WINCH_MOTOR_ID = 9;

  // Hatch Actuator ID
  // This allows for the dropping of the hatches
  public final static int HATCH_ACTUATOR_ID = 2;

  // Climb Actuator IDS
  // Two Actuators are needed to climb with the robot
  public final static int[] CLIMB_ACTUATORS_IDS = { 8, 4 };

  public final static int SEAT_MOTOR_ID = 6;

}
