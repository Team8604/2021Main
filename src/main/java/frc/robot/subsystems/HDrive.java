// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class HDrive extends SubsystemBase {
  /** Creates a new Drivetrain. */
  
  private WPI_TalonFX leftLeader;
  private WPI_TalonFX rightLeader;
  private WPI_TalonFX hLeader;
  private WPI_TalonFX [] leftFollowers;
  private WPI_TalonFX [] rightFollowers;
  private WPI_TalonFX [] hFollowers;
  private SpeedControllerGroup leftMotors;
  private SpeedControllerGroup rightMotors;
  private SpeedControllerGroup hMotors;
  private DifferentialDrive differentialDrive;

  public HDrive() {
    // Init Left Leader
    leftLeader  = new WPI_TalonFX(Constants.kLeftLeader);
    // Init Right Leader
    rightLeader  = new WPI_TalonFX(Constants.kRightLeader);

    hLeader = new WPI_TalonFX(Constants.kHLeader);

    // init left followers
    leftFollowers = new WPI_TalonFX[Constants.kLeftFollowers.length];
    for(int i = 0; i < leftFollowers.length; ++i) {
        leftFollowers[i] = new WPI_TalonFX(Constants.kLeftFollowers[i]);
    }
    //init right followers
    rightFollowers = new WPI_TalonFX[Constants.kRightFollowers.length];
    for(int i = 0; i < rightFollowers.length; ++i) {
        rightFollowers[i] = new WPI_TalonFX(Constants.kRightFollowers[i]);
    }

    hFollowers = new WPI_TalonFX[Constants.kHFollowers.length];
    for(int i = 0; i < hFollowers.length; ++i) {
        hFollowers[i] = new WPI_TalonFX(Constants.kHFollowers[i]);
    }

    leftMotors = new SpeedControllerGroup(leftLeader, leftFollowers);
    rightMotors = new SpeedControllerGroup(rightLeader, rightFollowers);
    hMotors = new SpeedControllerGroup(hLeader, hFollowers);
    differentialDrive = new DifferentialDrive(leftMotors, rightMotors);
  }

  public void arcadeDrive(double moveSpeed, double rotateSpeed) {
    differentialDrive.arcadeDrive(moveSpeed, rotateSpeed);
  }

  public void set(double leftSpeed, double rightSpeed){
    leftMotors.set(leftSpeed);
    rightMotors.set(rightSpeed);
  }

  public void set(double leftSpeed, double rightSpeed, double hSpeed){
    leftMotors.set(leftSpeed);
    rightMotors.set(rightSpeed);
    hMotors.set(hSpeed);
  }

  public void set(double hSpeed){
    hMotors.set(hSpeed);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}