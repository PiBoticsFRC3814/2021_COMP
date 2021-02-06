// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.ColorSensorV3;

import java.util.Objects;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

public class ControlPanel extends SubsystemBase {
  /**
   * Creates a new ControlPanel.
   */
  private static final I2C.Port i2cPort = I2C.Port.kOnboard;

  private static final ColorSensorV3 colorSensor = new ColorSensorV3(i2cPort);

  private static final ColorMatch m_colorMatcher = new ColorMatch();

  private static final Color kCyan = ColorMatch.makeColor(.25, .45, .3);
  private static final Color kGreen = ColorMatch.makeColor(.25, .56, .19);
  private static final Color kRed = ColorMatch.makeColor(.56, .33, .1);
  private static final Color kYellow = ColorMatch.makeColor(.39, .50, .1);

  Color detectedColor = colorSensor.getColor();
  ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

  public static final WPI_TalonSRX ControlPanelMotor = new WPI_TalonSRX(Constants.ControlPanelMotor);

  public static String colorCode = "N";
  public static String gotoColor = "N";
  public static String fmsData = "N";
  public static String initialColor = "N";
  public static int counter = 0;
  public static boolean posFinish = false;
  public static boolean rotFinish = false;
  public static boolean r = false;
  public static boolean g = false;
  public static boolean b = false;
  public static boolean y = false;

  public ControlPanel() {
    m_colorMatcher.addColorMatch(kCyan);
    m_colorMatcher.addColorMatch(kGreen);
    m_colorMatcher.addColorMatch(kRed);
    m_colorMatcher.addColorMatch(kYellow);
  }

  public void PutColorValue() {
    detectedColor = colorSensor.getColor();
    match = m_colorMatcher.matchClosestColor(detectedColor);
    if (match.color == kCyan)
    {
      colorCode = "B";
    }
    else if (match.color == kGreen)
    {
      colorCode = "G";
    }
    else if (match.color == kRed)
    {
      colorCode = "R";
    }
    else if (match.color == kYellow)
    {
      colorCode = "Y";
    }
    else
    {
      colorCode = "N";
    }
    SmartDashboard.putNumber("R", detectedColor.red);
    SmartDashboard.putNumber("G", detectedColor.green);
    SmartDashboard.putNumber("B", detectedColor.blue);
    SmartDashboard.putString("Detected Color", colorCode);
  }

  public void GetFMS() {
    fmsData = DriverStation.getInstance().getGameSpecificMessage();
    if (Objects.equals(fmsData, "R"))
    {
      gotoColor = "G";
    }
    else if (Objects.equals(fmsData, "G"))
    {
      gotoColor = "B";
    }
    else if (Objects.equals(fmsData, "B"))
    {
      gotoColor = "Y";
    }
    else if (Objects.equals(fmsData, "Y"))
    {
      gotoColor = "R";
    }
  }

  public void Position() {
    PutColorValue();
    if (Objects.equals(fmsData,colorCode))
    {
      ControlPanelMotor.set(-0.5);
      Timer.delay(0.03);
      ControlPanelMotor.set(0.0);
      posFinish = true;
    }
    else
    {
      ControlPanelMotor.set(0.5);
    }
    SmartDashboard.putString("FMSDATA", fmsData);
  }


  public void Rotation() {
    PutColorValue();
    if (Objects.equals(colorCode,"R"))
    {
      r = true;
    }
    else if (Objects.equals(colorCode,"G"))
    {
      g = true;
    }
    else if (Objects.equals(colorCode,"B"))
    {
      b = true;
    }
    else if (Objects.equals(colorCode,"Y"))
    {
      y = true;
    }
    

    SmartDashboard.putBoolean("rbool", r);
    SmartDashboard.putBoolean("gbool", g);
    SmartDashboard.putBoolean("bbool", b);
    SmartDashboard.putBoolean("ybool", y);
    SmartDashboard.putNumber("Counter", counter);

    if (r && g && b && y)
    {
      r = false;
      g = false;
      b = false;
      y = false;
      counter++;
    }

    if (counter <= 10)
    {
      ControlPanelMotor.set(0.7);
    }
    else 
    {
      ControlPanelMotor.set(0.0);
      rotFinish = true;
    }
  }

  public void stop() {
    ControlPanelMotor.set(0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
