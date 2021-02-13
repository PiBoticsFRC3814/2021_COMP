// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Limelight;

public class Z3Limelight extends CommandBase {
  /** Creates a new Z3Limelight. */
  Limelight m_LimeLight;
  DriveTrain m_PiboticsDrive;
  ADIS16448_IMU gyro;

  public static double ys, zs;
  public static int timeOut = 0;
  public static int position = 0;

  public static Boolean isYPos = false;
  public static Boolean isZPos = false;
  public Z3Limelight(DriveTrain piboticsdrive, Limelight LimeLight, ADIS16448_IMU gyroscope) {
    // Use addRequirements() here to decl
    m_PiboticsDrive = piboticsdrive;
    m_LimeLight = LimeLight;
    gyro = gyroscope;
    addRequirements(m_PiboticsDrive);
    addRequirements(m_LimeLight);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_LimeLight.onLight();
    m_LimeLight.displayOutput(gyro.getAngle());
    SmartDashboard.putBoolean("Target Acquired", m_LimeLight.isValidTarget());
    if (m_LimeLight.yaw > 2)
    {
      ys = 0.3;
      isYPos = false;
    }
    else if (m_LimeLight.yaw < -2)
    {
      ys = -0.3;
      isYPos = false;
    }
    else
    {
      ys = 0;
      isYPos = true;
    }
    if (m_LimeLight.z < Constants.Z3Lowest)
    {
      zs = 0.3;
      isZPos = false;
    }
    else if (m_LimeLight.z > Constants.Z3Farthest)
    {
      zs = -0.3;
      isZPos = false;
    }
    else
    {
      zs = 0;
      isZPos = true;
    }

    if (isYPos && isZPos)
    {
      position++;
    }
    else
    {
      m_LimeLight.position = false;
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
      m_LimeLight.position = true;
    }
    m_PiboticsDrive.Drive(zs, 0.0, ys, 0.0);
    SmartDashboard.putNumber("Zs", zs);
    SmartDashboard.putNumber("Ys", ys);
    SmartDashboard.putNumber("Counter", timeOut);
    SmartDashboard.putNumber("pos", position);
    SmartDashboard.putBoolean("ValidTarget", m_LimeLight.isValidTarget());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (timeOut <= 100 || !m_LimeLight.position)
    {
      return false;
    }
    else
    {
      m_PiboticsDrive.Drive(0.0, 0.0, 0.0, 0.0);
      m_LimeLight.offLight();
      isZPos = false;
      isYPos = false;
      return true;
    }
  }
}