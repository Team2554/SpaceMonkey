/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ShooterIn extends Command {
  Timer timeout;

  public ShooterIn() {
    requires(Robot.ballShooter);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    timeout = new Timer();
    timeout.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.ballShooter.ballActuatorIn();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (timeout.get() > 1.5);
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.ballShooter.stopballActuator();

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.ballShooter.stopballActuator();

  }
}
