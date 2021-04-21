package frc.robot;

import frc.robot.commands.AutonomousDrive;
import frc.robot.commands.AutonomousRotate;

import edu.wpi.first.wpilibj2.command.Command;

public class AutonomousPaths {

    public static final Command[] bouncePath = new Command[] {
        new AutonomousDrive(30),
        new AutonomousRotate(-90),
        new AutonomousDrive(60),
        //COLLECT A
        new AutonomousDrive(-60),
        new AutonomousRotate(135),
        new AutonomousDrive(30 * Math.sqrt(2)),
        new AutonomousRotate(45),
        new AutonomousDrive(30),
        new AutonomousRotate(-90),
        new AutonomousDrive(60),
        new AutonomousRotate(-90),
        new AutonomousDrive(30),
        new AutonomousRotate(-90),
        new AutonomousDrive(120),
        //COLLECT B
        new AutonomousDrive(-120),
        new AutonomousRotate(90),
        new AutonomousDrive(90),
        new AutonomousRotate(-90),
        new AutonomousDrive(120),
        //COLLECT C
        new AutonomousDrive(-60),
        new AutonomousRotate(90),
        new AutonomousDrive(60),
    };

}
