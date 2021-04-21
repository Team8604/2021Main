// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class DriveStraightTime extends CommandBase {
  private double speed;
  private double time;
  private double startTime;
  private boolean done;

  public DriveStraightTime(double speed, double time) {
    addRequirements(RobotContainer.drivetrain);
    this.speed = speed;
    this.time = time;
  }

  @Override
  public void initialize() {
    startTime = Timer.getFPGATimestamp();
    done = false;
  }

  @Override
  public void execute() {
    System.out.println(Timer.getFPGATimestamp());
    if(Timer.getFPGATimestamp() - startTime < time){
      RobotContainer.drivetrain.arcadeDrive(speed, 0);
    }
    else{
      RobotContainer.drivetrain.arcadeDrive(0, 0);
      System.out.println("DONE");
      done = true;
    }
  }

  @Override
  public void end(boolean interrupted) {
    RobotContainer.drivetrain.arcadeDrive(0, 0);
  }

  @Override
  public boolean isFinished() {
    return done;
  }
}
