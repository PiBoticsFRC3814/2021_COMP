// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class TimedForward extends CommandBase {

  private final DriveTrain m_drive;
  private final ADIS16448_IMU gyro;
  private Double moveTime;
  private Double moveAngle;
  private Double moveSpeed;
  private Timer autoTimer;
  private Boolean done;
  private Double z = 0.0;

  /** Creates a new TimedForward. */
  public TimedForward(DriveTrain driveTrain, ADIS16448_IMU gyroscope, Double movetime, Double Angle, Double Speed) {
    m_drive = driveTrain;
    gyro = gyroscope;
    moveTime = movetime;
    moveAngle = Angle;
    moveSpeed = Speed;
    autoTimer = new Timer();
    addRequirements(m_drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    autoTimer.reset();
    m_drive.Brake();
    done = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    autoTimer.start();
    if (autoTimer.get() < moveTime) {
      if (gyro.getGyroAngleX()<moveAngle){
        z=-0.09;
      }
      else if (gyro.getGyroAngleX()>moveAngle){
        z=0.09;
      }
      m_drive.Drive(moveSpeed,0.0,z,0.0);
    }
    else {
      m_drive.Drive(0.0,0.0,0.0,0.0);
      done = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (done){
      done = false;
      m_drive.Drive(0.0,0.0,0.0,0.0);
      return true;
    }
    else{
      return false;
    }
  }
}
