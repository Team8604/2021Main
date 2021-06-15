// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants;

public class BallTunnelBeforeShooting extends CommandBase {

  private double startingPosition;
  private boolean done = false;

  public BallTunnelBeforeShooting() {
    addRequirements(RobotContainer.ballTunnel);
  }

  @Override
  public void initialize() {
    startingPosition = RobotContainer.ballTunnel.getMotorPosition();
    done = false;
  }

  @Override
  public void execute() {
    if (RobotContainer.ballTunnel.getMotorPosition() > startingPosition - (Constants.kTunnelDistanceShoot)) {
      RobotContainer.ballTunnel.setSpeed(-Constants.kBallTunnelMotorSpeedSlow);
    } else {
      RobotContainer.ballTunnel.setSpeed(0);
      done = true;
    }
  }

  @Override
  public void end(boolean interrupted) {
    RobotContainer.ballTunnel.setSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return done; // timer.hasElapsed(Constants.kBallTunnelDeactivateDelay);
  }
}
