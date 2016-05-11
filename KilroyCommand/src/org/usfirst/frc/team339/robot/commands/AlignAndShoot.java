package org.usfirst.frc.team339.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *	Auto-Align and shoot.
 */
public class AlignAndShoot extends CommandGroup {
    
    public  AlignAndShoot() 
    {
    	addSequential(new DriveByCamera());
        addSequential(new Shoot());
    }
}
