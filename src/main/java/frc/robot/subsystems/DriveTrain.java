/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.Vector2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.DriveTrain.TeleopCommand;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  WPI_TalonSRX frontLeft = new WPI_TalonSRX(RobotMap.FRONT_LEFT_ID);
  WPI_TalonSRX frontRight = new WPI_TalonSRX(RobotMap.FRONT_RIGHT_ID);
  WPI_TalonSRX backLeft = new WPI_TalonSRX(RobotMap.BACK_LEFT_ID);
  WPI_TalonSRX backRight = new WPI_TalonSRX(RobotMap.BACK_RIGHT_ID);

  public static PigeonIMU gyro = new PigeonIMU(RobotMap.PIGEON_IMU_ID);

  WPI_TalonSRX driveMotors[] = { frontLeft, backLeft, frontRight, backRight };

  public DriveTrain() {
    for (int i = 0; i < driveMotors.length; i++) {
      driveMotors[i].set(ControlMode.PercentOutput, 0);

      driveMotors[i].configFactoryDefault();

      driveMotors[i].setNeutralMode(NeutralMode.Brake);
    }

    frontRight.setInverted(true);
    backRight.setInverted(true);

  }

  private double m_deadband = 0.05;
  private double m_deadbandz = .1;

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new TeleopCommand());
  }

  public void holomonicDrive(double xSpeed, double ySpeed, double rotation, double gyroAngle) {
    xSpeed = limit(xSpeed);
    xSpeed = applyDeadband(xSpeed, m_deadband);

    ySpeed = limit(ySpeed);
    ySpeed = applyDeadband(ySpeed, m_deadband);

    rotation = limit(rotation);
    rotation = applyDeadband(rotation, m_deadbandz);

    xSpeed = Math.copySign(xSpeed * xSpeed, xSpeed);
    ySpeed = Math.copySign(ySpeed * ySpeed, ySpeed);
    rotation = Math.copySign(rotation * rotation, rotation);

    Vector2d input = new Vector2d(xSpeed, ySpeed);

    input.rotate(-gyroAngle);

    double[] wheelSpeeds = new double[4];

    wheelSpeeds[0] = input.x + input.y + rotation; // FrontLeft
    wheelSpeeds[1] = -input.x + input.y - rotation; // FrontRight
    wheelSpeeds[2] = -input.x + input.y + rotation; // BackLeft
    wheelSpeeds[3] = input.x + input.y - rotation; // BackRight

    normalize(wheelSpeeds);

    frontLeft.set(ControlMode.PercentOutput, wheelSpeeds[0]);
    frontRight.set(ControlMode.PercentOutput, wheelSpeeds[1]);
    backLeft.set(ControlMode.PercentOutput, wheelSpeeds[2]);
    backRight.set(ControlMode.PercentOutput, wheelSpeeds[3]);

  }

  private double limit(double value) {
    if (value > 1.0) {
      return 1.0;
    }

    if (value < -1.0) {
      return -1.0;
    }

    return value;
  }

  private void normalize(double[] wheelSpeeds) {
    double maxMagnitude = Math.abs(wheelSpeeds[0]);
    for (int i = 1; i < wheelSpeeds.length; i++) {
      double temp = Math.abs(wheelSpeeds[i]);
      if (maxMagnitude < temp) {
        maxMagnitude = temp;
      }
    }
    if (maxMagnitude > 1.0) {
      for (int i = 0; i < wheelSpeeds.length; i++) {
        wheelSpeeds[i] = wheelSpeeds[i] / maxMagnitude;
      }
    }
  }

  private double applyDeadband(double value, double deadband) {
    if (Math.abs(value) > deadband) {
      if (value > 0.0) {
        return (value - deadband) / (1.0 - deadband);
      } else {
        return (value + deadband) / (1.0 - deadband);
      }
    }
    return 0.0;
  }

  public double getGyroAngle() {
    double[] gyroData = new double[3];

    gyro.getYawPitchRoll(gyroData);

    return gyroData[0];
  }

  public double getPitch() {
    double[] gyroData = new double[3];

    gyro.getYawPitchRoll(gyroData);

    return gyroData[1];
  }

  public void log() {

    SmartDashboard.putNumber("Yaw", getGyroAngle());
    SmartDashboard.putNumber("Pitch", getPitch());
  }
}
