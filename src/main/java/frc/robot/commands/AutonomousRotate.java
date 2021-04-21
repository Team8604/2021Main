package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants;

public class AutonomousRotate extends CommandBase {

  private double rotation;

  public AutonomousRotate(double rotation) {
    addRequirements(RobotContainer.drivetrain);
    this.rotation = rotation;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double actualRotation = Constants.kDegreesToInches * rotation;
    RobotContainer.drivetrain.setLeftPID(actualRotation);
    RobotContainer.drivetrain.setRightPID(-actualRotation);

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
