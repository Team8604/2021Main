// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.InvertType;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {

  private WPI_TalonFX master;
  private WPI_TalonFX slave;

  private Solenoid hoodPiston;

  public Shooter() {
    master = new WPI_TalonFX(Constants.kShooterMotorMaster);
    slave = new WPI_TalonFX(Constants.kShooterMotorSlave);
    slave.follow(master);
    slave.setInverted(InvertType.OpposeMaster);
    hoodPiston = new Solenoid(Constants.kPCM, Constants.kShooterSolenoid);

    //CONFIGURE PID
    master.configureFactoryDefault();
    master.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, Constants.kPIDLoopIdx, Constants.kTimeoutsMs);
    master.config_kF(Constants.kPIDLoopIdx, Constants.kGains_Velocity_kF, Constants.kTimeoutsMs);
    master.config_kP(Constants.kPIDLoopIdx, Constants.kGains_Velocity_kP, Constants.kTimeoutsMs);
    master.config_kI(Constants.kPIDLoopIdx, Constants.kGains_Velocity_kI, Constants.kTimeoutsMs);
    master.config_kD(Constants.kPIDLoopIdx, Constants.kGains_Velocity_kD, Constants.kTimeoutsMs);
  }

  @Override
  public void periodic() {
  }

  public void setShooterHood(boolean extended){
    if(shooterHoodExtended != extended) {
      shooterHoodExtended = extended;
      if(Constants.isDebugMode){
        SmartDashboard.putBoolean("shooterHoodState", extended);
      }
      hoodPiston.set(extended);
    }
  }

  public void setMotorRaw(double percentOutput){
    if(Constants.isDebugMode){
      SmartDashboard.putNumber("shooterMotor", percentOutput);
      SmartDashboard.putString("shooterMotorMode", "raw");
    }
    master.set(TalonFXControlMode.Velocity, percentOutput);
  }

  public void setMotorPID(double speedRPM){
    if(Constants.isDebugMode){
      SmartDashboard.putNumber("shooterMotor", speedRPM);
      SmartDashboard.putString("shooterMotorMode", "PID");
    }
    master.set(TalonFXControlMode.Velocity, speedRPM * Constants.kRPM2Ticks);
  }

  private boolean shooterHoodExtended;
}
