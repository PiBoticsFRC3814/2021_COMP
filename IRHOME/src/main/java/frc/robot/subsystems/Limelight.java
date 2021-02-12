// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.lang.Math;

public class Limelight extends SubsystemBase {
  /**
   * Creates a new Limelight.
   */
  public double yaw, z, target;
  public boolean position = false;
  public boolean light = false;
  public double cameraAngle = 0;
  public static double distanceShort = 0;
  public static double distanceLong = 0;
  public static int closest = 0;


  public Limelight() {
  }

  public int closest(double gyro) {
    // 1 = short, 2 = long, 0 = no data xv
    /*getData(gyro);
    distanceShort = Math.abs(Constants.shortFarthest - z);
    distanceLong = Math.abs(Constants.farLowest - z);

    if (distanceLong > distanceShort)
    {
      closest = 1;
    }
    else if (distanceShort > distanceLong)
    {
      closest = 2;
    }
    else
    {
      closest = 0;
    }*/
    return closest;
  }

  public void getData(double gyroAngle) {
    cameraAngle = Constants.llAngle;
    z = (Constants.tHeight-Constants.lHeight)/(Math.tan(Math.toRadians(cameraAngle+NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0.0))));
    yaw = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0.0);
    target = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0.0);
  }

  public boolean isValidTarget() {
    if (target == 0.0)
    {
       return false;
    }
   else
    {
      return true;
    }
  }

  public void offLight() {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
    light = false;
  }

  public void onLight() {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
    light = true;

  }

  public boolean isInPosition() {
    return position;
  }

  public void displayOutput(double gyro) {
    getData(gyro);
    SmartDashboard.putNumber("Distance", z);
    SmartDashboard.putNumber("Yaw", yaw);
    SmartDashboard.putNumber("Gyro Angle", gyro);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
