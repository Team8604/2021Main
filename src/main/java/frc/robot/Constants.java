// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
    // Debug mode
    public static boolean isDebugMode = true;

    // CAN IDs
    public static final int kPCM = 0;
    public static final int kRightLeader = 0;
    public static final int[] kRightFollowers = { 1 };
    public static final int kLeftLeader = 2;
    public static final int[] kLeftFollowers = { 3 };
    public static final int kIntakeMotor = 4;
    public static final int kBallMotor = 5;
    public static final int kShooterMotorMaster = 12;
    public static final int kShooterMotorSlave = 13;
    public static final int kHLeader = 14;
    public static final int[] kHFollowers = { 15 };
    public static final int kIntakeMotorHelper = 16;

    // Drivetrain speed modifiers
    public static final double kDriveModifierTurn = .4; // .75
    public static final double kDriveModifier = .6; // .4
    public static final double kCounterSteer = 0.28;

    // Motor speeds
    public static final double kBallTunnelMotorSpeedShoot = .3;
    public static final double kIntakeMotorHelperSpeed = 0.7;
    public static final double kBallTunnelMotorSpeedSlow = 0.3;
    public static final double kIntakeMotorSpeed = 0.70;
    public static final double kBallTunnelMotorSpeed = 0.60;

    // Inversions
    public static boolean invertDrivetrainMotors = true;
    public static final boolean kIntakeMotorInversion = true;
    public static final boolean kBallTunnelInversion = true;

    // PID Constants
    public static final int kPIDLoopIdx = 0;
    public static final int kTimeoutsMs = 30;
    public static final double kGains_Velocity_kP = .39;
    public static final double kGains_Velocity_kI = 0;
    public static final double kGains_Velocity_kD = 1;
    public static final double kGains_Velocity_kF = .057;

    public static final double kLimeLightTurn_kP = 0.029; //1/27, so max turn at limelight edge of view is 0.037037....
    public static final double kMinRotateSpeed = 0.29; //0.3;

    // Solenoids
    public static final int kShooterSolenoidExtend = 0;
    public static final int kShooterSolenoidRetract = 1;
    public static final int kIntakeSolenoidExtend = 2;
    public static final int kIntakeSolenoidRetract = 3;

    // Sensors
    public static final int kBallSensorLowerLeft = 0;
    public static final int kBallSensorLowerRight = 1;

    // Setpoints
    public static final int kTunnelDistance = 15000; //7500;
    public static final int kTunnelDistanceShoot = 1024;
    public static final double kShootShortMotorSpeed = 3000;
    public static final double kShootLongMotorSpeed = 2750;
    //Setpoints for interstellar accuracy
    public static final double kShootGreen = 3000;
    public static final double kShootYellow = 3500;
    public static final double kShootBlue = 2775;
    public static final double kShootRed = 2900;
    public static final double kMaxRPMDelta = 150;

    // Current limits
    public static final double kHDriveCurrentLimit = 35;
    public static final double kHDriveCurrentLimitTime = 0.4;
    public static final double kDriveCurrentLimitPeak = 45;
    public static final double kDriveCurrentLimitContinuous = 40;
    public static final double kDriveCurrentLimitTime = 0.4;

    // Math
    public static final double kRPM2Ticks = 2048 / (60 * 10);
    public static final double kTicks2RPM = 1 / kRPM2Ticks;

    // Other
    public static final int kSensorDebounce = 3;
    public static final double kBallTunnelDeactivateDelay = 1;
    public static final int kRequiredLimeLightGoodCycles = 15;

    // Button IDs
    public static final int kButtonA = 1;
    public static final int kButtonB = 2;
    public static final int kButtonX = 3;
    public static final int kButtonY = 4;
    public static final int kBumperL = 5;
    public static final int kBumperR = 6;

    public static final int kLeftStickX = 0;
    public static final int kLeftStickY = 1;
    public static final int kRightStickX = 4;
    public static final int kRightStickY = 5;

    public static final int kBBStickX = 0;
    public static final int kBBStickY = 1;

    public static final int kBBLTrigger = 2;
    public static final int kBBRTrigger = 3;

    public static final int kBBButtonX = 2;
    public static final int kBBButtonY = 3;
    public static final int kBBButtonA = 0;
    public static final int kBBButtonB = 1;
    public static final int kBBButtonLB = 4;
    public static final int kBBButtonRB = 5;

    public static final int kBBButtonShare = 6;
    public static final int kBBButtonOptions = 7;
    public static final int kBBButtonL3 = 8;
    public static final int kBBButtonR3 = 9;    
}
