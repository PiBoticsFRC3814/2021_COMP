// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.NetworkButton;
import edu.wpi.first.wpilibj.Joystick;




/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  //ADXRS450_Gyro gyro = new ADXRS450_Gyro();
  ADIS16448_IMU gyro = new ADIS16448_IMU();


  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  private final DriveTrain m_piboticsdrive = new DriveTrain();
  private final Shooter m_shooter = new Shooter();
  private final IntakeMaintain m_IntakeMaintain = new IntakeMaintain();
  private final Limelight m_LimeLight = new Limelight();
  private final ControlPanel m_ControlPanel = new ControlPanel();

  private final Joystick driverStick = new Joystick(Constants.oi_Driver);

  private final CommandBase m_autonomousCommand = new Autonomous1(m_piboticsdrive,m_LimeLight,m_shooter,m_IntakeMaintain);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    m_piboticsdrive.setDefaultCommand(new PiboticsDrive(() -> driverStick.getY(), () -> driverStick.getX(), () -> driverStick.getZ(), () -> gyro.getGyroAngleX(), m_piboticsdrive));
    m_LimeLight.setDefaultCommand(new GetLimelight(m_LimeLight));
    m_ControlPanel.setDefaultCommand(new GrabColorData(m_ControlPanel));
    SmartDashboard.putBoolean("GyroReset", false);
    SmartDashboard.putBoolean("Move Green", false);
    SmartDashboard.putBoolean("Move Yellow", false);
    SmartDashboard.putBoolean("Move Blue", false);
    SmartDashboard.putBoolean("Move Red", false);
    

    // Configure the button bindings
    configureButtonBindings();


  
    
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //joystick buttons
    final JoystickButton LowerIntake = new JoystickButton(driverStick, 7);
    final JoystickButton UpperIntake = new JoystickButton(driverStick, 1);
    final JoystickButton AllIntake = new JoystickButton(driverStick, 8);
    final JoystickButton Shooter = new JoystickButton(driverStick, 2);
    final JoystickButton LimelightZ1 =  new JoystickButton(driverStick, 9);
    final JoystickButton LimelightZ2 = new JoystickButton(driverStick, 10);
    final JoystickButton LimelightZ3 = new JoystickButton(driverStick, 11);
    final JoystickButton LimelightZ4 = new JoystickButton(driverStick, 12);
    final JoystickButton Outtake = new JoystickButton(driverStick, 5);
    final JoystickButton ToggleLight = new JoystickButton(driverStick, 4);
    final NetworkButton GyroReset = new NetworkButton("SmartDashboard", "GyroReset");
    final NetworkButton LimelightZ1Network = new NetworkButton("SmartDashboard", "Move Green");
    final NetworkButton LimelightZ2Network = new NetworkButton("SmartDashboard", "Move Yellow");
    final NetworkButton LimelightZ3Network = new NetworkButton("SmartDashboard", "Move Blue");
    final NetworkButton LimelightZ4Network = new NetworkButton("SmartDashboard", "Move Red");

    
    AllIntake.whenPressed(new AllIntakeOn(m_IntakeMaintain));
    AllIntake.whenReleased(new AllIntakeOff(m_IntakeMaintain));

    LowerIntake.whenPressed(new LowerIntakeOn(m_IntakeMaintain));
    LowerIntake.whenReleased(new LowerIntakeOff(m_IntakeMaintain));

    UpperIntake.whenPressed(new UpperIntakeOn(m_IntakeMaintain));
    UpperIntake.whenReleased(new UpperIntakeOff(m_IntakeMaintain));

    Shooter.whenPressed(new Shoot(m_shooter,m_LimeLight));
    Shooter.whenReleased(new StopShoot(m_shooter,m_LimeLight));

    LimelightZ1.whenPressed(new Z1Limelight(m_piboticsdrive,m_LimeLight,gyro));
    LimelightZ1.whenReleased(new GetLimelight(m_LimeLight));

    LimelightZ2.whenPressed(new Z2Limelight(m_piboticsdrive,m_LimeLight,gyro));
    LimelightZ2.whenReleased(new GetLimelight(m_LimeLight));

    LimelightZ3.whenPressed(new Z3Limelight(m_piboticsdrive,m_LimeLight,gyro));
    LimelightZ3.whenReleased(new GetLimelight(m_LimeLight));

    LimelightZ4.whenPressed(new Z4Limelight(m_piboticsdrive,m_LimeLight,gyro));
    LimelightZ4.whenReleased(new GetLimelight(m_LimeLight));

    LimelightZ1Network.whenPressed(new Z1Limelight(m_piboticsdrive,m_LimeLight,gyro));
    LimelightZ1Network.whenReleased(new GetLimelight(m_LimeLight));

    LimelightZ2Network.whenPressed(new Z2Limelight(m_piboticsdrive,m_LimeLight,gyro));
    LimelightZ2Network.whenReleased(new GetLimelight(m_LimeLight));

    LimelightZ3Network.whenPressed(new Z3Limelight(m_piboticsdrive,m_LimeLight,gyro));
    LimelightZ3Network.whenReleased(new GetLimelight(m_LimeLight));

    LimelightZ4Network.whenPressed(new Z4Limelight(m_piboticsdrive,m_LimeLight,gyro));
    LimelightZ4Network.whenReleased(new GetLimelight(m_LimeLight));

    Outtake.whenPressed(new IntakeReverse(m_IntakeMaintain));
    Outtake.whenReleased(new UpperIntakeOff(m_IntakeMaintain));
    

    


    //Position.whenPressed(new PositionControl(m_ControlPanel));
    //Position.whenReleased(new StopControlPanel(m_ControlPanel));
    //Rotation.whenPressed(new RotationControl(m_ControlPanel));
    //Rotation.whenReleased(new StopControlPanel(m_ControlPanel));

    ToggleLight.whenPressed(new ToggleLimelight(m_LimeLight));
    ToggleLight.whenReleased(new GetLimelight(m_LimeLight));

    GyroReset.whenPressed(new GyroReset(gyro));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autonomousCommand;
  }
}
