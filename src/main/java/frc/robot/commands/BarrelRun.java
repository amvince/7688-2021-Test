// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class BarrelRun extends SequentialCommandGroup {
  /** Creates a new BarrelRun. */
  public BarrelRun() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new Forward(120), // Forward 7.5' + 2.5' inside box
      new Turn(360,30), // , new WaitCommand(0.2), new Turn(180, 30),// 360 spin around 2.5' radius
      new Forward(90), // Forward 7.5'
      new Turn(-270, 30), // 270 spin counterclockwise 2.5' radius
      new Turn (-90, 60), // 90 spin counterclockwise 5' radius
      new Turn (-180, 30), // 180 spin counterclockwise 2.5' radius
      new Forward(22.5*12) // Forward 22.5' (fully inside end zone)
    );
}
}
