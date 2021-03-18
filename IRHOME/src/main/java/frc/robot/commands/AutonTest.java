// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.RecordJoystick;

public class AutonTest extends CommandBase {
  /** Creates a new AutonTest. */
  private final DriveTrain m_drivetrain;
  private final RecordJoystick m_recordjoystick;
  private double[][] data = new double [3][1000000];
  private int i = 0;
  public AutonTest(DriveTrain driveTrain, RecordJoystick recordJoystick) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_recordjoystick = recordJoystick;
    m_drivetrain = driveTrain;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    try {
      data = m_recordjoystick.ReadCSV();
    } catch (Exception e) {
      DriverStation.reportError("Failed to grab data", false);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    try {
      m_drivetrain.Drive(data[1][i], data[0][i], data[2][i], 0);
    } catch (Exception e) {
      DriverStation.reportError(data[0][i] + " " + data[1][i] + " " + data[2][i], false);
    }
    i++;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(i >= data[0].length)
    {
      return true;
    }
    else
    {
      return false;
    }
  }
}
