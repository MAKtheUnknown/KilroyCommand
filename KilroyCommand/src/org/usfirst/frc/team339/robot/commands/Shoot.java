package org.usfirst.frc.team339.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *	Fires, but makes sure ball is in and arm is out of the way.
 */
public class Shoot extends CommandGroup
{

public Shoot ()
{

    addSequential(new DepositBall());
    addSequential(new PositionArm(ARM_OUT_OF_WAY, VACATE_SPEED));
    addSequential(new FireCatapult());

}

private final double ARM_OUT_OF_WAY = 120.0;
private final double VACATE_SPEED = 1.0;
}
