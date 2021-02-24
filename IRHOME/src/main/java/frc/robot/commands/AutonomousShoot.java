// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.IntakeMaintain;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

public class AutonomousShoot extends CommandBase {
  /**
   * Creates a new AutoShoot.
   */
  Limelight m_LimeLight;
  Shooter m_Shooter;
  DriveTrain m_PiboticsDrive;
  IntakeMaintain m_Intake;
  Timer shootDelay;
  public Timer Timeguy; 


  public static double ys = 0.0, zs = 0.0;
  public static int counter = 0;
  public static int timeOut = 0;
  public static int position = 0;

  public static Boolean isYPos = false;
  public static Boolean isZPos = false;

  public AutonomousShoot(Limelight limelight, Shooter shooter, DriveTrain drive, IntakeMaintain intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_LimeLight = limelight;
    m_Shooter = shooter;
    m_PiboticsDrive = drive;
    m_Intake = intake;
    addRequirements(m_LimeLight);
    addRequirements(m_Shooter);
    addRequirements(m_PiboticsDrive);
    addRequirements(m_Intake);
    shootDelay = new Timer();
    Timeguy = new Timer();

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Timeguy.reset();
    Timeguy.start();
    shootDelay.reset();
    shootDelay.start();
    m_LimeLight.position1 = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putBoolean("Position", m_LimeLight.position1);
    SmartDashboard.putNumber("Timer", shootDelay.get());
    /*if (!m_LimeLight.position1)
    {
      if (m_LimeLight.closest() == 1)
      {
        //short code
        m_LimeLight.onLight();
        m_LimeLight.displayOutput();
        SmartDashboard.putBoolean("Target Acquired", m_LimeLight.isValidTarget());

        if (m_LimeLight.yaw > 1)
        {
          ys = 0.3;
          isYPos = false;
        }
        else if (m_LimeLight.yaw < -1)
        {
          ys = -0.3;
          isYPos = false;
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
        }
        else if (m_LimeLight.z > Constants.Z1Farthest)
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
          m_LimeLight.position1 = false;
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
          Timer.delay(1.0);
        }
        m_PiboticsDrive.Drive(zs, 0.0, ys, 0.0);
        SmartDashboard.putNumber("Zs", zs);
        SmartDashboard.putNumber("Ys", ys);
        SmartDashboard.putNumber("Counter", timeOut);
        SmartDashboard.putNumber("pos", position);
        SmartDashboard.putBoolean("ValidTarget", m_LimeLight.isValidTarget());
        m_Shooter.WheelsOn(Constants.shooterSpeed);
      }
      else if (m_LimeLight.closest() == 2)
      {
        //far code
        m_LimeLight.onLight();
        m_LimeLight.displayOutput();
        SmartDashboard.putBoolean("Target Acquired", m_LimeLight.isValidTarget());
      if (m_LimeLight.isValidTarget()){
        if (m_LimeLight.yaw > 1)
        {
          ys = 0.3;
          isYPos = false;
        }
        else if (m_LimeLight.yaw < -1)
        {
          ys = -0.3;
          isYPos = false;
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
        }
        else if (m_LimeLight.z > Constants.Z1Farthest)
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
          m_LimeLight.position1 = false;
        }
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
          Timer.delay(1);
        }
        m_PiboticsDrive.Drive(zs, 0.0, ys, 0.0);
        SmartDashboard.putNumber("Zs", zs);
        SmartDashboard.putNumber("Ys", ys);
        SmartDashboard.putNumber("Counter", timeOut);
        SmartDashboard.putNumber("pos", position);
        SmartDashboard.putBoolean("ValidTarget", m_LimeLight.isValidTarget());
        m_Shooter.WheelsOn(Constants.shooterSpeed);
        
      }
    }
    else
    {
      if (shootDelay.get() >= 0.5)
      {
        m_Intake.intakeOn();;
        Timer.delay(0.05);
        m_Intake.intakeOff();
        shootDelay.reset();
        counter++;
        SmartDashboard.putNumber("Shotcount", counter);
      }
      else
      {
        m_Intake.intakeOff();
      }
    }*/

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(counter <= 10 && Timeguy.get()<12)
    {
      return false;
    }
    else
    {
      m_PiboticsDrive.Drive(0.0, 0.0, 0.0, 0.0);
      m_Intake.intakeOff();
      m_Shooter.WheelsOff();
      m_LimeLight.offLight();
      isYPos = false;
      isZPos = false;
      m_LimeLight.position1 = false;
      return true;
    }
  }
}
