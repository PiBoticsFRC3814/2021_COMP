// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;



import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

public class Shoot extends CommandBase {
  /**
   * Creates a new Shoot.
   */
  private final Shooter m_shooter;
  private final Limelight m_limelight;

  public double tempSpeed = 0.85;
  public double motorSpeed = 0.85;

  public Shoot(Shooter piboticsshooter, Limelight limelight) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_shooter = piboticsshooter;
    m_limelight = limelight;
    addRequirements(m_shooter);
    addRequirements(m_limelight);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (m_limelight.position1)
    {
        motorSpeed = Constants.MotorSpeed1;
    }
    else if (m_limelight.position2)
    {
        motorSpeed = Constants.MotorSpeed2;
    }
    else if (m_limelight.position3)
    {
        motorSpeed = Constants.MotorSpeed3;
    }
    else if (m_limelight.position4)
    {
        motorSpeed = Constants.MotorSpeed4;
    }
    else 
    {
        motorSpeed = 0.85;
        DriverStation.reportError("Can't find valid position. Shooter isn't functional", false);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //tempSpeed=SmartDashboard.getNumber("VariableSpeed", 0.0);
    //SmartDashboard.putNumber("VariableSpeed", tempSpeed);
    m_shooter.WheelsOn(motorSpeed);
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

