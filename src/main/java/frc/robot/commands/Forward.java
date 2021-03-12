// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;

public class Forward extends CommandBase {
  private double target_dist, curr_dist;
  private static Drivetrain m_drive;
  private double kP = 0.05;
  private double speed;

  /** Creates a new Forward. */
  public Forward(double dist) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_drive);
    m_drive = RobotContainer.m_drive;
    target_dist = dist;
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    curr_dist = 0;
    m_drive.resetEncoder();
    m_drive.autonomous();
     
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double error = m_drive.yaw();
    double turnPower = error * kP;

    curr_dist = (m_drive.l_dist() + m_drive.r_dist() )/2;
    speed = (Math.abs(target_dist-curr_dist) < 20) ? 0.4 : Constants.DriveConstants.AUTODRIVE;;
    speed = (target_dist > 0) ? -speed : speed;

    m_drive.arcadeDrive(speed, -turnPower);
    // m_drive.arcadeDrive(speed, 0);
  
    
    System.out.println("Distance: "+curr_dist+" Error: "+error);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drive.stop();
    m_drive.teleop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (Math.abs(curr_dist) >= Math.abs(target_dist));
  }
}
