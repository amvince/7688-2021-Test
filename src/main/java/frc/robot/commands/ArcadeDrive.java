// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.Drivetrain;


public class ArcadeDrive extends CommandBase {
  /** Creates a new ArcadeDrive. */
  private static Drivetrain m_drive;
  private static double speed = DriveConstants.MAXSPEED;
  private static double rot = DriveConstants.MAXTURN;
  
  
  public ArcadeDrive() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_drive);
    m_drive = RobotContainer.m_drive;
    // m_stick = RobotContainer.m_oi;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double rdrive = RobotContainer.m_oi.getRawAxis(1);
    double rrot = RobotContainer.m_oi.getRawAxis(0);
    m_drive.arcadeDrive(rdrive*speed, rrot*rot);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
