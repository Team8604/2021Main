// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class StopDrivetrain extends CommandBase {

  public StopDrivetrain() {
    addRequirements(RobotContainer.drivetrain);
  }

  @Override
  public void initialize() {
    RobotContainer.drivetrain.arcadeDrive(0,0);
  }

  @Override
  public void execute() {
    RobotContainer.drivetrain.arcadeDrive(0,0);
  }

  @Override
  public void end(boolean interrupted) {
    RobotContainer.drivetrain.arcadeDrive(0, 0);
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
