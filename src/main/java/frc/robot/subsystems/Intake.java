// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Intake extends SubsystemBase {
  private WPI_TalonFX intakeMotor;
  private DoubleSolenoid intakeSolenoid;
  private CANSparkMax intakeHelper;

  public Intake() {
    intakeSolenoid = new DoubleSolenoid(Constants.kPCM, Constants.kIntakeSolenoidExtend,
        Constants.kIntakeSolenoidRetract);
    intakeMotor = new WPI_TalonFX(Constants.kIntakeMotor);
    intakeMotor.setInverted(Constants.kIntakeMotorInversion);
    intakeHelper = new CANSparkMax(Constants.kIntakeMotorHelper, MotorType.kBrushed);
  }

  @Override
  public void periodic() {
  }

  public void setMotor(double speed) {
    if (Constants.isDebugMode) {
      SmartDashboard.putNumber("intakeMotorStatus", speed);
    }
    intakeMotor.set(speed);
  }

  public void setMotorHelper(double speed) {
    if (Constants.isDebugMode) {
      SmartDashboard.putNumber("intakeMotorHelperStatus", speed);
    }
    intakeHelper.set(speed);
  }

  public void setSolenoid(boolean extended) {
    if (Constants.isDebugMode) {
      SmartDashboard.putBoolean("intakeExtensionStatus", extended);
    }
    if (extended) {
      intakeSolenoid.set(Value.kForward);

    } else {
      intakeSolenoid.set(Value.kReverse);
    }
  }

  public boolean getSolenoid() {
    if (intakeSolenoid.get() == Value.kForward) {
      return true;
    } else {
      return false;
    }
  }
}
