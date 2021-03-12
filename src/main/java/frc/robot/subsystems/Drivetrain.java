// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;
 import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class Drivetrain extends SubsystemBase {
  /** Creates a new drivetrain. */
  private final WPI_TalonSRX l_talon, r_talon;
  private final WPI_VictorSPX l_victor, r_victor;
  private final Encoder r_encoder, l_encoder;
  private final DifferentialDrive m_drive;
  private double l_rot, r_rot, l_dist, r_dist;
  private double x_ang, y_ang, z_ang;
  private AHRS ahrs;
  private Timer elapsed;

  public Drivetrain() {
      
      l_talon = new WPI_TalonSRX(DriveConstants.LDRIVE);
      r_talon = new WPI_TalonSRX(DriveConstants.RDRIVE);
      l_victor = new WPI_VictorSPX(DriveConstants.LDRIVE);
      r_victor = new WPI_VictorSPX(DriveConstants.RDRIVE);
      l_encoder = new Encoder(DriveConstants.ENCL1, DriveConstants.ENCL2);
      r_encoder = new Encoder(DriveConstants.ENCR1, DriveConstants.ENCR2, true);
      l_victor.follow(l_talon);
      r_victor.follow(r_talon);
      m_drive = new DifferentialDrive(l_talon, r_talon);
      l_encoder.reset();
      r_encoder.reset();
      l_encoder.setDistancePerPulse((3*3.14*2)/2048);
      r_encoder.setDistancePerPulse((3*3.14*2)/2048);
      elapsed = new Timer();
      elapsed.reset();
      elapsed.start();

      try {
         ahrs = new AHRS(SerialPort.Port.kUSB);
        //  ahrs = new AHRS(I2C.Port.kMXP);   
         // ahrs = new AHRS(I2C.Port.kOnboard);
      } catch(RuntimeException ex) {
        DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
      }
    }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    l_rot = l_encoder.getDistance();
    r_rot = r_encoder.getDistance();
    x_ang = ahrs.getRoll();
    y_ang = ahrs.getPitch();
    z_ang = ahrs.getYaw();
    l_dist = l_rot; //2048 * (3*6.28);
    r_dist = r_rot; //2048 * (3*6.28);
    // SmartDashboard.putNumber("IMU_CompassHeading",  ahrs.getCompassHeading());
    SmartDashboard.putNumber("Roll",x_ang);
    SmartDashboard.putNumber("Pitch",y_ang);
    SmartDashboard.putNumber("Yaw:",z_ang);

    SmartDashboard.putNumber("Left Encoder:",l_rot);
    SmartDashboard.putNumber("Left Distance: ",l_dist);
    SmartDashboard.putNumber("Right Encoder:",r_rot);
    SmartDashboard.putNumber("Right Distance: ",r_dist);

  }

  public void arcadeDrive(double speed, double rot) {
    m_drive.arcadeDrive(speed, rot);
  }

  public void tankDrive(double left, double right) {
    m_drive.tankDrive(left, right);
  }

  public double yaw() {
    return z_ang;
  }
  public double l_rot() {
    return l_rot;
  }
  public double r_rot() {
    return r_rot;
  }
  public double l_dist() {
    return l_dist;
  }
  public double r_dist() {
    return r_dist;
  }

  public void stop() {
    m_drive.tankDrive(0,0);
  }

  public void resetEncoder() {
    l_encoder.reset();
    r_encoder.reset();
    ahrs.reset();
  }

  public void autonomous() {
    l_talon.setNeutralMode(NeutralMode.Brake);
    r_talon.setNeutralMode(NeutralMode.Brake);
  }

  public void teleop() {
    l_talon.setNeutralMode(NeutralMode.Coast);
    r_talon.setNeutralMode(NeutralMode.Coast);

  }
}
