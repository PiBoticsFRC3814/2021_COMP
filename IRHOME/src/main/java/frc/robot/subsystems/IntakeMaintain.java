// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants;

public class IntakeMaintain extends SubsystemBase {
  /**
   * Creates a new IntakeBalls.
   */
  WPI_TalonSRX frontIndex;
  WPI_TalonSRX rearIndex;
  WPI_TalonSRX mecanumIntake;
  WPI_TalonSRX mecanumBackIntake;
  //WPI_TalonSRX liftIntake;

  //DigitalInput lowerIntake;
  //DigitalInput upperIntake;

  public IntakeMaintain() {
    frontIndex = new WPI_TalonSRX(Constants.frontIntake);
    rearIndex = new WPI_TalonSRX(Constants.rearIntake);
    mecanumIntake = new WPI_TalonSRX(Constants.mecanumIntake);
    mecanumBackIntake = new WPI_TalonSRX(Constants.mecanumBackIntake);
    //liftIntake = new WPI_TalonSRX(Constants.liftIntake);

    //lowerIntake = new DigitalInput(Constants.lowerInput);
    //upperIntake = new DigitalInput(Constants.upperInput);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void upperOn(){
    rearIndex.set(-Constants.ballIntakeSpeed);
    frontIndex.set(Constants.ballIntakeSpeed);
  }
  public void mecanumOn(){
    mecanumIntake.set(Constants.mecanumIntakeSpeed);
    mecanumBackIntake.set(Constants.mecanumIntakeSpeed);
  }
  public void intakeOn(){
    mecanumIntake.set(Constants.mecanumIntakeSpeed);
    mecanumBackIntake.set(Constants.mecanumIntakeSpeed);
    rearIndex.set(-Constants.ballIntakeSpeed);
    frontIndex.set(Constants.ballIntakeSpeed);
  }
  public void upperOff(){
    rearIndex.set(0.0);
    frontIndex.set(0.0);
  }
  public void mecanumOff(){
    mecanumIntake.set(0.0);
    mecanumBackIntake.set(0.0);
  }
  public void intakeOff(){
    rearIndex.set(0.0);
    frontIndex.set(0.0);
    mecanumIntake.set(0.0);
    mecanumBackIntake.set(0.0);
  }
  public void upperReverse(){
    rearIndex.set(Constants.ballIntakeSpeed/2);
    frontIndex.set(-Constants.ballIntakeSpeed/2);
  }
  
  
}
