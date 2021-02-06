// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */

  private WPI_TalonSRX m_motor;
  public boolean shootable = false;
  public double tempSpeed = 0.85;

  public Shooter() {
     // initialize motor
     m_motor = new WPI_TalonSRX(Constants.shooterMotor);
 
   }
  public void WheelsOn(double speed) {
    //temporary stuff;
    //tempSpeed=SmartDashboard.getNumber("SetPoint", 0.0);
    
    //normal stuff
    //m_motor.set(-tempSpeed);
    m_motor.set(-speed);
    //SmartDashboard.putNumber("SetPoint", tempSpeed);
  }
  public void WheelsOff() {
    m_motor.set(0.0);
  }
  public boolean maxRPM() {
    return shootable;
    //returns if the RPM is at proper speed
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}