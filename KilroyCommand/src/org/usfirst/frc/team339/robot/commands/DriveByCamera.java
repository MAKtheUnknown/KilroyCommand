package org.usfirst.frc.team339.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *	Puts up the camera for vision processing,
 *	Aligns on the goal,
 *	Approaches the goal,
 *	Aligns again.
 */
public class DriveByCamera extends CommandGroup {
    
	/**
	 *	Puts up the camera for vision processing,
	 *	Aligns on the goal,
	 *	Approaches the goal,
	 *	Aligns again.
	 */
    public  DriveByCamera() 
    {
    	addSequential(new PutUpCamera());
        addSequential(new AlignByCamera());
        addSequential(new ApproachByCamera());
        addSequential(new AlignByCamera());
    }
    
}
