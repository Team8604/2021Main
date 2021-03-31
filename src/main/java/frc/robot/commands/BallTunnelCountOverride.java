// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.RobotContainer;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class BallTunnelCountOverride extends CommandBase {
  
  private int amount;

  public BallTunnelCountOverride(int amount) {
    this.amount = amount;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.ballTunnel.setBallsInTunnel(RobotContainer.ballTunnel.getBallsInTunnel() + amount); 
    if(RobotContainer.ballTunnel.getBallsInTunnel() < 0){
      RobotContainer.ballTunnel.setBallsInTunnel(0);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}