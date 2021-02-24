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
  public double x, y, target;
  public boolean position1 = false;
  public boolean position2 = false;
  public boolean position3 = false;
  public boolean position4 = false;
  public boolean light = false;
  public double cameraAngle = 0;
  public static double distanceShort = 0;
  public static double distanceLong = 0;
  public static int closest = 0;


  public Limelight() {
  }

  public int closest() {
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

  public void getData() {
    cameraAngle = Constants.llAngle;
    y = (Constants.tHeight-Constants.lHeight)/(Math.tan(Math.toRadians(cameraAngle+NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0.0))));
    x = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0.0);
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

  /*public boolean isInPosition() {
    return position;
  }*/

  public void displayOutput() {
    getData();
    SmartDashboard.putNumber("Distance", y);
    SmartDashboard.putNumber("X Distance", x);
    SmartDashboard.putBoolean("Position Green", position1);
    SmartDashboard.putBoolean("Position Yellow", position2);
    SmartDashboard.putBoolean("Position Blue", position3);
    SmartDashboard.putBoolean("Position Red", position4);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
