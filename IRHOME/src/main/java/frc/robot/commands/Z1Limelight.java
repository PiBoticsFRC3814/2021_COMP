// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Limelight;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

public class Z1Limelight extends CommandBase {
  /**
   * Creates a new LimeLight.
/** */
  Limelight m_LimeLight;
  DriveTrain m_PiboticsDrive;

  public static double ys, zs;
  public static int timeOut = 0;
  public static int position = 0;

  private Boolean isYPos = false;
  private Boolean isZPos = false;

  public Z1Limelight(DriveTrain piboticsdrive, Limelight LimeLight) {
    m_PiboticsDrive = piboticsdrive;
    m_LimeLight = LimeLight;
    addRequirements(m_PiboticsDrive);
    addRequirements(m_LimeLight);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_LimeLight.position2 = false;
    m_LimeLight.position3 = false;
    m_LimeLight.position4 = false;

    m_LimeLight.onLight();
    m_LimeLight.displayOutput();
    SmartDashboard.putBoolean("Target Acquired", m_LimeLight.isValidTarget());

    if (m_LimeLight.yaw > 2)
    {
      ys = 0.1;
      isYPos = false;
      m_LimeLight.position1 = false;
    }
    else if (m_LimeLight.yaw < -2)
    {
      ys = -0.1;
      isYPos = false;
      m_LimeLight.position1 = false;
    }
    else
    {
      ys = 0;
      isYPos = true;
    }
    if (m_LimeLight.z < Constants.Z1Lowest)
    {
      zs = 0.3;
      isZPos = false;
      m_LimeLight.position1 = false;
    }
    else if (m_LimeLight.z > Constants.Z1Farthest)
    {
      zs = -0.3;
      isZPos = false;
      m_LimeLight.position1 = false;
    }
    else
    {
      zs = 0;
      isZPos = true;
    }

    if (isYPos && isZPos)
    {
      position++;
      DriverStation.reportWarning("I made it to position once", false);
    }
    else
    {
      m_LimeLight.position1 = false;
      DriverStation.reportWarning("I didnt make it", false);
    }

    if (!m_LimeLight.isValidTarget())
    {
      timeOut++;
    }
    else if (m_LimeLight.isValidTarget())
    {
      timeOut = 0;
    }

    if (position >= 25)
    {
      m_LimeLight.position1 = true;
      DriverStation.reportWarning("I made it to my position", false);
    }
    m_PiboticsDrive.Drive(-zs, 0.0, ys, 0.0);
    SmartDashboard.putBoolean("Position Green", m_LimeLight.position1);
}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (timeOut > 100 || m_LimeLight.position1)
    {
      m_PiboticsDrive.Drive(0.0, 0.0, 0.0, 0.0);
      m_LimeLight.offLight();
      isYPos = false;
      isZPos = false;
      SmartDashboard.putBoolean("Move Green", false);
      DriverStation.reportWarning("Quit Command", false);
      return true;
    }
    else
    {

      return false;
    }
  }
}
