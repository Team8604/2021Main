// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static boolean isDebugMode = false;

    public static final int kLeftLeader = 20;
    public static final int kRightLeader = 23;
    public static final int kHLeader = 26;
    public static final int[] kLeftFollowers = {21,22};
    public static final int[] kRightFollowers = {24,25};
    public static final int[] kHFollowers = {27, 28};

    public static final int kIntakeMotor = 26;
    public static final int kBallMotor = 27;

    public static final int kPCM = 1;
    public static final int kIntakeSolenoid = 1;

    public static final int kButtonA = 1;
    public static final int kButtonB = 2;
    public static final int kButtonX = 3;
    public static final int kButtonY = 4;
    public static final int kBumperL = 5;
    public static final int kBumperR = 6;

    public static final int kLeftStickX = 1;
    public static final int kLeftStickY = 2;
    public static final int kRightStickX = 4;
    public static final int kRightStickY = 5;

    public static final int kBallSensorIDBase = 3;

    public static final double kIntakeMotorSpeed = 0.05;
    public static final double kBallTunnelMotorSpeed = 0.05;

    public static final double kBallTunnelDeactivateDelay = 0.5;


}
