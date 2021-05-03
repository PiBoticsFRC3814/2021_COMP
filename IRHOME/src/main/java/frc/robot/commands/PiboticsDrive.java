// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

import java.util.function.DoubleSupplier;

public class PiboticsDrive extends CommandBase {
  /**
   * Creates a new PiboticsDrive.
   */
  private final DriveTrain m_drivetrain;
  private final DoubleSupplier m_y;
  private final DoubleSupplier m_z;

  public PiboticsDrive(DoubleSupplier y, DoubleSupplier z,DriveTrain piboticsdrive) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drivetrain = piboticsdrive;
    m_y = y;
    m_z = z;
    addRequirements(m_drivetrain);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drivetrain.Drive(m_y.getAsDouble(), m_z.getAsDouble(), true, Constants.deadzoneY, Constants.deadzoneZ);
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
