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

public class BallTunnel extends SubsystemBase {
  private WPI_TalonFX ballMotor;
  private DigitalInput sensor0;
  private DigitalInput sensor1;
  private int bottomCounter0 = 0;
  private int bottomCounter1 = 0;

  public BallTunnel() {
    ballMotor = new WPI_TalonFX(Constants.kBallMotor);
    ballMotor.setInverted(Constants.kBallTunnelInversion);
    ballMotor.setNeutralMode(NeutralMode.Brake);
    ballMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, Constants.kPIDLoopIdx,
        Constants.kTimeoutsMs);
    sensor0 = new DigitalInput(Constants.kBallSensorLowerLeft);
    sensor1 = new DigitalInput(Constants.kBallSensorLowerRight);
  }

  @Override
  public void periodic() {
    if (!sensor0.get()) {
      bottomCounter0++;
    } else {
      bottomCounter0 = 0;
    }
    if (!sensor1.get()) {
      bottomCounter1++;
    } else {
      bottomCounter1 = 0;
    }
  }

  public void setSpeed(double speed) {
    if (Constants.isDebugMode) {
      SmartDashboard.putNumber("ballTunnelMotorStatus", speed);
    }
    ballMotor.set(speed);
  }

  public double getMotorPosition() {
    return ballMotor.getSelectedSensorPosition();
  }

  public boolean readSensorStateLower() {
    boolean bottom0 = false;
    boolean bottom1 = false;
    if (bottomCounter0 > Constants.kSensorDebounce) {
      bottom0 = true;
    } else {
      bottom0 = false;
    }

    if (bottomCounter1 > Constants.kSensorDebounce) {
      bottom1 = true;
    } else {
      bottom1 = false;
    }
    return bottom0 || bottom1;
  }
}
