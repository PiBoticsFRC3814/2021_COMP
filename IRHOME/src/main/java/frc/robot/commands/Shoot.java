// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class Shoot extends CommandBase {
  /**
   * Creates a new Shoot.
   */
  private final Shooter m_shooter;

  public double tempSpeed = 0.85;

  public Shoot(Shooter piboticsshooter) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_shooter = piboticsshooter;
    addRequirements(m_shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    tempSpeed=SmartDashboard.getNumber("VariableSpeed", 0.0);
    SmartDashboard.putNumber("VariableSpeed", tempSpeed);
    m_shooter.WheelsOn(tempSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

