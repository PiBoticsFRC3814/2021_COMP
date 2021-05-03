// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class AngleTurn extends CommandBase {
  /** Creates a new AngleTurn. */
  private final DriveTrain m_drive;
  private final ADIS16448_IMU gyro;
  private Double moveAngle;
  private Boolean done;
  private Double z = 0.0;

  public AngleTurn(DriveTrain driveTrain, ADIS16448_IMU gyroscope, Double Angle) {
    m_drive = driveTrain;
    gyro = gyroscope;
    moveAngle = Angle;
    addRequirements(m_drive);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    done = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (gyro.getGyroAngleX()>moveAngle + 1){
      z = -(Math.abs((gyro.getGyroAngleX()-moveAngle)*0.00333)+0.2);
    }
    if (gyro.getGyroAngleX()<moveAngle - 1){
      z= Math.abs((gyro.getGyroAngleX()-moveAngle)*0.00333)+0.2;
    }
    if ((moveAngle+1)>gyro.getGyroAngleX() && (moveAngle-1)<gyro.getGyroAngleX()){
      z = 0.0;
      done = true;
    }
    m_drive.Drive(0.0, -z, false, 0.0, 0.0);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (done){
      done = false;
      m_drive.Drive(0.0, 0.0, false, 0.0, 0.0);
      return true;
    }
    else{
      return false;
    }
  }
}
