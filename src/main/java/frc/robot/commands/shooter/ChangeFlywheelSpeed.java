package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class ChangeFlywheelSpeed extends CommandBase {

  public ChangeFlywheelSpeed(int val) {
    Shooter.testingFlywheelSpeed += val;//I know this is awful but this is literally testing code.
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }
}