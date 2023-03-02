// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.Timer;

import frc.robot.subsystems.Claw;

/** An example command that uses an example subsystem. */
public class ClawDrive extends CommandBase {
	@SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
	private Claw claw;
	private double speed;

	private boolean timed;
	private final double time = 1;
	private Timer timer = new Timer();

	public ClawDrive(Claw c, double rate, boolean isTimed) {
		claw = c;
		speed = rate;
		timed = isTimed;

		// Use addRequirements() here to declare subsystem dependencies.
		addRequirements(c);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute()
	{
		claw.setDriverMotor(speed);
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {}

	// Returns true when the command should end.
	@Override
	public boolean isFinished()
	{
		if (timer.get() > time && timed)
		{
			return true;
		}

		return false;
	}
}
