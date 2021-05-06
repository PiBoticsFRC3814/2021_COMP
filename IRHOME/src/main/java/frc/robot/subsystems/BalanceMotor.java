/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants;

public class BalanceMotor extends SubsystemBase {
  
  private static final WPI_TalonSRX balanceMotor = new WPI_TalonSRX(Constants.balanceMotor);
  /**
   * Creates a new BalanceMotor.
   */
  public BalanceMotor() {
    balanceMotor.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void goRight(){
    balanceMotor.set(Constants.rightSpeed);

  }

  public void goLeft(){
    balanceMotor.set(Constants.leftSpeed);

  }
  public void stopBalance(){
    balanceMotor.set(0.0);

  }
}
