// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import frc.robot.Constants;
import frc.robot.RobotContainer;


public class BallTunnel extends SubsystemBase {

  private WPI_TalonFX ballMotor;
  private DigitalInput[] sensors;

  private int ballsInTunnel = 0;

  public BallTunnel() {
    ballMotor = new WPI_TalonFX(Constants.kBallMotor);
    sensors = new DigitalInput[]{
      new DigitalInput(Constants.kBallSensorIDBase+0),
      new DigitalInput(Constants.kBallSensorIDBase+1),
      new DigitalInput(Constants.kBallSensorIDBase+2),
      new DigitalInput(Constants.kBallSensorIDBase+3)
    };
  }

  @Override
  public void periodic() {
    if(!readSensorStateLower() && lastSensorState){
      setBallsInTunnel(ballsInTunnel+1);
    }
    lastSensorState = readSensorStateLower();
  }

  public void setSpeed(double speed){
    if(Constants.isDebugMode){
      SmartDashboard.putNumber("ballTunnelMotorStatus", speed);
    }
    ballMotor.set(speed);
  }

  public boolean readSensorState(int sensor){//0 and 1 for bottom, 2 and 3 for top
    return sensors[sensor].get();
  }

  public boolean readSensorStateLower(){
    return readSensorState(0) || readSensorState(1);
  }
  public boolean readSensorStateUpper(){
    return readSensorState(2) || readSensorState(3);
  }

  private boolean lastSensorState;

  public void setBallsInTunnel(int value){
    if(Constants.isDebugMode){
      SmartDashboard.putNumber("ballsInTunnel", value);
    }
    ballsInTunnel = value;
  }

  public int getBallsInTunnel(){
    return ballsInTunnel;
  }

}
