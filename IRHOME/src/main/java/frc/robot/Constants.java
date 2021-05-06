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
    public static final double deadzoneY = 0.1;
    public static final double deadzoneZ = 0.2;
    //talon IDs
    public static final int frontIntake = 10;
    public static final int rearIntake = 11;
    public static final int mecanumIntake = 12;
    public static final int mecanumBackIntake = 14;
    public static final int climbMotor = 40;
    public static final int balanceMotor = 41;
    public static final int shooterMotor = 60;
    public static final int leftDrive = 1;
    public static final int rightDrive = 2;

//motor Speeds
    public static final double ballIntakeSpeed = 0.75;
    public static final double mecanumIntakeSpeed = 0.75;
    public static final double liftIntakeSpeed = 0.5;
    public static final double upSpeed = 1.0;
    public static final double downSpeed = -1.0;
    public static final double leftSpeed = 1;
    public static final double rightSpeed = -1;
    public static final double shooterSpeed = 0.85;

//Controller IDs
    public static final int oi_Driver = 0;
    public static final int oi_Operator = 2;
//Limelight Constants
    public static final double lHeight = 35.5;
    public static final double tHeight = 90;
    public static final double llAngle = 26.1;

    //z1 firing range
    public static final double Z1Lowest = 75;
    public static final double Z1Farthest = 87;
    //z2 firing range
    public static final double Z2Lowest = 250;
    public static final double Z2Farthest = 260;
}
