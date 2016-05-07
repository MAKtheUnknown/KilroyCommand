package org.usfirst.frc.team339.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AimAndShoot extends CommandGroup {
    
    public  AimAndShoot() 
    {
    	
    	addSequential(new DriveByCamera());
        addSequential(new Shoot());

    }
}
