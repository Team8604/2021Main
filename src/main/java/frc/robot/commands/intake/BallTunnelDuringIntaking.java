// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants;

public class BallTunnelDuringIntaking extends CommandBase {
  private Boolean previousSensorState = false;
  private double startingPosition;

  public BallTunnelDuringIntaking() {
    addRequirements(RobotContainer.ballTunnel);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    if (previousSensorState == false && RobotContainer.ballTunnel.readSensorStateLower() == true) {
      // Enter this statement if this is the first moment the ball has broken the sensor sight
      startingPosition = RobotContainer.ballTunnel.getMotorPosition();
      previousSensorState = true;
    }

    else if (previousSensorState && RobotContainer.ballTunnel.readSensorStateLower()) {
      if (RobotContainer.ballTunnel.getMotorPosition() < startingPosition + Constants.kTunnelDistance) {
        RobotContainer.ballTunnel.setSpeed(Constants.kBallTunnelMotorSpeed);
      } else {
        RobotContainer.ballTunnel.setSpeed(0);
        previousSensorState = false;
      }
    } else {
      RobotContainer.ballTunnel.setSpeed(0);
    }
    previousSensorState = RobotContainer.ballTunnel.readSensorStateLower();
  }

  @Override
  public void end(boolean interrupted) {
    RobotContainer.ballTunnel.setSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return false; // timer.hasElapsed(Constants.kBallTunnelDeactivateDelay);
  }
}
