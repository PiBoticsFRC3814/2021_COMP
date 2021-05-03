// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  /** Creates a new DriveTrain. */

  public static final WPI_TalonSRX leftDrive = new WPI_TalonSRX(Constants.leftDrive);
  public static final WPI_TalonSRX rightDrive = new WPI_TalonSRX(Constants.rightDrive);
  public static final DifferentialDrive piboticsdrive = new DifferentialDrive(leftDrive, rightDrive);
  
  public DriveTrain() {}

  private double ApplyDeadband(double value, double deadband)
  {
    if (Math.abs(value) > deadband) {
      if (value > 0.0) {
        return (value - deadband) / (1.0 - deadband);
      } else {
        return (value + deadband) / (1.0 - deadband);
      }
    } else {
      return 0.0;
    }
  }

  public void Drive(double y, double z, boolean stick, double yDeadband, double zDeadband)
  {
    piboticsdrive.arcadeDrive(ApplyDeadband(y, yDeadband), ApplyDeadband(z, zDeadband), stick);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
