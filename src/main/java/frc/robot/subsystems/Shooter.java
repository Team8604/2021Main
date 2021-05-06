// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  private WPI_TalonFX master;
  private WPI_TalonFX[] slaves;

  private DoubleSolenoid hoodPiston;

  private double targetSpeed;

  public Shooter() {
    master = new WPI_TalonFX(Constants.kShooterMotorMaster);
    for(int i = 0;i < Constants.kShooterMotorSlaves.length;i ++){
      slaves[i] = new WPI_TalonFX(Constants.kShooterMotorSlaves[i]);
      slaves[i].follow(master);
      slaves[i].setInverted(InvertType.OpposeMaster);
      slaves[i].configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, Constants.kDriveCurrentLimitContinuous,
        Constants.kDriveCurrentLimitPeak, Constants.kDriveCurrentLimitTime));
    }
    master.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, Constants.kDriveCurrentLimitContinuous,
        Constants.kDriveCurrentLimitPeak, Constants.kDriveCurrentLimitTime));
    hoodPiston = new DoubleSolenoid(Constants.kPCM, Constants.kShooterSolenoidExtend,
        Constants.kShooterSolenoidRetract);

    // CONFIGURE PID
    master.configFactoryDefault();
    master.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, Constants.kPIDLoopIdx,
        Constants.kTimeoutsMs);
    master.config_kF(Constants.kPIDLoopIdx, Constants.kGains_Velocity_kF, Constants.kTimeoutsMs);
    master.config_kP(Constants.kPIDLoopIdx, Constants.kGains_Velocity_kP, Constants.kTimeoutsMs);
    master.config_kI(Constants.kPIDLoopIdx, Constants.kGains_Velocity_kI, Constants.kTimeoutsMs);
    master.config_kD(Constants.kPIDLoopIdx, Constants.kGains_Velocity_kD, Constants.kTimeoutsMs);
  }

  @Override
  public void periodic() {
    if (Constants.isDebugMode) {
      SmartDashboard.putNumber("shooterMotorActual", master.getSelectedSensorVelocity() / Constants.kRPM2Ticks);
    }
  }

  public void setShooterHood(boolean extended) {
    if (Constants.isDebugMode) {
      SmartDashboard.putBoolean("shooterHoodState", extended);
    }
    if (extended) {
      hoodPiston.set(Value.kForward);
    } else {
      hoodPiston.set(Value.kReverse);
    }
    targetSpeed = 0;
  }

  public void setMotorRaw(double percentOutput) {
    if (Constants.isDebugMode) {
      SmartDashboard.putNumber("shooterMotor", percentOutput);
      SmartDashboard.putString("shooterMotorMode", "raw");
    }
    master.set(TalonFXControlMode.PercentOutput, percentOutput);
  }

  public void setMotorPID(double speedRPM) {
    if (Constants.isDebugMode) {
      SmartDashboard.putNumber("shooterMotorTarget", speedRPM);
      SmartDashboard.putString("shooterMotorMode", "PID");
    }
    master.set(TalonFXControlMode.Velocity, speedRPM * Constants.kRPM2Ticks);
    targetSpeed = speedRPM;
  }

  public boolean motorWithinRPM(double targetRPM, double tolerance) {
    double currentDelta = targetRPM - (master.getSelectedSensorVelocity() * Constants.kTicks2RPM);
    return currentDelta < tolerance && currentDelta > -tolerance;
  }

  public boolean motorWithinRPM(double tolerance) {
    return motorWithinRPM(targetSpeed, tolerance);
  }
}
