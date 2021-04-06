package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants;

public class AutonomousDrive extends CommandBase {

  private double distance;

  public AutonomousDrive(double distance) {
    addRequirements(RobotContainer.drivetrain);
    this.distance = distance;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    RobotContainer.drivetrain.setLeftPID(distance);
    RobotContainer.drivetrain.setRightPID(distance);

    if(Math.abs(RobotContainer.drivetrain.getLeftPIDError()) < Constants.kMaxInchesDelta && Math.abs(RobotContainer.drivetrain.getRightPIDError()) < Constants.kMaxInchesDelta){
      counter++;
    } else {
      counter = 0;
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return counter >= Constants.kRequiredPIDGoodCycles;
  }

  private int counter = 0;
}
