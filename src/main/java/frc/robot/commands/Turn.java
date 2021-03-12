// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;

public class Turn extends CommandBase {
  private double target_angle, last_heading, heading;
  private double wheelBase = Constants.DriveConstants.WHEELBASE;
  private Drivetrain m_drive;
  private double radius = 0;
  private double error; 
  private double kP = 0.005;
  private double speed = Constants.DriveConstants.AUTODRIVE;
  private boolean forward=true;

  /** Creates a new Turn. */
  public Turn(double angle) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.m_drive);
    m_drive = RobotContainer.m_drive;
    target_angle = angle;
    radius = 0;
    
  }

  public Turn(double angle, double turnRadius) {
    addRequirements(RobotContainer.m_drive);
    m_drive = RobotContainer.m_drive;
    target_angle = angle;
    radius = turnRadius;

  }

  public Turn(double angle, double turnRadius, boolean direction) {
    addRequirements(RobotContainer.m_drive);
    m_drive = RobotContainer.m_drive;
    target_angle = angle;
    radius = turnRadius;
    forward  = direction;
  }
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drive.resetEncoder();
    m_drive.autonomous();
    last_heading = m_drive.yaw();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double rSpeed, lSpeed, turnSpeed;

    heading = m_drive.yaw();
    error = heading - target_angle;

    turnSpeed = (Math.abs(error)>90) ? speed * 1.5 : speed;
    if(Math.abs(error)<15)  turnSpeed *= 0.85;
    
    rSpeed = (target_angle < 0 ) ? turnSpeed : turnSpeed*(radius - wheelBase)/(radius+wheelBase);;
    lSpeed = (target_angle < 0 ) ? turnSpeed*(radius - wheelBase)/(radius+wheelBase) : turnSpeed;
  
    // double change = heading - last_heading;

    if (!forward) {
      m_drive.tankDrive(lSpeed, rSpeed);
    } else {
      m_drive.tankDrive(-lSpeed, -rSpeed);
    }
    System.out.println("Yaw: "+m_drive.yaw()+" Error: "+error);
    last_heading = heading;
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
    return (Math.abs(error)<3);
  }
}
