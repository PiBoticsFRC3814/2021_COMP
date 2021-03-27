// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.IntakeMaintain;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

public class AutonomousShoot2 extends CommandBase {
  /**
   * Creates a new AutoShoot.
   */
  Limelight m_LimeLight;
  Shooter m_Shooter;
  DriveTrain m_PiboticsDrive;
  IntakeMaintain m_Intake;
  ADIS16448_IMU gyro;
  Timer shootDelay;
  public Timer Timeguy; 


  public static int counter = 0;
  public static int timeOut = 0;
  public static int position = 0;

  private static double xs, ys, zs;

  private Boolean isYPos = false;
  private Boolean isZPos = false;
  private Boolean isXPos = false;
  private static Boolean shot = false;

  public AutonomousShoot2(Limelight limelight, Shooter shooter, DriveTrain drive, IntakeMaintain intake, ADIS16448_IMU gyroscope) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_LimeLight = limelight;
    m_Shooter = shooter;
    m_PiboticsDrive = drive;
    m_Intake = intake;
    gyro = gyroscope;
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
    m_LimeLight.position1 = false;
    timeOut = 0;
    position = 0;
    counter = 0;
    m_LimeLight.position2 = false;
    m_LimeLight.position3 = false;
    m_LimeLight.position4 = false;
    shot = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putBoolean("Position", m_LimeLight.position1);
    SmartDashboard.putNumber("Timer", shootDelay.get());
    m_LimeLight.onLight();
    m_LimeLight.displayOutput();
    m_Shooter.WheelsOn(Constants.MotorSpeed2);
    
      if (m_LimeLight.x > 4)
      {
        xs = 0.3;
        isXPos = false;
      }
      else if (m_LimeLight.x < -4)
      {
        xs = -0.3;
        isXPos = false;
      }
      else
      {
        xs = 0;
        isXPos = true;
      }
      if (m_LimeLight.y < Constants.Z2Lowest)
      {
        ys = 0.3;
        isYPos = false;
      }
      else if (m_LimeLight.y > Constants.Z2Farthest)
      {
        if (m_LimeLight.y <= 90)
        {
          ys = -0.125;
        }
        else
        {
          ys = -0.3;
        }
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
        m_LimeLight.position2 = true;
        DriverStation.reportWarning("I made it to my position", false);
      }

      m_PiboticsDrive.Drive(-ys, -xs, zs, gyro.getGyroAngleX());
  

    if (m_LimeLight.position2 && !shot)
      {       
        shootDelay.start();
        if (shootDelay.get()>0.5)
        {
           m_Intake.intakeOn();;
           Timer.delay(0.15);
           m_Intake.intakeOff();
           shootDelay.reset();
           counter++;
           SmartDashboard.putNumber("Shotcount", counter);
           if (counter >= 3)
           {
             shot = true;
           }
          }
      }
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(!shot)
    {
      return false;
    }
    else
    {
      m_PiboticsDrive.Drive(0.0, 0.0, 0.0, 0.0);
      m_Intake.intakeOff();
      m_Shooter.WheelsOff();
      m_LimeLight.offLight();
      isXPos = false;
      isYPos = false;
      isZPos = false;
      shot = false;
      counter = 0;
      timeOut = 0;
      position = 0;
      SmartDashboard.putBoolean("Move Yellow", false);
      return true;
    }
  }
}
