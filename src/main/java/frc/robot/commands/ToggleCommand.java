/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleCommand extends Command {

  Command cmdA;
  Command cmdB;
  boolean a = true;

  public ToggleCommand(Command a, Command b) {
    cmdA = a;
    cmdB = b;
  }

  @Override
  protected void initialize() {
    if (a) {
      cmdA.start();
    } else {
      cmdB.start();
    }

    a = !a;
  }

  @Override
  protected boolean isFinished() {
    return true;
  }
}
