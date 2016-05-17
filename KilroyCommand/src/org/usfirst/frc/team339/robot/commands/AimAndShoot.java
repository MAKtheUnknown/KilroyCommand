package org.usfirst.frc.team339.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drive by camera to align on the goal,
 * then shoot.
 *
 * Formerly known as Button 8.
 */
public class AimAndShoot extends CommandGroup
{

/**
 * Drive by camera to align on the goal,
 * then shoot.
 */
public AimAndShoot ()
{

    addSequential(new DriveByCamera());
    addSequential(new Shoot());

}
}
