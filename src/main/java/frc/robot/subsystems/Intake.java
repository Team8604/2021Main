// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class Intake extends SubsystemBase {

  private WPI_TalonFX intakeMotor;
  private Solenoid intakeSolenoid;

  public Intake() {
    intakeSolenoid = new Solenoid(Constants.kPCM, Constants.kIntakeSolenoid);
    intakeMotor = new WPI_TalonFX(Constants.kIntakeMotor);
  }

  @Override
  public void periodic() {
  }

  public void setMotor(double speed){
    SmartDashboard.putNumber("intakeMotorStatus", speed);
    intakeMotor.set(speed);
  }

  public void setSolenoid(boolean extended){
    if(Constants.isDebugMode) { 
      SmartDashboard.putBoolean("intakeExtensionStatus", extended); 
    }
    intakeSolenoid.set(extended);
  }

  public boolean getSolenoid(){
    return intakeSolenoid.get();
  }
}
