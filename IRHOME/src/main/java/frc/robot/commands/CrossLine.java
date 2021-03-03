// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.Timer;

public class CrossLine extends CommandBase {
  
  ADIS16448_IMU gyro;

  public Timer Timeguy; 
 
  private final DriveTrain m_crossline;
  private static double zs;
  /**
   * Creates a new CrossLine.
   */
  public CrossLine(DriveTrain m_drivetrain, ADIS16448_IMU gyroscope) {
    Timeguy = new Timer();
    m_crossline = m_drivetrain;
    gyro = gyroscope;
    addRequirements(m_crossline);

    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Timeguy.reset();
    Timeguy.start();
    m_crossline.Drive(0.0, 0.0, 0.0, 0.0);
    }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (gyro.getGyroAngleX() < -1)
    {
      zs = -0.1;
    }
    else if (gyro.getGyroAngleX() > 1)
    {
      zs = 0.1;
    }
    
    m_crossline.Drive(-0.4, 0.0, 0.0, zs);


  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_crossline.Drive(0.0, 0.0, 0.0, 0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(Timeguy.get() > 1.0){
    m_crossline.Drive(0.0, 0.0, 0.0, 0.0);
    return true;
    }else{
      return false;
    }
  }
}