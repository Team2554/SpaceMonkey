/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import edu.wpi.first.networktables.*;

import java.time.*;

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

class GenericPIDSource implements PIDSource {
  NetworkTableEntry value;

  public GenericPIDSource(NetworkTableEntry value) {
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

/**
 * Add your docs here.
 */
public class Vision extends Subsystem {
  private NetworkTable visionTable = Robot.shuffleboard.getSubTable("Vision");

  private NetworkTableEntry connected = visionTable.getEntry("connected");
  private NetworkTableEntry exists = visionTable.getEntry("target_exists");
  private NetworkTableEntry angle = visionTable.getEntry("yaw_angle");
  private NetworkTableEntry pixelDiff = visionTable.getEntry("pixel_diff");

  private GenericPIDSource angleIn = new GenericPIDSource(angle);
  private GenericPIDOutput angleOut = new GenericPIDOutput();
  private PIDController anglePID = new PIDController(1.0, 0.0, 0.0, angleIn, angleOut);

  private GenericPIDSource pixelIn = new GenericPIDSource(pixelDiff);
  private GenericPIDOutput pixelOut = new GenericPIDOutput();
  private PIDController pixelPID = new PIDController(1.0, 0.0, 0.0, pixelIn, pixelOut);

  public static PigeonIMU imu = new PigeonIMU(RobotMap.PIGEON_IMU_ID);

  private boolean doneAlign = false;
  private boolean doneEverything = false;

  private short[] imuVals = new short[3];

  private LocalDateTime startTime;

  public Vision() {
    anglePID.setSetpoint(0.0);
    pixelPID.setSetpoint(0.0);

    anglePID.setInputRange(-1, 1);
    pixelPID.setInputRange(-1, 1);
  }

  public void init() {
    anglePID.reset();
    pixelPID.reset();

    startTime = LocalDateTime.now();
  }

  public void excecute() {
    while (!(boolean) exists.getBoolean(false)) {
      LocalDateTime currTime = LocalDateTime.now();
      long diff = Duration.between(startTime, currTime).getSeconds();
      if (diff > 3) {
        doneEverything = true;
        return;
      }
    }
    if (!doneAlign) {
      Robot.driveTrain.holomonicDrive(pixelOut.getPIDValue(), 0, angleOut.getPIDValue(), 0.0);
      if (Math.abs(angle.getDouble(-999.9)) < 2 && Math.abs(pixelDiff.getDouble(-999.9)) < 20) {
        anglePID.reset();
        doneAlign = true;
      }
    } else {
      Robot.driveTrain.holomonicDrive(0, 0.5, angleOut.getPIDValue(), 0.0);
      imu.getBiasedAccelerometer(imuVals);
      double x = (((double) imuVals[0]) / 16384.0) * 9.81; // Converting to G's and then m/s^2
      double y = (((double) imuVals[1]) / 16384.0) * 9.81;

      double netAccel = Math.sqrt((x * x) + (y * y)); // Pythogorean theorem
      if (netAccel < 1.0)
        doneEverything = true;
    }
  }

  public boolean isDone() {
    return doneEverything;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
