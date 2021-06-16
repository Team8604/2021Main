// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.shooter.distances;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.shooter.BallTunnelBeforeShooting;
import frc.robot.commands.shooter.BallTunnelDuringShooting;
import frc.robot.commands.shooter.LimeLightTurn;
import frc.robot.commands.shooter.ShooterHood;
import frc.robot.commands.shooter.ShooterMotor;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShootLong extends SequentialCommandGroup {
  /** Creates a new ShootLong. */
  public ShootLong() {
    addCommands(new BallTunnelBeforeShooting(), new ShooterHood(false), new ShooterMotor(Shooter.testingFlywheelSpeed), new LimeLightTurn(), new BallTunnelDuringShooting());
  }
}
