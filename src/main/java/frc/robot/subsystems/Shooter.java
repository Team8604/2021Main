// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
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
    }
    master.set(percentOutput);
  }

  private boolean shooterHoodExtended;
}
