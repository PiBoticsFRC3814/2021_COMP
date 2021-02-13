// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class GyroReset extends CommandBase {
  /** Creates a new GyroReset. */
  ADIS16448_IMU gyro;
  public GyroReset(ADIS16448_IMU gyroscope) {
    // Use addRequirements() here to declare subsystem dependencies.
    gyro = gyroscope;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    gyro.reset();
    DriverStation.reportWarning("Gyro has been reset. Initiaing all else fails protocol", false);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    SmartDashboard.putBoolean("GyroReset", false);
    return true;
  }
}
