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



public class ClimbMotor extends SubsystemBase {

  private static final WPI_TalonSRX climbMotor = new WPI_TalonSRX(Constants.climbMotor);
  
  /**
   * Creates a new ClimbMotor.
   */
  public ClimbMotor() {
    climbMotor.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void goUp(){
      climbMotor.set(Constants.upSpeed);

  }

  public void goDown(){
      climbMotor.set(Constants.downSpeed);

  }

  public void stopClimb(){
      climbMotor.set(0.0);

  }
}
