// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Driver;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RecordJoystick extends SubsystemBase {
  /** Creates a new RecordJoystick. */

  public RecordJoystick() {
  }

  public void ReadCSV() {

  }

  public void WriteCSV(double x, double y, double z) throws java.io.IOException {
    FileWriter csvWriter = new FileWriter("/home/lvuser/JoystickData.csv", true);

    csvWriter.append(Double.toString(x));
    csvWriter.append(",");
    csvWriter.append(Double.toString(y));
    csvWriter.append(",");
    csvWriter.append(Double.toString(z));
    csvWriter.append("\n");
    
    csvWriter.flush();
    csvWriter.close();
  }

  public void WriteTimeCSV() throws IOException {
    FileWriter csvWriter = new FileWriter("/home/lvuser/JoystickData.csv", true);

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    LocalDateTime now = LocalDateTime.now();  
    
    csvWriter.append(dtf.format(now));
    csvWriter.append("\n");

    csvWriter.flush();
    csvWriter.close();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
