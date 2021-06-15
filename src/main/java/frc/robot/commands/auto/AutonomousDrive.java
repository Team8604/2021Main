package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomousDrive extends CommandBase {

  private double distance;
  private double rightDistance;
  private double leftDistance;;

  public AutonomousDrive(double distance) {
    addRequirements(RobotContainer.drivetrain);
    this.distance = distance * Constants.kInchesToTicks;
  }

  @Override
  public void initialize() {
    RobotContainer.drivetrain.setSafetyEnabled(false);
    RobotContainer.drivetrain.setMotorPower(Constants.kDrivePowerRatioDuringAuto);
    leftDistance = distance + RobotContainer.drivetrain.getLeftPIDPosition();
    rightDistance = -distance + RobotContainer.drivetrain.getRightPIDPosition();
    
  }

  @Override
  public void execute() {
    RobotContainer.drivetrain.setLeftPID(leftDistance);
    RobotContainer.drivetrain.setRightPID(rightDistance);
     if(Constants.isDebugMode){
       SmartDashboard.putNumber("autoDriveRightTarget", rightDistance);
       SmartDashboard.putNumber("autoDriveLeftTarget", leftDistance);
       SmartDashboard.putNumber("autoDriveLeftError", RobotContainer.drivetrain.getLeftPIDError(leftDistance));
       SmartDashboard.putNumber("autoDriveRightError", RobotContainer.drivetrain.getRightPIDError(rightDistance));
       SmartDashboard.putNumber("autoDriveLeftPosition", RobotContainer.drivetrain.getLeftPIDPosition());
       SmartDashboard.putNumber("autoDriveRightPosition", RobotContainer.drivetrain.getRightPIDPosition());
     }
     if(Math.abs(RobotContainer.drivetrain.getLeftPIDError(leftDistance)) < Constants.kMaxInchesDelta * Constants.kInchesToTicks && Math.abs(RobotContainer.drivetrain.getRightPIDError(rightDistance)) < Constants.kMaxInchesDelta * Constants.kInchesToTicks){
      counter++;
    } else {
       counter = 0;
    }
    
  }

  @Override
  public void end(boolean interrupted) {
    RobotContainer.drivetrain.setMotorPower(1);
    RobotContainer.drivetrain.setSafetyEnabled(true);
  }

  @Override
  public boolean isFinished() {
    return counter >= Constants.kRequiredPIDGoodCycles;
  }

  private int counter = 0;
}
