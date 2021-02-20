// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //drive paramters
    public static final double deadzoneX = 0.1;
    public static final double deadzoneY = 0.1;
    public static final double deadzoneZ = 0.1;
    //talon IDs
    public static final int frontIntake = 10;
    public static final int rearIntake = 11;
    public static final int mecanumIntake = 12;
    public static final int mecanumBackIntake = 14;
    public static final int liftIntake = 13;
    public static final int cMotor = 40;
    public static final int bMotor = 41;
    public static final int blMotor = 50;
    public static final int ControlPanelMotor = 30;
    public static final int shooterMotor = 60;
    //mecanum drive(lf, lr, rf, rr)
    public static final int lf = 1;
    public static final int lr = 2;
    public static final int rf = 3;
    public static final int rr = 4;
//Digital Input IDs
    public static final int lowerInput = 1;
    public static final int upperInput = 2;

//pneumatic IDs
    public static final int PCM1 = 0;
    public static final int frontExtend = 0;
    public static final int frontRetract = 1;
    public static final int rearExtend = 2;
    public static final int rearRetract = 3;

//motor Speeds
    public static final double ballIntakeSpeed = 0.75;
    public static final double mecanumIntakeSpeed = 0.75;
    public static final double liftIntakeSpeed = 0.5;
    public static final double upSpeed = 1.0;
    public static final double downSpeed = -1.0;
    public static final double leftSpeed = 1;
    public static final double rightSpeed = -1;
    public static final double gateSpeed = 0.1;
    public static final double gateReverse = -0.1;
    public static final double shooterSpeed = 0.85;

//Controller IDs
    public static final int oi_Driver = 0;
    public static final int oi_Operator = 2;
//Limelight Constants
    public static final double lHeight = 35.5;
    public static final double tHeight = 90;
    public static final double llAngle = 18;

    //z1 firing range
    public static final double Z1Lowest = 68;
    public static final double Z1Farthest = 80;
    public static final double MotorSpeed1 = 1.0;
    //z2 firing range
    public static final double Z2Lowest = 124;
    public static final double Z2Farthest = 136;
    public static final double MotorSpeed2 = 0.6;
    //z3 firing range
    public static final double Z3Lowest = 182;
    public static final double Z3Farthest = 1194;
    public static final double MotorSpeed3 = 0.69;
    //z4 firing range
    public static final double Z4Lowest = 269;
    public static final double Z4Farthest = 281;
    public static final double MotorSpeed4 = 1.0;
}
