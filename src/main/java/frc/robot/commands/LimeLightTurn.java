// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class LimeLightTurn extends CommandBase {
  /** Creates a new LimelightTurn. */
  public LimeLightTurn() {
    addRequirements(RobotContainer.drivetrain);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    NetworkTableEntry tv = table.getEntry("tv");

    double x = tx.getDouble(0.0);
    double y = ty.getDouble(0.0);
    double area = ta.getDouble(0.0);
    double v = tv.getDouble(0.0);

    if(Constants.isDebugMode){
      SmartDashboard.putNumber("LimelightX", x);
      SmartDashboard.putNumber("LimelightV", v);
      SmartDashboard.putNumber("LimelightY", y);
      SmartDashboard.putNumber("LimelightArea", area);
    }

    if(v != 0.0){
      double turnAmount = Constants.kLimeLightTurn_kP * x;
      if(turnAmount < Constants.kMinRotateSpeed){
        turnAmount = Constants.kMinRotateSpeed;
      }
      RobotContainer.drivetrain.arcadeDrive(0, turnAmount);
      if(x > -1 && x < 1){
        counter++;
      } else{
        counter = 0;
      }
    } else {
      counter = Constants.kRequiredLimeLightGoodCycles;//Dirty little hack to immediately finish.
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return counter >= Constants.kRequiredLimeLightGoodCycles;
  }

  private int counter = 0;
}
