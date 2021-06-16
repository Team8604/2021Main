package frc.robot.utils;

//Essentially a struct
public class ShooterConfiguration {//Stuff like this makes you wish you had proper structs
    
    public final boolean hoodExtended;
    public final double flywheelSpeed;

    public ShooterConfiguration(boolean hoodExtended, double flywheelSpeed){
        this.hoodExtended = hoodExtended;
        this.flywheelSpeed = flywheelSpeed;
    }

}
