/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import org.opencv.core.Mat;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  public static Hatch hatch = new Hatch();
  public static BallShooter ballShooter = new BallShooter();
  public static Winch winch = new Winch();
  public static DriveTrain driveTrain = new DriveTrain();
  public static BackClimbActuator backClimbActuator = new BackClimbActuator();
  public static FrontClimbActuator frontClimbActuator = new FrontClimbActuator();
  public static Vision vision = new Vision();

  public static OI m_oi = new OI();

  public static UsbCamera camera0;
  public static UsbCamera camera1;
  public static VideoSink server;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  private static NetworkTableInstance inst = NetworkTableInstance.getDefault();
  public static NetworkTable shuffleboard = inst.getTable("Shuffleboard");

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   * 
   * 
   */
  @Override
  public void robotInit() {
    SmartDashboard.putData("Auto mode", m_chooser);
    /*
     * Thread t = new Thread(() -> {
     * 
     * boolean allowCam1 = false;
     * 
     * UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(0);
     * camera1.setResolution(320, 240); camera1.setFPS(30); UsbCamera camera2 =
     * CameraServer.getInstance().startAutomaticCapture(1);
     * camera2.setResolution(320, 240); camera2.setFPS(30);
     * 
     * CvSink cvSink1 = CameraServer.getInstance().getVideo(camera1); CvSink cvSink2
     * = CameraServer.getInstance().getVideo(camera2); CvSource outputStream =
     * CameraServer.getInstance().putVideo("Switcher", 320, 240);
     * 
     * Mat image = new Mat();
     * 
     * while (!Thread.interrupted()) {
     * 
     * if (m_oi.driveJoystick.getRawButton(5)) { allowCam1 = true; }
     * 
     * if (m_oi.driveJoystick.getRawButton(3)) { allowCam1 = false; }
     * 
     * if (allowCam1) { cvSink2.setEnabled(false); cvSink1.setEnabled(true);
     * cvSink1.grabFrame(image); } else { cvSink1.setEnabled(false);
     * cvSink2.setEnabled(true); cvSink2.grabFrame(image); }
     * 
     * outputStream.putFrame(image); try { Thread.sleep(10); } catch (Exception e) {
     * 
     * } }
     * 
     * }); t.start();
     */
    /*
     * camera0 = CameraServer.getInstance().startAutomaticCapture(0);
     * camera0.setResolution(320, 240); camera0.setFPS(20);
     * 
     * camera1 = CameraServer.getInstance().startAutomaticCapture(1);
     * camera1.setResolution(320, 240); camera1.setFPS(20);
     * 
     * server = CameraServer.getInstance().getServer();
     */

  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode. You
   * can use it to reset any subsystem information you want to clear when the
   * robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString code to get the
   * auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons to
   * the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
     * switch(autoSelected) { case "My Auto": autonomousCommand = new
     * MyAutoCommand(); break; case "Default Auto": default: autonomousCommand = new
     * ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }

  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    log();

  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

    Robot.driveTrain.gyro.setYaw(0);

  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    log();

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  public void log() {
    driveTrain.log();
  }
}