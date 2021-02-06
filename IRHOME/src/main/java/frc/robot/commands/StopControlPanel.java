// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ControlPanel;

public class StopControlPanel extends CommandBase {
  /**
   * Creates a new StopControlPanel.
   */
  ControlPanel m_ControlPanel;
  public StopControlPanel(ControlPanel controlpanel) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_ControlPanel = controlpanel;
    addRequirements(m_ControlPanel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_ControlPanel.stop();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}

