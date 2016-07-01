package org.usfirst.frc.team339.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Moves arm up and puts the ball into the catapult.
 */
public class DepositBall extends CommandGroup
{

	public DepositBall ()
	{
		//move the arm to a point where it can safely drop in the ball.
		addSequential(new PositionArmToDeposit());
		//pull the ball through into the catapult, with a short, additional delay.
		addSequential(new PullInBall(DepositBall.DEPOSIT_INTAKE_DELAY));
	}


	public static final double DEPOSIT_INTAKE_DELAY = 0.5;
}
