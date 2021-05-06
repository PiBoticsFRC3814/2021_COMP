// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.IntakeMaintain;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

public class AutonomousShoot extends CommandBase {
  /** Creates a new AutonomousShoot. */
  Limelight m_LimeLight;
  DriveTrain m_PiboticsDrive;
  Shooter m_Shooter;
  IntakeMaintain m_Intake;
  Timer shootDelay;

  private static double ys, zs;
  private static int timeOut = 0;
  private static int position = 0;
  private static int counter = 0;

  private Boolean isYPos = false;
  private Boolean isXPos = false;
  private Boolean POS = false;

  public AutonomousShoot(Limelight light, Shooter shoot, DriveTrain drive, IntakeMaintain intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_LimeLight = light;
    m_Shooter = shoot;
    m_PiboticsDrive = drive;
    m_Intake = intake;
    shootDelay = new Timer();
    addRequirements(m_LimeLight);
    addRequirements(m_Shooter);
    addRequirements(m_PiboticsDrive);
    addRequirements(m_Intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timeOut = 0;
    position = 0;
    counter = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_Shooter.WheelsOn(Constants.shooterSpeed);
    m_LimeLight.onLight();
    m_LimeLight.displayOutput();
    SmartDashboard.putBoolean("Target Acquired", m_LimeLight.isValidTarget());

    if (m_LimeLight.x > 5)
    {
      zs = 0.5;
      isXPos = false;
    }
    else if (m_LimeLight.x < -5)
    {
      zs = -0.5;
      isXPos = false;
    }
    else
    {
      zs = 0;
      isXPos = true;
    }
    if (m_LimeLight.y < Constants.Z1Lowest)
    {
      ys = 0.3;
      isYPos = false;
    }
    else if (m_LimeLight.y > Constants.Z1Farthest)
    {
        ys = -0.3;
      isYPos = false;
    }
    else
    {
      ys = 0;
      isYPos = true;
    }

    if (isXPos && isYPos)
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
      POS = true;
      DriverStation.reportWarning("I made it to my position", false);
    }

    if (POS && counter < 5)
    {
      shootDelay.start();
      if (shootDelay.get() >= 0.5)
      {
        m_Intake.intakeOn();;
        Timer.delay(0.1);
        m_Intake.intakeOff();
        shootDelay.reset();
        counter++;
      }
      else
      {
        m_Intake.intakeOff();
      }
    }

    m_PiboticsDrive.Drive(-ys, zs, false, 0.0, 0.0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (timeOut > 1000 || counter >= 5)
    {
      m_PiboticsDrive.Drive(0.0, 0.0, false, 0.0, 0.0);
      m_LimeLight.offLight();
      isXPos = false;
      isYPos = false;
      POS = false;
      timeOut = 0;
      position = 0;
      counter = 0;
      shootDelay.stop();
      shootDelay.reset();
      m_Shooter.WheelsOn(0.0);

      return true;
    }
    else
    {
      return false;
    }
  }
}
