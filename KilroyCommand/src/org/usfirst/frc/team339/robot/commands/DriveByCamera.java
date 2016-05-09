package org.usfirst.frc.team339.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *	Puts up the camera for vision processing,
 *	Aligns on the goal,
 *	Approaches the goal,
 *	Aligns again,
 *	Shoots.
 */
public class DriveByCamera extends CommandGroup {
    
    public  DriveByCamera() 
    {
    	addSequential(new PutUpCamera());
        addSequential(new AlignByCamera());
        addSequential(new ApproachByCamera());
        addSequential(new AlignByCamera());
    }
}
