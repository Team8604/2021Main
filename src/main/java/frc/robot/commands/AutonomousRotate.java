package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class AutonomousRotate extends CommandBase {

  private double rotation;
  private double startRotation;

  public AutonomousRotate(double rotation) {
    addRequirements(RobotContainer.drivetrain);
    this.rotation = rotation;
  }

  @Override
  public void initialize() {
    if(!RobotContainer.drivetrain.gyroDisabled){
      startRotation = RobotContainer.drivetrain.getGyroAngle();//NORMAL
    } else {
      //RobotContainer.drivetrain.setMotorPower(kDrivePowerRatioDuringAuto);//FALLBACK
    }
  }

  @Override
  public void execute() {
    if(!RobotContainer.drivetrain.gyroDisabled){
      double delta = (startRotation + rotation) % 360 - RobotContainer.drivetrain.getGyroAngle();
      if(delta > 30) delta = 30;
      if(delta < -30) delta = -30;
      double turnAmount = Constants.kLimeLightTurn_kP * delta;
      if(Math.abs(turnAmount) < Constants.kMinRotateSpeed){
        turnAmount = Math.copySign(Constants.kMinRotateSpeed, turnAmount);
      }
      RobotContainer.drivetrain.arcadeDrive(0, turnAmount);
      if((delta) % 360 > -1 && x < (delta) % 360){
         counter++;
       } else{
         counter = 0;
       }
    } 
    else
    {
      //double actualRotation = Constants.kDegreesToInches * rotation;//FALLBACK
      //RobotContainer.drivetrain.setLeftPID(actualRotation);
      //RobotContainer.drivetrain.setRightPID(-actualRotation);

      //if(Math.abs(RobotContainer.drivetrain.getLeftPIDError()) < Constants.kMaxInchesDelta && Math.abs(RobotContainer.drivetrain.getRightPIDError()) < Constants.kMaxInchesDelta){
      //  counter++;
      //} else {
      //  counter = 0;
      //}
    }
  }

  @Override
  public void end(boolean interrupted) {
    // if(RobotContainer.drivetrain.gyroDisabled){
    //   RobotContainer.drivetrain.setMotorPower(1);//FALLBACK
    // }
  }

  @Override
  public boolean isFinished() {
    return counter >= Constants.kRequiredPIDGoodCycles;
  }

  private int counter = 0;
}
