/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.FrontClimbActuator;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class FrontClimbUp extends Command {

  Timer limitTimer;
  boolean startTimer = false;

  public FrontClimbUp() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.frontClimbActuator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    limitTimer = new Timer();

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    if (Robot.frontClimbActuator.limitSwitchFront.get())
      Robot.frontClimbActuator.SetFrontActuatorSpeed(0);

    else
      Robot.frontClimbActuator.RetractFrontActuator();

    // Robot.frontClimbActuator.RetractFrontActuator();

  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.frontClimbActuator.StopFrontActuator();
  }

  @Override
  protected void interrupted() {
    Robot.frontClimbActuator.StopFrontActuator();
  }
}
