// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class PathA extends SequentialCommandGroup {
  /** Creates a new PathA. */
  public PathA() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
   
    // For Distance Measurement Testing
    
    addCommands(
      new Forward(50)
    );
    
    // Figure 8 - Test Path
    /*
    addCommands(
     new Forward(30),
     new Turn(90),
     new Forward(30),
     new Turn(45),
     new Forward(30),
     new Turn(-45),
     new Forward(30),
     new Turn(-90),
     new Forward(30),
     new Turn(-90),
     new Forward(30),
     new Turn(-45),
     new Forward(30),
     new Turn(45),
     new Forward(30),
     new Turn(90)
    */

    // Barrel Race (Autonav Path 1)
    /*
    addCommands(
      new Forward(120), // Forward 7.5' + 2.5' inside box
      new Turn(360,30), // , new WaitCommand(0.2), new Turn(180, 30),// 360 spin around 2.5' radius
      new Forward(90), // Forward 7.5'
      new Turn(-270, 30), // 270 spin counterclockwise 2.5' radius
      new Turn (-90, 60), // 90 spin counterclockwise 5' radius
      new Turn (-180, 30), // 180 spin counterclockwise 2.5' radius
      new Forward(22.5*12) // Forward 22.5' (fully inside end zone)
    );
    */
    
    // Slalom
    /*
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
    */

    // Bounce
    /*
    addCommands (
      new Forward(30), // forward 2.5'
      new Turn(-90, 30), // 90 counter 2.5' r
      new Forward(30), // forward 2.5'
      new Turn(180), // 180
      new Forward(30, 127), // 30 counter 11.5'
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
    */
    
  }
}
