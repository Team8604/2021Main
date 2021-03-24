// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Constants;
import frc.robot.commands.IntakeMotor;
import frc.robot.commands.IntakeExtension;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class IntakeExtensionMotor extends SequentialCommandGroup {
  /** Creates a new IntakeExtensionMotor. */
  public IntakeExtensionMotor(boolean extended) {
    SmartDashboard.putBoolean("intakeExtensionMotorStatus", extended);
    isExtended = extended;
    if(isExtended){
      addCommands(new IntakeExtension(extended), new IntakeMotor(extended ? Constants.kIntakeMotorSpeed : 0), new BallTunnelDuringIntaking());
    } else {
      addCommands(new IntakeExtension(extended), new IntakeMotor(extended ? Constants.kIntakeMotorSpeed : 0));
    }
  }

  public static boolean isExtended = false;
}
