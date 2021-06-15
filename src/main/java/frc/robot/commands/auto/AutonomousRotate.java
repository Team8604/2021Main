package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
      double delta = clampTo360(startRotation + rotation) - RobotContainer.drivetrain.getGyroAngle();
      //MOST EFFICIENT PATH
      if(delta > 180){
        delta -= 360;
      }
      if(delta < -180){
        delta += 360;
      }
      if(Constants.isDebugMode){
        SmartDashboard.putNumber("autoTurnDelta", delta);
      }
      //CLAMP SPEED
      if(delta > 30){
        delta = 30;
      } 
      if(delta < -30){
       delta = -30;
      }    
      double turnAmount = Constants.kAutoTurn_kP * delta;
      if(Math.abs(turnAmount) < Constants.kMinRotateSpeed){
        turnAmount = Math.copySign(Constants.kMinRotateSpeed, turnAmount);
      }
      RobotContainer.drivetrain.arcadeDrive(0, turnAmount);
      double clampedDelta = clampTo360(delta);
      if(clampedDelta < Constants.kMaxAutoTurnDelta || clampedDelta > 360 - Constants.kMaxAutoTurnDelta){
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

  private double clampTo360(double value){
    return value-Math.floor(value/360)*360;
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
