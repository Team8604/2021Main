// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants;

public class BallTunnelDuringShooting extends CommandBase {
  /** Creates a new BallTunnelDuringShooting. */
  private boolean done = false;

  public BallTunnelDuringShooting() {
    addRequirements(RobotContainer.ballTunnel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    done = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (RobotContainer.shooter.motorWithinRPM(Constants.kMaxRPMDelta)) {
      RobotContainer.ballTunnel.setSpeed(Constants.kBallTunnelMotorSpeedShoot);
      done = true;
    } else {
      // RobotContainer.ballTunnel.setSpeed(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // RobotContainer.ballTunnel.setSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return done;
  }
}
