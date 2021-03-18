// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */
  private static final WPI_TalonSRX lf = new WPI_TalonSRX(Constants.lf);
  private static final WPI_TalonSRX lr = new WPI_TalonSRX(Constants.lr);
  private static final WPI_TalonSRX rf = new WPI_TalonSRX(Constants.rf);
  private static final WPI_TalonSRX rr = new WPI_TalonSRX(Constants.rr);
  double value;


  private static final MecanumDrive piboticsdrive = new MecanumDrive(lf, lr, rf, rr);

  public DriveTrain() {
    lf.setInverted(false);
    lr.setInverted(false);
    rf.setInverted(false);
    rr.setInverted(false);
  }

  public void Drive(double y, double x, double z, double gyro) {
    piboticsdrive.driveCartesian(-x, y, z, gyro);
    value = rr.getMotorOutputVoltage();
    //DriverStation.reportError(value + " " + y, false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

