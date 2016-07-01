package org.usfirst.frc.team339.robot.commands;

import org.usfirst.frc.team339.robotmap.Subsystems;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Open the solenoids and unleash the catapult.
 */
public class FireCatapult extends Command
{

	double startTime;

	public FireCatapult ()
	{

		requires(Subsystems.catapult);
	}

	// Called just before this Command runs the first time
	protected void initialize ()
	{

		Subsystems.catapult.fire();

		//start the timer
		startTime = System.currentTimeMillis();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute ()
	{
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished ()
	{
		if (startTime + STOP_TIME > System.currentTimeMillis())
		{
			return true;
		}
		return false;
	}

	// Called once after isFinished returns true
	protected void end ()
	{
		Subsystems.catapult.lower();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted ()
	{
	}

	private final double STOP_TIME = 2000;
}


