package org.usfirst.frc.team339.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
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
