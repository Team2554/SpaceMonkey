/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.networktables.*;

import com.ctre.phoenix.sensors.PigeonIMU;

class GenericPIDOutput implements PIDOutput {
  private double pidOutput = 0;

  @Override
  public void pidWrite(double output) {
    pidOutput = output;
  }

  public double getPIDValue() {
    return pidOutput;
  }
}

class NetworkTablePIDSource implements PIDSource {
  private NetworkTableEntry value;

  public NetworkTablePIDSource(NetworkTableEntry value) {
    this.value = value;
  }

  @Override
  public void setPIDSourceType(PIDSourceType pidSource) {
    // suck it
  }

  @Override
  public PIDSourceType getPIDSourceType() {
    return PIDSourceType.kDisplacement;
  }

  @Override
  public double pidGet() {
    return value.getDouble(0.0);
  }
}

class UltrasonicPIDSource implements PIDSource {
  private AnalogInput sensor;

  public UltrasonicPIDSource(AnalogInput sensor) {
    this.sensor = sensor;
  }

  @Override
  public void setPIDSourceType(PIDSourceType pidSource) {
    // suck it
  }

  @Override
  public PIDSourceType getPIDSourceType() {
    return PIDSourceType.kDisplacement;
  }

  @Override
  public double pidGet() {
    return sensor.getVoltage() / 2.0;
  }

}

/**
 * Add your docs here.
 */
public class Vision extends Subsystem {
  public final double DISTANCE_SETPOINT = 50.0; // in centimeters the distance we want to maintain

  private NetworkTable visionTable = Robot.shuffleboard.getSubTable("Vision");

  private NetworkTableEntry connected = visionTable.getEntry("connected");
  private NetworkTableEntry exists = visionTable.getEntry("target_exists");
  private NetworkTableEntry angle = visionTable.getEntry("yaw_angle");
  private NetworkTableEntry pixelDiff = visionTable.getEntry("pixel_diff");

  private NetworkTablePIDSource angleIn = new NetworkTablePIDSource(angle);
  private GenericPIDOutput angleOut = new GenericPIDOutput();
  private PIDController anglePID = new PIDController(1.0, 0.0, 0.0, angleIn, angleOut);

  private NetworkTablePIDSource pixelIn = new NetworkTablePIDSource(pixelDiff);
  private GenericPIDOutput pixelOut = new GenericPIDOutput();
  private PIDController pixelPID = new PIDController(1.0, 0.0, 0.0, pixelIn, pixelOut);

  public Vision() {
    anglePID.setSetpoint(0.0);
    pixelPID.setSetpoint(0.0);

    anglePID.setInputRange(-1, 1);
    pixelPID.setInputRange(-1, 1);

    anglePID.disable();
    pixelPID.disable();

    anglePID.reset();
    pixelPID.reset();
  }

  public void init() {
    anglePID.enable();
    pixelPID.enable();
  }

  public void end() {
    anglePID.disable();
    pixelPID.disable();

    anglePID.reset();
    pixelPID.reset();
  }

  public double getAngle() {
    return angle.getDouble(-999.9);
  }

  public double getPixel() {
    return pixelDiff.getDouble(-999.9);
  }

  public double getPIDOutputAngle() {
    return pixelOut.getPIDValue();
  }

  public double getPIDOutputPixel() {
    return pixelOut.getPIDValue();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
