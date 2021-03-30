// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Slolom extends SequentialCommandGroup {
  /** Creates a new Slolom. */
  public Slolom(DriveTrain driveTrain, ADIS16448_IMU gyroscope) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    //BATTERY 6
    addCommands(
      new GyroReset(gyroscope),
      new TimedForward(driveTrain, gyroscope, 1.4, 0.0, 0.3),
      new AngleTurn(driveTrain, gyroscope, 90.0),
      new TimedForward(driveTrain, gyroscope, 2.0, 90.0, 0.3),
      new AngleTurn(driveTrain, gyroscope, 0.0),
      new TimedForward(driveTrain, gyroscope, 4.1, 0.0, 0.3),
      new AngleTurn(driveTrain, gyroscope, -90.0),
      new TimedForward(driveTrain, gyroscope, 2.1, -90.0, 0.3),
      new AngleTurn(driveTrain, gyroscope, 0.0),
      new TimedForward(driveTrain, gyroscope, 2.0, 0.0, 0.3),
      new AngleTurn(driveTrain, gyroscope, 90.0),
      new TimedForward(driveTrain, gyroscope, 2.2, 90.0, 0.3),
      new AngleTurn(driveTrain, gyroscope, 180.0),
      new TimedForward(driveTrain, gyroscope, 1.7, 180.0, 0.3),
      new AngleTurn(driveTrain, gyroscope, 270.0),
      new TimedForward(driveTrain, gyroscope, 2.2, 270.0, 0.3),
      new AngleTurn(driveTrain, gyroscope, 180.0),
      new TimedForward(driveTrain, gyroscope, 4.1, 180.0, 0.3),
      new AngleTurn(driveTrain, gyroscope, 90.0),
      new TimedForward(driveTrain, gyroscope, 1.7, 90.0, 0.3),
      new AngleTurn(driveTrain, gyroscope, 180.0),
      new TimedForward(driveTrain, gyroscope, 1.8, 180.0, 0.3)
    );
  }
}
