package org.usfirst.frc.team339.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *	Moves arm up and puts the ball into the catapult.
 */
public class DepositBall extends CommandGroup
{

public DepositBall ()
{
    addSequential(new PositionArm(DEPOSIT, DEFAULT_SPEED));
    addSequential(new PullInBall());
}



public static final double DEPOSIT = 130.0;
public static final double DEFAULT_SPEED = 1.0;
}
