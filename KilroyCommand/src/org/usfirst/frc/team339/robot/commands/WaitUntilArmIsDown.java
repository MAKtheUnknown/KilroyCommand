package org.usfirst.frc.team339.robot.commands;

import org.usfirst.frc.team339.robot.subsystems.ManipulatorArm;
import org.usfirst.frc.team339.robotmap.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WaitUntilArmIsDown extends Command {

    public WaitUntilArmIsDown() 
    {
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	if(Subsystems.intakeArm.getPosition() <= ManipulatorArm.MIN_SOFT_ARM_STOP + WaitUntilArmIsDown.ARM_DOWN_THRESHOLD)
    	{
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    private static final double ARM_DOWN_THRESHOLD = 3.0;
}
