// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Bounce extends SequentialCommandGroup {
  /** Creates a new Bounce. */
  public Bounce() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands (
      new Forward(30), // forward 2.5'
      new Turn(-90, 30), // 90 counter 2.5' r
      new Forward(30), // forward 2.5'
      new Turn(180), // 180
      new Turn(30, 127), // 30 counter 11.5'
      new Turn(-150, 30), // 150 counter 2.5'
      new Forward(90), // forward 7.5
      new Turn(180), // 180
      new Forward(90), // forward 7.5
      new Turn(-90, 30), // 90 counter 2.5'
      new Forward(30), // forward 2.5
      new Turn(-90, 30), // 90 counter 2.5
      new Forward(90), // forward 7.5
      new Turn(180), // 180
      new Turn(-90, 60) // 90 counter 5'
    );
  }
}
