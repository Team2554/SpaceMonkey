/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.ToggleCommand;
import frc.robot.commands.BackClimbActuator.BackClimbDown;
import frc.robot.commands.BackClimbActuator.BackClimbUp;
import frc.robot.commands.BackClimbActuator.ClimbBackward;
import frc.robot.commands.BackClimbActuator.ClimbForward;
import frc.robot.commands.Camera.EnableCameraOne;
import frc.robot.commands.Camera.EnableCameraZero;
import frc.robot.commands.DriveTrain.RotateToAngle;
import frc.robot.commands.FrontClimbActuator.FrontClimbDown;
import frc.robot.commands.FrontClimbActuator.FrontClimbUp;
import frc.robot.commands.Hatch.HatchIn;
import frc.robot.commands.Hatch.HatchOut;
import frc.robot.commands.Shooter.CaptureBall;
import frc.robot.commands.Shooter.ShootBall;
import frc.robot.commands.Shooter.ShooterIn;
import frc.robot.commands.Shooter.ShooterOut;
import frc.robot.commands.Winch.MoveWinchDown;
import frc.robot.commands.Winch.MoveWinchUp;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());

  // Joystick ID
  final public static int DRIVE_JOYSTICK_ID = 0;
  final public static int ACTION_JOYSTICK_ID = 1;

  // Moving the Winch Buttons
  final public static int MOVE_WINCH_UP_BUTTON_ID = 5;
  final public static int MOVE_WINCH_DOWN_BUTTON_ID = 3;

  // Winch comands for actionstick
  final public static int MOVE_WINCH_UP_BUTTON_ID_ACTIONSTICK = 11;
  final public static int MOVE_WINCH_DOWN_BUTTON_ID_ACTIONSTICK = 12;

  // Controlling the Shooter Buttons
  final public static int CAPTURE_BALL_BUTTON_ID = 1;
  final public static int SHOOT_BALL_BUTTON_ID = 1;

  // Enabling full drivetrain speed
  final public static int BOOST_MODE_BUTTON_ID = 2;

  // Controlling the Hatch Button
  final public static int TOGGLE_HATCH_ID = 2;

  // Controlling the Ball Shooter Actuator
  // final public static int TOGGLE_SHOOTER_ID = 2;

  // Controlling the Front Climb Actuator
  final public static int FRONT_CLIMB_UP_ID = 10;
  final public static int FRONT_CLIMB_DOWN_ID = 6;

  // Controlling the Back Climb Actuator
  final public static int BACK_CLIMB_UP_ID = 9;
  final public static int BACK_CLIMB_DOWN_ID = 5; // Actuator goes down

  // Controlling both Climb Actuators
  final public static int CLIMB_UP_ID = 7;
  final public static int CLIMB_DOWN_ID = 8;

  // Controlling the Wheel on the Back Climb Actuator
  final public static int CLIMB_MOTOR_FORWARD_ID = 4;
  final public static int CLIMB_MOTOR_BACKWARD_ID = 3;

  final public static int TOGGLE_CAMERA_0_ID = 7;
  final public static int TOGGLE_CAMERA_1_ID = 8;

  final public static int YEET_FORWARD_ID = 0; /// Temporary

  // Controlling Drive Train to turn on JoyStick
  final public static int TURN_DRIVETRAIN = 1;

  public static Joystick driveJoystick = new Joystick(DRIVE_JOYSTICK_ID);
  public static Joystick actionJoystick = new Joystick(ACTION_JOYSTICK_ID);

  JoystickButton turn90 = new JoystickButton(driveJoystick, TURN_DRIVETRAIN);

  JoystickButton moveWinchUp = new JoystickButton(driveJoystick, MOVE_WINCH_DOWN_BUTTON_ID);// final
  JoystickButton moveWinchDown = new JoystickButton(driveJoystick, MOVE_WINCH_UP_BUTTON_ID);// final

  JoystickButton captureBall = new JoystickButton(driveJoystick, CAPTURE_BALL_BUTTON_ID);// final
  JoystickButton shootBall = new JoystickButton(actionJoystick, SHOOT_BALL_BUTTON_ID);// final

  public static JoystickButton boostMode = new JoystickButton(driveJoystick, BOOST_MODE_BUTTON_ID);

  JoystickButton toggleHatch = new JoystickButton(actionJoystick, TOGGLE_HATCH_ID);

  // JoystickButton toggleShooter = new JoystickButton(actionJoystick,
  // TOGGLE_SHOOTER_ID);

  JoystickButton frontRetract = new JoystickButton(actionJoystick, FRONT_CLIMB_UP_ID);
  JoystickButton frontExtend = new JoystickButton(actionJoystick, FRONT_CLIMB_DOWN_ID);

  JoystickButton winch_up = new JoystickButton(actionJoystick, MOVE_WINCH_UP_BUTTON_ID_ACTIONSTICK);
  JoystickButton winch_down = new JoystickButton(actionJoystick, MOVE_WINCH_DOWN_BUTTON_ID_ACTIONSTICK);

  JoystickButton backRetract = new JoystickButton(actionJoystick, BACK_CLIMB_UP_ID);
  JoystickButton backExtend = new JoystickButton(actionJoystick, BACK_CLIMB_DOWN_ID);

  JoystickButton climbMotorForward = new JoystickButton(actionJoystick, CLIMB_MOTOR_FORWARD_ID);
  JoystickButton climbMotorBackward = new JoystickButton(actionJoystick, CLIMB_MOTOR_BACKWARD_ID);

  JoystickButton retractBoth = new JoystickButton(actionJoystick, CLIMB_UP_ID);
  JoystickButton extendBoth = new JoystickButton(actionJoystick, CLIMB_DOWN_ID);

  JoystickButton toggleCamera0 = new JoystickButton(driveJoystick, TOGGLE_CAMERA_0_ID);
  JoystickButton toggleCamera1 = new JoystickButton(driveJoystick, TOGGLE_CAMERA_1_ID);

  public OI() {

    captureBall.whileHeld(new CaptureBall());
    shootBall.whileHeld(new ShootBall());

    toggleHatch.whenPressed(new ToggleCommand(new HatchIn(), new HatchOut()));

    frontRetract.whileHeld(new FrontClimbUp());
    frontExtend.whileHeld(new FrontClimbDown());

    backRetract.whileHeld(new BackClimbUp()); // acutator goes up
    backExtend.whileHeld(new BackClimbDown()); // actuator goes down

    climbMotorForward.whileHeld(new ClimbForward());
    climbMotorBackward.whileHeld(new ClimbBackward());

    retractBoth.whileHeld(new FrontClimbUp());
    retractBoth.whileHeld(new BackClimbUp());

    extendBoth.whileHeld(new FrontClimbDown());
    extendBoth.whileHeld(new BackClimbDown());

    // turn90.whileHeld(new RotateToAngle(70));
    winch_down.whileHeld(new MoveWinchDown());
    winch_up.whileHeld(new MoveWinchUp());

  }

}
