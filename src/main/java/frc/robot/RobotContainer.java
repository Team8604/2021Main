// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

//Imports
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static Joystick driverController = new Joystick(0);
  public static JoystickButton driverAButton = new JoystickButton(driverController, Constants.kButtonA);
  public static JoystickButton driverBButton = new JoystickButton(driverController, Constants.kButtonB);
  public static JoystickButton driverXButton = new JoystickButton(driverController, Constants.kButtonX);
  public static JoystickButton driverYButton = new JoystickButton(driverController, Constants.kButtonY);
  public static JoystickButton driverRBumper = new JoystickButton(driverController, Constants.kBumperR);
  public static JoystickButton driverLBumper = new JoystickButton(driverController, Constants.kBumperL);

  public static Joystick buttonBoardController = new Joystick(0);
  public static JoystickButton buttonBoardAButton = new JoystickButton(buttonBoardController, Constants.kButtonA);
  public static JoystickButton buttonBoardBButton = new JoystickButton(buttonBoardController, Constants.kButtonB);
  public static JoystickButton buttonBoardXButton = new JoystickButton(buttonBoardController, Constants.kButtonX);
  public static JoystickButton buttonBoardYButton = new JoystickButton(buttonBoardController, Constants.kButtonY);
  public static JoystickButton buttonBoardRBumper = new JoystickButton(buttonBoardController, Constants.kBumperR);
  public static JoystickButton buttonBoardLBumper = new JoystickButton(buttonBoardController, Constants.kBumperL);

  public static Compressor compressor = new Compressor();
  public static HDrive drivetrain = new HDrive();
  public static Intake intake = new Intake();
  public static BallTunnel ballTunnel = new BallTunnel();
  public static Shooter shooter = new Shooter();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    compressor.start();
    configureButtonBindings();
    drivetrain.setDefaultCommand(new DriveArcadeH());
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    driverAButton.whenPressed(new IntakeExtensionMotor(true));
    driverAButton.whenReleased(new IntakeExtensionMotor(false));
    driverLBumper.whenPressed(new BallTunnelMotor(-Constants.kBallTunnelMotorSpeed));
    driverLBumper.whenReleased(new BallTunnelMotor(0));
    driverRBumper.whenPressed(new BallTunnelMotor(Constants.kBallTunnelMotorSpeed));
    driverRBumper.whenReleased(new BallTunnelMotor(0));

    buttonBoardAButton.whenPressed(new IntakeExtensionMotor(true));
    buttonBoardAButton.whenReleased(new IntakeExtensionMotor(false));
    buttonBoardLBumper.whenPressed(new BallTunnelMotor(-Constants.kBallTunnelMotorSpeed));
    buttonBoardLBumper.whenReleased(new BallTunnelMotor(0));
    buttonBoardRBumper.whenPressed(new BallTunnelMotor(Constants.kBallTunnelMotorSpeed));
    buttonBoardRBumper.whenReleased(new BallTunnelMotor(0));
    //Normal Tele
    driverXButton.whenPressed(new ShootShort());
    driverXButton.whenReleased(new ShooterMotorRaw(0));
    driverXButton.whenReleased(new BallTunnelMotor(0));
    driverYButton.whenPressed(new ShootLong());
    driverYButton.whenReleased(new ShooterMotorRaw(0));
    driverYButton.whenReleased(new BallTunnelMotor(0));

    buttonBoardXButton.whenPressed(new ShootShort());
    buttonBoardXButton.whenReleased(new ShooterMotorRaw(0));
    buttonBoardXButton.whenReleased(new BallTunnelMotor(0));
    buttonBoardYButton.whenPressed(new ShootLong());
    buttonBoardYButton.whenReleased(new ShooterMotorRaw(0));
    buttonBoardYButton.whenReleased(new BallTunnelMotor(0));

    // driverBButton.whenPressed(new DriveStraightTime(.75, .95));
    // driverBButton.whenReleased(new StopDrivetrain());
    // driverXButton.whenPressed(new DriveStraightTime(-.75, .95));
    // driverXButton.whenReleased(new StopDrivetrain());
    
    //Interstellar Accuracy
    
    /*
    driverXButton.whenPressed(new ShootGreen());
    driverXButton.whenReleased(new ShooterMotorRaw(0));
    driverXButton.whenReleased(new BallTunnelMotor(0));
    driverYButton.whenPressed(new ShootYellow());
    driverYButton.whenReleased(new ShooterMotorRaw(0));
    driverYButton.whenReleased(new BallTunnelMotor(0));
    driverBButton.whenPressed(new ShootBlue());
    driverBButton.whenReleased(new ShooterMotorRaw(0));
    driverBButton.whenReleased(new BallTunnelMotor(0));
    driverRBumper.whenPressed(new ShootRed());
    driverRBumper.whenReleased(new ShooterMotorRaw(0));
    driverRBumper.whenReleased(new BallTunnelMotor(0));
    */
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return new AutonomousRoutine(AutonomousPaths.slalomPath);
  }
}
