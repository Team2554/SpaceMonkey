
package frc.robot.commands.Winch;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveWinchDown extends Command {
  public MoveWinchDown() {
    requires(Robot.winch);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.winch.winchDown();

  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.winch.winchHoldingPower();
  }

  @Override
  protected void interrupted() {
    Robot.winch.winchHoldingPower();

  }
}
