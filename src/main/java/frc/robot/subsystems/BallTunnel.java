// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import frc.robot.Constants;
import frc.robot.RobotContainer;


public class BallTunnel extends SubsystemBase {

  private WPI_TalonFX ballMotor;
  // private DigitalInput[] sensors;

  // private boolean lastSensorStateLower;
  // private boolean lastSensorStateUpper;

  private DigitalInput sensor0;
  private DigitalInput sensor1;

  private int bottomCounter0 = 0;
  private int bottomCounter1 = 0;

  // private int ballsInTunnel = 0;

  public BallTunnel() {
    ballMotor = new WPI_TalonFX(Constants.kBallMotor);
    ballMotor.setInverted(Constants.kBallTunnelInversion);
    ballMotor.setNeutralMode(NeutralMode.Brake);
    ballMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, Constants.kPIDLoopIdx, Constants.kTimeoutsMs);
    /*
    sensors = new DigitalInput[]{
      new DigitalInput(Constants.kBallSensorIDBase+0),
      new DigitalInput(Constants.kBallSensorIDBase+1),
      new DigitalInput(Constants.kBallSensorIDBase+2),
      new DigitalInput(Constants.kBallSensorIDBase+3;
    };
    */
    sensor0 = new DigitalInput(Constants.kBallSensorLowerLeft);
    sensor1 = new DigitalInput(Constants.kBallSensorLowerRight);
  }

  @Override
  public void periodic() {
    if(!sensor0.get()){
      bottomCounter0++;
    }
    else{
      bottomCounter0 = 0;
    }

    if(!sensor1.get()){
      bottomCounter1++;
    }
    else{
      bottomCounter1 = 0;
    }
    /*
    if(!readSensorStateLower() && lastSensorStateLower){
      setBallsInTunnel(ballsInTunnel+1);
    }
    lastSensorStateLower = readSensorStateLower();
    if(!readSensorStateUpper() && lastSensorStateUpper){
      setBallsInTunnel(ballsInTunnel-1);
    }
    lastSensorStateUpper = readSensorStateUpper();
    */
  }

  public void setSpeed(double speed){
    if(Constants.isDebugMode){
      SmartDashboard.putNumber("ballTunnelMotorStatus", speed);
    }
    ballMotor.set(speed);
  }
  public double getMotorPosition(){
    return ballMotor.getSelectedSensorPosition();
  }
  public boolean readSensorStateLower(){
    boolean bottom0 = false;
    boolean bottom1 = false;
    if(bottomCounter0 > Constants.kSensorDebounce){
      bottom0 = true;
    }
    else{
      bottom0 = false;
    }

    if(bottomCounter1 > Constants.kSensorDebounce){
      bottom1 = true;
    }
    else{
      bottom1 = false;
    }
    return bottom0 || bottom1;
  }
  public boolean readSensorStateUpper(){
    return false;
  }
  /*public boolean readSensorState(int sensor){//0 and 1 for bottom, 2 and 3 for top
    return sensors[sensor].get();
  }

  public boolean readSensorStateLower(){
    return readSensorState(0) || readSensorState(1);
  }
  public boolean readSensorStateUpper(){
    return readSensorState(2) || readSensorState(3);
  }
*/
  /*public void setBallsInTunnel(int value){
    if(Constants.isDebugMode){
      SmartDashboard.putNumber("ballsInTunnel", value);
    }
    ballsInTunnel = value;
  }

  public int getBallsInTunnel(){
    return ballsInTunnel;
  }
*/
}
