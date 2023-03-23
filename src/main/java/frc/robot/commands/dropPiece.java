// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;

import frc.robot.subsystems.Claw;

import frc.robot.commands.ClawDrive;
import frc.robot.commands.ClawRelease;

public class DropPiece extends CommandBase {
	private final Claw claw;
	private final String location;

	public DropPiece(Claw c, String l) {
		claw = c;
		location = l;

		addRequirements(c);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize()
	{
		Double liftTime = null;
		Double liftSpeed = 0.4;

		switch (location) {
			case "High":
				liftTime = 2.00;
				break;
			case "Mid":
				liftTime = 1.00;
				break;
			case "Low":
				liftTime = 1.00;
				liftSpeed = -liftSpeed;
				break;
			default:
				throw new Error("Invalid lift location! Pass dropPiece() a value of 'High', 'Mid', or 'Low'.");
		}
		
		// ClawGrab holdPiece = new ClawGrab(claw, 6);
		// DriveForward approachGoal = new DriveForward(drivetrain, 0.6, 0.5)
		ClawDrive moveClawToGoal = new ClawDrive(claw, liftSpeed, liftTime);
		ClawRelease dropPiece = new ClawRelease(claw, 0.5);

		CommandBase steps = Commands.sequence(
			moveClawToGoal, 
			dropPiece
		);

		steps.schedule();
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute()
	{}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted)
	{}

	// Returns true when the command should end.
	@Override
	public boolean isFinished()
	{
		return false;
	}
}
