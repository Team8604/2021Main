package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.auto.AutonomousDrive;
import frc.robot.commands.auto.AutonomousRotate;

public class AutonomousPaths {

    public static final Command[] bouncePath = new Command[] {
        new AutonomousDrive(90-(38/2)),
        new AutonomousRotate(-90),
        new AutonomousDrive(35),
        //COLLECT A
        new AutonomousDrive(-30),
        new AutonomousRotate(135),
        new AutonomousDrive(30 * Math.sqrt(2)),
        new AutonomousRotate(45),
        new AutonomousDrive(30),
        new AutonomousRotate(-90),
        new AutonomousDrive(60),
        new AutonomousRotate(-90),
        new AutonomousDrive(100),
        //COLLECT B
        new AutonomousDrive(-100),
        new AutonomousRotate(90),
        new AutonomousDrive(85),
        new AutonomousRotate(-90),
        new AutonomousDrive(95),
        //COLLECT C
        new AutonomousDrive(-45),
        new AutonomousRotate(90),
        new AutonomousDrive(45),
    };

    public static final Command[] slalomPath = new Command[] {
        new AutonomousDrive(57.5-(38/2)),
        new AutonomousRotate(-45),
        new AutonomousDrive(60 * Math.sqrt(2)),
        new AutonomousRotate(45),
        new AutonomousDrive(117.5),
        new AutonomousRotate(45),
        new AutonomousDrive(55 * Math.sqrt(2)),
        new AutonomousRotate(-105),
        new AutonomousDrive(20 * Math.sqrt(3)),
        new AutonomousRotate(-120),
        new AutonomousDrive(20 * Math.sqrt(3)),
        new AutonomousRotate(-105),
        new AutonomousDrive(55 * Math.sqrt(2)),
        new AutonomousRotate(45),
        new AutonomousDrive(115),
        new AutonomousRotate(45),
        new AutonomousDrive(60 * Math.sqrt(2)),
        new AutonomousRotate(-45),
        new AutonomousDrive(45),
    };    

    public static final Command[] testPath = new Command[] {
        new AutonomousDrive(24)
    };

}
