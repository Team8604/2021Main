// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ShooterMotorRaw extends CommandBase {
  private double speed;

  public ShooterMotorRaw(double speed) {
    addRequirements(RobotContainer.shooter);
    this.speed = speed;
  }

  @Override
  public void initialize() {
    RobotContainer.shooter.setMotorRaw(speed);
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
