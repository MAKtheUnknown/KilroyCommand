package org.usfirst.frc.team339.robot.commands;

import org.usfirst.frc.team339.robot.subsystems.ManipulatorArm.ArmPosition;
import org.usfirst.frc.team339.robotmap.Subsystems;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PositionArmToDeposit extends Command
{

	public PositionArmToDeposit ()
	{
		// Use requires() here to declare subsystem dependencies
		requires(Subsystems.intakeArm);
	}

	// Called just before this Command runs the first time
	protected void initialize ()
	{
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute ()
	{
		Subsystems.intakeArm.moveToPosition(ArmPosition.DEPOSIT);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished ()
	{
		if (Subsystems.intakeArm.isInDepositPosition()
		        || Subsystems.intakeArm.ballIsOut())
		{
			return true;
		}
		return false;
	}

	// Called once after isFinished returns true
	protected void end ()
	{
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted ()
	{
	}

}
