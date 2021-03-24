// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;

import frc.robot.RobotContainer;
import frc.robot.command.IntakeExtensionMotor;

public class BallTunnelDuringIntaking extends CommandBase {

  private Timer timer;

  public BallTunnelDuringIntaking() {
    addRequirements(RobotContainer.ballTunnel);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if(RobotContainer.ballTunnel.readSensor(0) && !RobotContainer.ballTunnel.readSensor(2)){//TODO: Verify sensor placement/usage.
      RobotContainer.ballTunnel.set(Constants.kBallTunnelMotorSpeed);
    } else {
      RobotContainer.ballTunnel.set(0);
    }
    if(!IntakeExtensionMotor.extended) {
      timer.start();
    }
  }

  @Override
  public void end(boolean interrupted) {
    RobotContainer.ballTunnel.set(0);
  }

  @Override
  public boolean isFinished() {
    return timer.hasElasped(Constants.kBallTunnelDeactivateDelay);
  }
}
