// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;

import frc.robot.RobotContainer;
import frc.robot.Constants;

import frc.robot.commands.IntakeExtensionMotor;

public class BallTunnelDuringIntaking extends CommandBase {

  private Timer timer;

  public BallTunnelDuringIntaking() {
    addRequirements(RobotContainer.ballTunnel);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if(RobotContainer.ballTunnel.readSensorStateLower() && !RobotContainer.ballTunnel.readSensorStateUpper()){//TODO: Verify sensor placement/usage.
      RobotContainer.ballTunnel.setSpeed(Constants.kBallTunnelMotorSpeed);
    } else {
      RobotContainer.ballTunnel.setSpeed(0);
    }
    if(!IntakeExtensionMotor.isExtended) {
      timer.start();
    }
  }

  @Override
  public void end(boolean interrupted) {
    RobotContainer.ballTunnel.setSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return timer.hasElapsed(Constants.kBallTunnelDeactivateDelay);
  }
}
