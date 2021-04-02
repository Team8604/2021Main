// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ShooterMotor extends CommandBase {

  private double target;

  public ShooterMotor(double target) {
    addRequirements(RobotContainer.shooter);
    this.target = target;
  }

  @Override
  public void initialize() {
    RobotContainer.shooter.setMotorPID(target);
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    RobotContainer.shooter.setMotorRaw(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
