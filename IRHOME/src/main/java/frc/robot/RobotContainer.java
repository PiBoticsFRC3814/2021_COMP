// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.analog.adis16448.frc.ADIS16448_IMU;
//test
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;



/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...



  public final DriveTrain m_piboticsdrive = new DriveTrain();
  private final Shooter m_shooter = new Shooter();
  private final IntakeMaintain m_IntakeMaintain = new IntakeMaintain();
  private final Limelight m_LimeLight = new Limelight();
  public final ClimbMotor m_ClimbMotor = new ClimbMotor();
  public final BalanceMotor m_BalanceMotor = new BalanceMotor();

  private final Joystick driverStick = new Joystick(Constants.oi_Driver);
  private final Joystick operatorStick = new Joystick(Constants.oi_Operator);


  private final CommandBase m_autonomousCommand = new Autonomous1(m_piboticsdrive,m_LimeLight,m_shooter,m_IntakeMaintain);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    m_piboticsdrive.setDefaultCommand(new PiboticsDrive(() -> driverStick.getY(), () -> driverStick.getZ(), m_piboticsdrive));
    m_LimeLight.setDefaultCommand(new GetLimelight(m_LimeLight));
    

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
    final JoystickButton LimelightZ2 =  new JoystickButton(driverStick, 10);
    final JoystickButton Outtake = new JoystickButton(driverStick, 5);
    final JoystickButton LowerOuttake = new JoystickButton(driverStick, 3);

    final JoystickButton ToggleLight = new JoystickButton(operatorStick, 6);
    final JoystickButton ClimberUp = new JoystickButton(operatorStick, 5);
    final JoystickButton ClimberDown = new JoystickButton(operatorStick, 3);
    final JoystickButton BalancerLeft = new JoystickButton(operatorStick, 7);
    final JoystickButton BalancerRight = new JoystickButton(operatorStick, 8);

    
    AllIntake.whenPressed(new AllIntakeOn(m_IntakeMaintain));
    AllIntake.whenReleased(new AllIntakeOff(m_IntakeMaintain));

    LowerIntake.whenPressed(new LowerIntakeOn(m_IntakeMaintain));
    LowerIntake.whenReleased(new LowerIntakeOff(m_IntakeMaintain));

    UpperIntake.whenPressed(new UpperIntakeOn(m_IntakeMaintain));
    UpperIntake.whenReleased(new UpperIntakeOff(m_IntakeMaintain));
    
    Outtake.whenPressed(new IntakeReverse(m_IntakeMaintain));
    Outtake.whenReleased(new UpperIntakeOff(m_IntakeMaintain));

    LowerOuttake.whenPressed(new FrontIntakeReverse(m_IntakeMaintain));
    LowerOuttake.whenReleased(new LowerIntakeOff(m_IntakeMaintain));

    Shooter.whenPressed(new Shoot(m_shooter,m_LimeLight));
    Shooter.whenReleased(new StopShoot(m_shooter,m_LimeLight));

    LimelightZ1.whenPressed(new Z1Limelight(m_piboticsdrive,m_LimeLight));
    LimelightZ1.whenReleased(new GetLimelight(m_LimeLight));

    LimelightZ2.whenPressed(new Z2Limelight(m_piboticsdrive,m_LimeLight));
    LimelightZ2.whenReleased(new GetLimelight(m_LimeLight));

    ToggleLight.whenPressed(new ToggleLimelight(m_LimeLight));
    ToggleLight.whenReleased(new GetLimelight(m_LimeLight));

    BalancerLeft.whenPressed(new BalanceLeft(m_BalanceMotor));
    BalancerLeft.whenReleased(new StopBalance(m_BalanceMotor));

    BalancerRight.whenPressed(new BalanceRight(m_BalanceMotor));
    BalancerRight.whenReleased(new StopBalance(m_BalanceMotor));

    ClimberUp.whenPressed(new ClimbUp(m_ClimbMotor));
    ClimberUp.whenReleased(new ClimbStop(m_ClimbMotor));

    ClimberDown.whenPressed(new ClimbDown(m_ClimbMotor));
    ClimberDown.whenReleased(new ClimbStop(m_ClimbMotor));
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
