// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.RecordJoystick;

import java.io.IOException;
import java.sql.Driver;
import java.util.function.DoubleSupplier;

public class PiboticsDrive extends CommandBase {
  /**
   * Creates a new PiboticsDrive.
   */
  private final DriveTrain m_drivetrain;
  private final RecordJoystick m_recordjoystick;
  private final DoubleSupplier m_x;
  private final DoubleSupplier m_y;
  private final DoubleSupplier m_z;
  private final DoubleSupplier m_gyro;

  public PiboticsDrive(DoubleSupplier y, DoubleSupplier x, DoubleSupplier z, DoubleSupplier gyro,
      DriveTrain piboticsdrive, RecordJoystick recordStick) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrain = piboticsdrive;
    m_recordjoystick = recordStick;
    m_x = x;
    m_y = y;
    m_z = z;
    m_gyro = gyro;
    addRequirements(m_drivetrain);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double x, y, z;
    if (m_x.getAsDouble() > Constants.deadzoneX || m_x.getAsDouble() < Constants.deadzoneX) {
      x = m_x.getAsDouble();

      if (x < 0.0) {
        x = (x + Constants.deadzoneX) * 1.1111;
      }
      if (x > 0.0) {
        x = (x - Constants.deadzoneX) * 1.1111;
      }

    } else
      x = 0.0;

    if (m_y.getAsDouble() > Constants.deadzoneY || m_y.getAsDouble() < Constants.deadzoneY) {
      y = m_y.getAsDouble();

      if (y < 0.0) {
        y = (y + Constants.deadzoneY) * 1.1111;
      }
      if (y > 0.0) {
        y = (y - Constants.deadzoneY) * 1.1111;
      }

    } else
      y = 0.0;

    if (m_z.getAsDouble() > Constants.deadzoneZ || m_z.getAsDouble() < Constants.deadzoneZ) {
      z = m_z.getAsDouble();

      if (z < 0.0) {
        z = (z + Constants.deadzoneZ) * 0.7;
      }
      if (z > 0.0) {
        z = (z - Constants.deadzoneZ) * 0.7;
      }

    } else
      z = 0.0;

    m_drivetrain.Drive(-y, -x, z, m_gyro.getAsDouble());
    SmartDashboard.putNumber("Gyro ADIS", m_gyro.getAsDouble());

    try {
      m_recordjoystick.WriteCSV(x, y, z);
    } catch (IOException e) {
      DriverStation.reportWarning("Can't Find csv", false);
      DriverStation.reportWarning(e.toString(), false);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) { }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
