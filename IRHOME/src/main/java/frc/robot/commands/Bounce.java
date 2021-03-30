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
public class Bounce extends SequentialCommandGroup {
  /** Creates a new Bounce. */
  public Bounce(DriveTrain driveTrain, ADIS16448_IMU gyroscope) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    //BATTERY 2
    addCommands(
    new GyroReset(gyroscope),
    new TimedForward(driveTrain, gyroscope, 1.43, 5.0, 0.3),
    new AngleTurn(driveTrain, gyroscope, 90.0),
    new TimedForward(driveTrain, gyroscope, 1.66, 90.0, 0.3),
    new AngleTurn(driveTrain, gyroscope, 110.0),
    new TimedForward(driveTrain, gyroscope, 3.66, 110.0, -0.3),
    new AngleTurn(driveTrain, gyroscope, 0.0),
    new TimedForward(driveTrain, gyroscope, 1.33, 0.0, 0.3),
    new AngleTurn(driveTrain, gyroscope, 90.0),
    new TimedForward(driveTrain, gyroscope, 3.53, 90.0, 0.3),
    new TimedForward(driveTrain, gyroscope, 1.0, 90.0, 0.0),
    new AngleTurn(driveTrain, gyroscope, 90.0),
    new TimedForward(driveTrain, gyroscope, 3.23, 90.0, -0.3),
    new AngleTurn(driveTrain, gyroscope, 0.0),
    new TimedForward(driveTrain, gyroscope, 2.36, 0.0, 0.3),
    new AngleTurn(driveTrain, gyroscope, 90.0),
    new TimedForward(driveTrain, gyroscope, 3.43, 90.0, 0.3),
    new TimedForward(driveTrain, gyroscope, 1.0, 90.0, 0.0),
    new AngleTurn(driveTrain, gyroscope, 90.0),
    new TimedForward(driveTrain, gyroscope, 1.66, 90.0, -0.3),
    new AngleTurn(driveTrain, gyroscope, 0.0),
    new TimedForward(driveTrain, gyroscope, 2.0, 0.0, 0.3));
  }
}
