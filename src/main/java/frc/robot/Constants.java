/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public class Constants {

    // Winch Holding Power
    final public static double WINCH_HOLDING_POWER = 0.05;

    final public static double ACTUATOR_SPEED = 0.5;

    // IR Sensor PID Constants

    final public static double IR_KP = 25;
    final public static double IR_KI = 0;
    final public static double IR_KD = 15;

    /**
     * Which PID slot to pull gains from. Starting 2018, you can choose from 0,1,2
     * or 3. Only the first two (0,1) are visible in web-based configuration.
     */
    public static final int kSlotIdx = 0;

    /**
     * Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops. For now
     * we just want the primary one.
     */
    public static final int kPIDLoopIdx = 0;

    /**
     * Set to zero to skip waiting for confirmation, set to nonzero to wait and
     * report to DS if action fails.
     */
    public static final int kTimeoutMs = 30;

    /**
     * PID Gains may have to be adjusted based on the responsiveness of control
     * loop. kF: 1023 represents output value to Talon at 100%, 7200 represents
     * Velocity units at 100% output
     * 
     * kP kI kD kF Iz PeakOut
     */
    public final static Gains kFrontLeft_Gains_Velocity = new Gains(.11333, 0.001, 20, .1097, 300, 1.00);
    public final static Gains kFrontRight_Gains_Velocity = new Gains(0.11333, 0.001, 20, .1097, 300, 1.00);
    public final static Gains kBackLeft_Gains_Velocity = new Gains(0.11333, 0.001, 20, .1097, 300, 1.00);
    public final static Gains kBackRight_Gains_Velocity = new Gains(0.11333, 0.001, 20, .1097, 300, 1.00);

    public final static Gains[] velocityGains = { kFrontLeft_Gains_Velocity, kFrontRight_Gains_Velocity,
            kBackLeft_Gains_Velocity, kBackRight_Gains_Velocity };

}
