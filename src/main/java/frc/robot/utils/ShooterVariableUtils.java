package frc.robot.utils;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

public final class ShooterVariableUtils {
    
    private ShooterVariableUtils() {}//Don't allow people to make instances of this.

    public static final double[][] MAPPING_DIST_RPM = new double[][] {
        { 12, 3000 },
        { 23, 3500 },
        { 24, 2500 },
        { 36, 3000 }//TEMP DATA
    };

    public static final double[][] MAPPING_LLY_DIST = new double[][] {
        { -5, 36 },
        {  0, 24 },
        {  5, 12 }//TEMP DATA
    };

    public static final double HOOD_SWITCH_POS = 24;

    // ASSUMES MAPPING IS SORTED
    public static double MapByMapping(double value, double[][] mapping){

        double[][] values = GetClosestValues(value, mapping);

        if(values[0][0] == values[1][0]){//The inputs are the same, return an average
            return (values[0][1] + values[1][1])/2;
        }

        if(values[0][1] == values[1][1]){//The outputs are the same, return either output
            return values[0][1];
        }

        return lerp(values[0][1], values[1][1], reverseLerp(values[0][0], values[1][0], value));
    }

    private static double lerp(double l, double h, double v){
        return l + v * (h - l);//According to wikipedia, this is slightly impercise because of floating point stuff.
    }

    //Finds how the value is between two points. EX: 30 is 0.25 from 24 to 48
    private static double reverseLerp(double l, double h, double o) {
        return (o - l) / (h - l);//This was me for 5 seconds on a whiteboard, might be wrong.
    }

    // Returns the lower and higher values
    public static double[][] GetClosestValues(double value, double[][] mapping){
        //Basic test cases
        if(mapping.length == 0){
            throw new IllegalArgumentException("Mapping must have some values!");
        }

        if(mapping.length == 1){
            return new double[][] { mapping[0], mapping[0] };
        }

        if(value <= mapping[0][0]){
            return new double[][] { mapping[0], mapping[0] };
        }
        
        int lastElemIdx = mapping.length - 1;
        if(value >= mapping[lastElemIdx][0]){
            return new double[][] { mapping[lastElemIdx], mapping[lastElemIdx] };
        }

        //Its not a trivial case

        boolean found = false;
        int lower = 0;
        int higher = lastElemIdx;
        int idx = mapping.length/2;
        double[][] result = null;
        while(!found){//performs binary search
            double valueAt = mapping[idx][0];
            if(valueAt < value && mapping[idx+1][0] > value) {
                result = new double[][] { mapping[idx], mapping[idx+1] };
                found = true;
            }
            if(valueAt > value){
                higher = idx;
            } else {
                lower = idx;
            }
            idx = (higher + lower) / 2;
        }

        return result;
    }

    public static ShooterConfiguration getConfig(){
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        NetworkTableEntry tx = table.getEntry("tx");//Some of these are unused. Maybe delete them?
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

        if(v == 0){
            return new ShooterConfiguration(false, 3000);//Default
        }

        double distance = MapByMapping(y, MAPPING_LLY_DIST);

        return new ShooterConfiguration(distance < HOOD_SWITCH_POS, MapByMapping(distance, MAPPING_DIST_RPM));
    }

}
