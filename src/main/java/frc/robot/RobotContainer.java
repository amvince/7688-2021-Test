// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.BarrelRun;
import frc.robot.commands.Bounce;
import frc.robot.commands.PathA;
import frc.robot.commands.Slalom;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.Command;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */

 /* TODO
Drive Forward command
Turn Command
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  public static Joystick m_oi = new Joystick(0);
  public final static Drivetrain m_drive = new Drivetrain();
  private final PathA m_pathA = new PathA();
  private final Command m_barrelRun = new BarrelRun();
  private final Command m_slalom = new Slalom();
  private final Command m_bounce = new Bounce();
  
  SendableChooser<Command> m_chooser = new SendableChooser<>();
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    m_drive.setDefaultCommand(new ArcadeDrive());
  
    m_chooser.setDefaultOption("Default", m_pathA);
    m_chooser.addOption("Barrel Run", m_barrelRun);
    m_chooser.addOption("Slalom", m_slalom);
    m_chooser.addOption("Bounce",m_bounce);

    SmartDashboard.putData(m_chooser);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {


  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_chooser.getSelected();
  }
}
