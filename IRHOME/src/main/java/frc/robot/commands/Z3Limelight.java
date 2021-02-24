// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.first.wpilibj.DriverStation;
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

  private static double xs, ys, zs;
  private static int timeOut = 0;
  private static int position = 0;

  private Boolean isXPos = false;
  private Boolean isYPos = false;
  private Boolean isZPos = false;
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
    timeOut = 0;
    position = 0;
    m_LimeLight.position3 = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_LimeLight.position1 = false;
    m_LimeLight.position2 = false;
    m_LimeLight.position4 = false;
    m_LimeLight.onLight();
    m_LimeLight.displayOutput();
    SmartDashboard.putBoolean("Target Acquired", m_LimeLight.isValidTarget());
    if (m_LimeLight.x > 2)
    {
      xs = 0.3;
      isXPos = false;

    }
    else if (m_LimeLight.x < -2)
    {
      xs = -0.3;
      isXPos = false;

    }
    else
    {
      xs = 0;
      isXPos = true;
    }
    if (m_LimeLight.y < Constants.Z3Lowest)
    {
      ys = 0.3;
      isYPos = false;

    }
    else if (m_LimeLight.y > Constants.Z3Farthest)
    {
      ys = -0.3;
      isYPos = false;

    }
    else
    {
      ys = 0;
      isYPos = true;
    }
    if (gyro.getGyroAngleX() < -1)
    {
      zs = -0.1;
      isZPos = false;

    }
    else if (gyro.getGyroAngleX() > 1)
    {
      zs = 0.1;
      isZPos = false;

    }
    else
    {
      zs = 0;
      isZPos = true;
    }

    if (isXPos && isYPos && isZPos)
    {
      position++;
      DriverStation.reportWarning("I made it to position once", false);
    }
    else
    {
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
      m_LimeLight.position3 = true;
      DriverStation.reportWarning("I made it to my position", false);
    }
    m_PiboticsDrive.Drive(-ys, -xs, zs, gyro.getGyroAngleX());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (timeOut > 1000 || m_LimeLight.position3)
    {
      m_PiboticsDrive.Drive(0.0, 0.0, 0.0, 0.0);
      m_LimeLight.offLight();
      isXPos = false;
      isYPos = false;
      isZPos = false;
      timeOut = 0;
      position = 0;
      SmartDashboard.putBoolean("Move Blue", false);
      DriverStation.reportWarning("Quit Command", false);
      return true;
    }
    else
    {

      return false;
    }
  }
}
