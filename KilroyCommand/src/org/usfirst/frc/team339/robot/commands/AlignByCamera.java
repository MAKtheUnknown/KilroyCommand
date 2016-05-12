package org.usfirst.frc.team339.robot.commands;

import org.usfirst.frc.team339.robotmap.Subsystems;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Adjusts the yaw of the robot such that it is aimed at the detected goal.
 */
public class AlignByCamera extends Command
{

	double motorRatio;

	/**
	 * Adjusts the yaw of the robot such that it is aimed at the detected goal.
	 * 
	 */
	public AlignByCamera ()
	{
		requires(Subsystems.transmission);
		requires(Subsystems.goalVision);

		this.motorRatio = DEFAULT_ALIGNMENT_SPEED;
	}

	/**
	 * Adjusts the yaw of the robot such that it is aimed at the detected goal.
	 * 
	 * @param motorRatio
	 */
	public AlignByCamera (double motorRatio)
	{
		requires(Subsystems.transmission);
		requires(Subsystems.goalVision);

		this.motorRatio = motorRatio;
	}

	// Called just before this Command runs the first time
	protected void initialize ()
	{
		Subsystems.goalVision.processNewImage();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute ()
	{
		//Take and process new image every .5 seconds
		if (System.currentTimeMillis() % 500 == 0)
		{
			Subsystems.goalVision.processNewImage();
		}

		if (Subsystems.goalVision.getProportionalGoalX() < X_OFFSET
		        - DEADBAND)
		//the goal is to the left
		{
			//turn left
			Subsystems.transmission.drive(motorRatio, -motorRatio);
		}
		//the goal is to the right
		else if (Subsystems.goalVision.getProportionalGoalX() > X_OFFSET
		        + DEADBAND)
		{
			//turn right
			Subsystems.transmission.drive(-motorRatio, motorRatio);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished ()
	{

		if (Subsystems.goalVision.getProportionalGoalX() >= X_OFFSET
		        - DEADBAND
		        && Subsystems.goalVision
		                .getProportionalGoalX() <= X_OFFSET + DEADBAND)
		//The goal is centered.
		{
			//We are aligned.
			return true;
		}

		return false;
	}

	// Called once after isFinished returns true
	protected void end ()
	{
		Subsystems.transmission.drive(0.0, 0.0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted ()
	{

	}

	private static final double X_OFFSET = 0.0;
	private static final double DEADBAND = 0.0;
	private static final double DEFAULT_ALIGNMENT_SPEED = .55;
}
