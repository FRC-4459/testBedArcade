package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.Timer;

public class Spin extends CommandBase 
{
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Drivetrain drivetrain;
  private final int seconds;
  private final boolean timed;
  private Timer timer = new Timer();

  

  public Spin(Drivetrain dt, boolean isTimed, int secs) 
  {
    drivetrain = dt;
    seconds = secs;
    timed = isTimed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    if (timed) 
    {
      timer.reset();
      timer.start();
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    drivetrain.setLeft(0.6);
    drivetrain.setRight(0.6);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    drivetrain.setLeft(0);
    drivetrain.setRight(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() 
  {
    if (timer.get() > seconds && timed)
    {
      return true;
    }
    return false;
  }
}