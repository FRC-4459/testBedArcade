// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.Timer;

/** An example command that uses an example subsystem. */
public class DriveForward extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Drivetrain drivetrain;
  private final double speed;
  private final double time;
  private Timer timer = new Timer();


  public DriveForward(Drivetrain dt, double motorSpeed, double timeSec) 
  {
    drivetrain = dt;
    speed = motorSpeed;
    time = timeSec;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize()
  {
    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    drivetrain.setLeft(speed);
    drivetrain.setRight(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted)
  {
    drivetrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished()
  {
    if (timer.get() > time)
    {
      return true;
    }
    return false;
  }
}
