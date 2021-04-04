// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants;

public class DriveArcadeH extends CommandBase {
  /** Creates a new DriveArcade. */
  public DriveArcadeH() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  double forwardSpeed = RobotContainer.driverController.getRawAxis(Constants.kBBRTrigger);
	double reverseSpeed = RobotContainer.driverController.getRawAxis(Constants.kBBLTrigger);
	double moveSpeed = forwardSpeed - reverseSpeed;
	double rotateSpeed = RobotContainer.driverController.getRawAxis(Constants.kLeftStickX);
  RobotContainer.drivetrain.arcadeDrive(moveSpeed, rotateSpeed);
  double hSpeed = RobotContainer.driverController.getRawAxis(Constants.kRightStickX);
  RobotContainer.drivetrain.set(hSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.drivetrain.set(0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
