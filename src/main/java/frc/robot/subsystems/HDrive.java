// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;


public class HDrive extends SubsystemBase {
  private WPI_TalonFX leftLeader;
  private WPI_TalonFX rightLeader;
  private WPI_TalonFX hLeader;
  private WPI_TalonFX[] leftFollowers;
  private WPI_TalonFX[] rightFollowers;
  private WPI_TalonFX[] hFollowers;
  private DifferentialDrive differentialDrive;

  public HDrive() {
    // Init Left Leader
    leftLeader = new WPI_TalonFX(Constants.kLeftLeader);
    leftLeader.setInverted(Constants.invertDrivetrainMotors);
    leftLeader.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true,
        Constants.kDriveCurrentLimitContinuous, Constants.kDriveCurrentLimitPeak, Constants.kDriveCurrentLimitTime));
    // Init Right Leader
    rightLeader = new WPI_TalonFX(Constants.kRightLeader);
    rightLeader.setInverted(Constants.invertDrivetrainMotors);
    rightLeader.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true,
        Constants.kDriveCurrentLimitContinuous, Constants.kDriveCurrentLimitPeak, Constants.kDriveCurrentLimitTime));
    // Init H Leader
    hLeader = new WPI_TalonFX(Constants.kHLeader);
    hLeader.configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, Constants.kHDriveCurrentLimit,
        Constants.kHDriveCurrentLimit, Constants.kHDriveCurrentLimitTime));
    // Init left followers
    leftFollowers = new WPI_TalonFX[Constants.kLeftFollowers.length];
    for (int i = 0; i < leftFollowers.length; ++i) {
      leftFollowers[i] = new WPI_TalonFX(Constants.kLeftFollowers[i]);
      leftFollowers[i].setInverted(Constants.invertDrivetrainMotors);
      leftFollowers[i].configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true,
          Constants.kDriveCurrentLimitContinuous, Constants.kDriveCurrentLimitPeak, Constants.kDriveCurrentLimitTime));
      leftFollowers[i].follow(leftLeader);
    }
    // init right followers
    rightFollowers = new WPI_TalonFX[Constants.kRightFollowers.length];
    for (int i = 0; i < rightFollowers.length; ++i) {
      rightFollowers[i] = new WPI_TalonFX(Constants.kRightFollowers[i]);
      rightFollowers[i].setInverted(Constants.invertDrivetrainMotors);
      rightFollowers[i].configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true,
          Constants.kDriveCurrentLimitContinuous, Constants.kDriveCurrentLimitPeak, Constants.kDriveCurrentLimitTime));
      rightFollowers[i].follow(rightLeader);
    }

    hFollowers = new WPI_TalonFX[Constants.kHFollowers.length];
    for (int i = 0; i < hFollowers.length; ++i) {
      hFollowers[i] = new WPI_TalonFX(Constants.kHFollowers[i]);
      hFollowers[i].configStatorCurrentLimit(new StatorCurrentLimitConfiguration(true, Constants.kHDriveCurrentLimit,
          Constants.kHDriveCurrentLimit, Constants.kHDriveCurrentLimitTime));
      hFollowers[i].follow(hLeader);
    }
    differentialDrive = new DifferentialDrive(leftLeader, rightLeader);

    leftLeader.configFactoryDefault();
    leftLeader.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, Constants.kPIDLoopIdx,
        Constants.kTimeoutsMs);
    leftLeader.config_kF(Constants.kPIDLoopIdx, Constants.kGains_Position_kF, Constants.kTimeoutsMs);
    leftLeader.config_kP(Constants.kPIDLoopIdx, Constants.kGains_Position_kP, Constants.kTimeoutsMs);
    leftLeader.config_kI(Constants.kPIDLoopIdx, Constants.kGains_Position_kI, Constants.kTimeoutsMs);
    leftLeader.config_kD(Constants.kPIDLoopIdx, Constants.kGains_Position_kD, Constants.kTimeoutsMs);

    rightLeader.configFactoryDefault();
    rightLeader.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, Constants.kPIDLoopIdx,
        Constants.kTimeoutsMs);
    rightLeader.config_kF(Constants.kPIDLoopIdx, Constants.kGains_Position_kF, Constants.kTimeoutsMs);
    rightLeader.config_kP(Constants.kPIDLoopIdx, Constants.kGains_Position_kP, Constants.kTimeoutsMs);
    rightLeader.config_kI(Constants.kPIDLoopIdx, Constants.kGains_Position_kI, Constants.kTimeoutsMs);
    rightLeader.config_kD(Constants.kPIDLoopIdx, Constants.kGains_Position_kD, Constants.kTimeoutsMs);

  }

  public void arcadeDrive(double moveSpeed, double rotateSpeed) {
    differentialDrive.arcadeDrive(moveSpeed, rotateSpeed);
  }

  public void set(double leftSpeed, double rightSpeed) {
    leftLeader.set(leftSpeed);
    rightLeader.set(rightSpeed);
  }

  public double getHCurrent() {
    return hLeader.getStatorCurrent();
  }

  public void set(double leftSpeed, double rightSpeed, double hSpeed) {
    leftLeader.set(leftSpeed);
    rightLeader.set(rightSpeed);
    hLeader.set(hSpeed);
  }

  public void set(double hSpeed) {
    hLeader.set(hSpeed);
  }

  public void setLeftPID(double targetInches) {
    leftLeader.set(TalonFXControlMode.Position, targetInches * Constants.kInchesToTicks);
  }

  public void setRightPID(double targetInches) {
    rightLeader.set(TalonFXControlMode.Position, targetInches * Constants.kInchesToTicks);
  }

  public double getLeftPIDError(){
    return leftLeader.getClosedLoopError(Constants.kPIDLoopIdx) * Constants.kTicksToInches;
  }

  public double getRightPIDError(){
    return rightLeader.getClosedLoopError(Constants.kPIDLoopIdx) * Constants.kTicksToInches;
  }

  @Override
  public void periodic() {
  }
}
