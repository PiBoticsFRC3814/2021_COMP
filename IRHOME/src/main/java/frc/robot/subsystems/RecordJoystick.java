// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RecordJoystick extends SubsystemBase {
  /** Creates a new RecordJoystick. */

  public RecordJoystick() {
  }

  public double[][] ReadCSV() throws java.io.IOException {
    boolean EOF = false;
    int i = 0;
    String data;
    String[] arrayOfData;
    double x,y,z;
    double[][] finalData = new double[Constants.ReadArrayDim1][Constants.ReadArrayDim2];
    FileReader csvReader = new FileReader("/U/Slolom.txt");
    BufferedReader bufferedReader = new BufferedReader(csvReader); 

    while(!EOF)
    {
      data = bufferedReader.readLine();

      if(data != null && !data.isEmpty() && !data.isBlank())
      {
        arrayOfData = data.split(",", 3);

        x = Double.parseDouble(arrayOfData[0]);
        y = Double.parseDouble(arrayOfData[1]);
        z = Double.parseDouble(arrayOfData[2]);

        DriverStation.reportError(data, false);


        finalData[0][i] = x;
        finalData[1][i] = y;
        finalData[2][i] = z;
        i++;
      }
      else
      {
        EOF = true;
      }
    }
    DriverStation.reportError("EOF", false);

    bufferedReader.close();
    return finalData;
  }

  public void WriteCSV(double x, double y, double z) throws java.io.IOException {
    FileWriter csvWriter = new FileWriter("/U/JoystickData.txt", true);

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
    FileWriter csvWriter = new FileWriter("/U/JoystickData.txt", true);

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
