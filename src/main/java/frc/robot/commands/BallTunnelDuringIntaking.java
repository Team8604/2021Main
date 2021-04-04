// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;

import frc.robot.RobotContainer;
import frc.robot.Constants;

import frc.robot.commands.IntakeExtensionMotor;

public class BallTunnelDuringIntaking extends CommandBase {

  private Timer timer;
  private Boolean previousSensorState = false;
  private double startingPosition;

  public BallTunnelDuringIntaking() {
    addRequirements(RobotContainer.ballTunnel);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if(previousSensorState==false && RobotContainer.ballTunnel.readSensorStateLower() == true){ //Enter this statement if this is the first moment the ball has broken the sensor sight
      startingPosition = RobotContainer.ballTunnel.getMotorPosition();
    }

    if(RobotContainer.ballTunnel.readSensorStateLower() && !RobotContainer.ballTunnel.readSensorStateUpper()){
      if(RobotContainer.ballTunnel.getMotorPosition() < startingPosition+Constants.kTunnelDistance){
        RobotContainer.ballTunnel.setSpeed(Constants.kBallTunnelMotorSpeed);
      }
      else{
        RobotContainer.ballTunnel.setSpeed(0);
      }
    } 
    else {
      RobotContainer.ballTunnel.setSpeed(0);
    }
    previousSensorState = RobotContainer.ballTunnel.readSensorStateLower();
    //if(RobotContainer.intake.getSolenoid()) {
    //  timer.start();
    //}
  }

  @Override
  public void end(boolean interrupted) {
    RobotContainer.ballTunnel.setSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return false; //timer.hasElapsed(Constants.kBallTunnelDeactivateDelay);
  }
}
