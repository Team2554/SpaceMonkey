/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;

public class RotateToAngle extends PIDCommand {
  int counter = 0;
  int angle;

  public RotateToAngle(int angle1) {

    super(0.1, 0.01, 0);
    angle = angle1;
    getPIDController().setInputRange(-180, 180);
    getPIDController().setOutputRange(-0.75, 0.75);
    getPIDController().setAbsoluteTolerance(2.5);
    getPIDController().setSetpoint(angle);
    getPIDController().setContinuous(true);
    requires(Robot.driveTrain);
  }

  protected void initialize() {
    Robot.driveTrain.gyro.setYaw(0);
  }

  @Override
  protected void execute() {

    if (Math.abs(Robot.m_oi.driveJoystick.getX()) > 0.5 || Math.abs(Robot.m_oi.driveJoystick.getY()) > 0.5)
      getPIDController().setSetpoint(-Robot.m_oi.driveJoystick.getDirectionDegrees());

    else
      getPIDController().setSetpoint(0);
  }

  @Override
  protected double returnPIDInput() {
    // System.out.println("Gyro Angle: " + Robot.driveTrain.getGyroAngle());
    return Robot.driveTrain.getGyroAngle();

  }

  @Override
  protected void usePIDOutput(double speed) {
    System.out.println("PID Error:" + getPIDController().getError());
    // System.out.println("Speed:" + speed);
    Robot.driveTrain.holomonicDrive(0, 0, -1 * speed, 0);
  }

  @Override
  protected boolean isFinished() {
    return false;

  }

  protected void end() {
    getPIDController().disable();
    Robot.driveTrain.holomonicDrive(0, 0, 0, 0);
  }
}
