package frc.robot;

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
    public static final int[] kShooterMotorSlaves = { 13 };
    public static final int kHLeader = 14;
    public static final int[] kHFollowers = { 15 };
    public static final int kIntakeMotorHelper = 16;

    // Drivetrain speed modifiers
    public static final double kDriveModifierTurn = .4; // 1
    public static final double kDriveModifier = .45; // 1
    public static final double kCounterSteer = 0.28;

    // Motor speeds
    public static final double kBallTunnelMotorSpeedShoot = .3;
    public static final double kIntakeMotorHelperSpeed = 0.7;
    public static final double kBallTunnelMotorSpeedSlow = 0.3;
    public static final double kIntakeMotorSpeed = 0.40;
    public static final double kBallTunnelMotorSpeed = 0.60;

    // Inversions
    public static boolean invertDrivetrainMotors = true;
    public static final boolean kIntakeMotorInversion = true;
    public static final boolean kBallTunnelInversion = true;
    public static final boolean kShooterInversion = false;

    // PID Constants
    public static final int kPIDLoopIdx = 0;
    public static final int kTimeoutMs = 0;
    public static final int kTimeoutsMs = 0;
    public static final double kGains_Velocity_kP = .39;
    public static final double kGains_Velocity_kI = 0;
    public static final double kGains_Velocity_kD = 1;
    public static final double kGains_Velocity_kF = .057;

    public static final double kGains_Position_kP = 0.5;
    public static final double kGains_Position_kI = 0;
    public static final double kGains_Position_kD = 0;
    public static final double kGains_Position_kF = 0;
    public static final double kLimeLightTurn_kP = 0.029; //1/27, so max turn at limelight edge of view is 0.037037....
    public static final double kAutoTurn_kP = 0.01425;
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
    public static final double kMaxInchesDelta = 0.1;
    public static final double kMaxLimeLightDelta = 1;
    public static final double kMaxAutoTurnDelta = 0.75;

    // Current limits
    public static final double kHDriveCurrentLimit = 35;
    public static final double kHDriveCurrentLimitTime = 0.4;
    public static final double kDriveCurrentLimitPeak = 45;
    public static final double kDriveCurrentLimitContinuous = 40;
    public static final double kDriveCurrentLimitTime = 0.4;
    public static final double kDrivePowerRatioDuringAuto = 0.3;

    // Math
    public static final double kWheelRadiusFromCenter = 12.2325;//Center of wheel to center of drivetrain X offset
    public static final double kWheelDiameter = 6;

    public static final double kRPM2Ticks = 2048 / (60 * 10);
    public static final double kTicks2RPM = 1 / kRPM2Ticks;
    public static final double kDrivetrainGearRatio = 8.4;
    public static final double kHDrivetrainGearRatio = 5;//TODO: Find actual value

    public static final double kFudgeFactor1 = 5.022;

    public static final double kRevToInches = kFudgeFactor1 * kDrivetrainGearRatio / (kWheelDiameter * Math.PI);
    public static final double kTicksToInches = kRevToInches / 2048;
    public static final double kInchesToRev = 1/kRevToInches;
    public static final double kInchesToTicks = 1/kTicksToInches;

    public static final double kDegreesToInches = (kWheelRadiusFromCenter * Math.PI) / 180;
    public static final double kInchesToDegrees = 1/kDegreesToInches;

    // Other
    public static final int kSensorDebounce = 3;
    public static final double kBallTunnelDeactivateDelay = 1;
    public static final int kRequiredLimeLightGoodCycles = 15;
    public static final int kRequiredPIDGoodCycles = 60;

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

    public static final int kButton1 = 1;
    public static final int kButton2 = 2;
    public static final int kButton3 = 3;
    public static final int kButton4 = 4;
}

