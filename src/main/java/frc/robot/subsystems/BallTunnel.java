// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import frc.robot.Constants;

public class BallTunnel extends SubsystemBase {

  private WPI_TalonFX ballMotor;

  public BallTunnel() {
    ballMotor = new WPI_TalonFX(Constants.kBallMotor);
  }

  @Override
  public void periodic() {
  }

  public void setSpeed(double speed){
    SmartDashboard.putNumber("ballTunnelMotorStatus", speed);
    ballMotor.set(speed);
  }
}
