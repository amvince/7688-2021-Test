// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Slalom extends SequentialCommandGroup {
  /** Creates a new Slalom. */
  public Slalom() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new Forward (30), // forward 2.5'
      new Turn(-45, 60), // 45 counterclowise 5' r
      new Turn(90, 90), // 90 clock 7.5 ' r
      new Turn(-45, 60), // 45 counterclock 5' r
      new Turn(-180, 30), // 180 counter 2.5' r
      new Turn(-45, 60), // 45 counterclock 5' r
      new Turn(-90, 90), // 90 counterclock 7.5' r
      new Turn(45, 60), // 45 clock 5'r
      new Forward(30) // forward 2.5'
    );
  }
}
