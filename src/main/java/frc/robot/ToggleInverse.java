package frc.robot;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class ToggleInverse extends CommandBase {
  public ToggleInverse() {
  }

  @Override
  public void initialize() {
    RobotContainer.drivetrain.inverse = !RobotContainer.drivetrain.inverse;
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }
}
