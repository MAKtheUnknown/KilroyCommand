package org.usfirst.frc.team339.robot.commands;

import org.usfirst.frc.team339.robotmap.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Wait extends Command {

	private double startTime;
	private double timeToWait;
	
	/**
	 * Waits...
	 * 
	 * @param time in seconds
	 */
    public Wait(double time) 
    {
        this.timeToWait = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	startTime = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	if(System.currentTimeMillis() >= startTime + (timeToWait * 1000))
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
}
