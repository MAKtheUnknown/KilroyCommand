package org.usfirst.frc.team339.robot.commands;

import org.usfirst.frc.team339.robot.subsystems.CameraProp.PropPosition;
import org.usfirst.frc.team339.robotmap.Subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

/**
 *	Opens the camera solenoid, and waits for it to go up.
 *	If already up, the timer is skipped.
 */
public class PutUpCamera extends Command {

	private long startTime;
	private double timer;
	private boolean skipTimer = false;
	
    public PutUpCamera() 
    {
        requires(Subsystems.cameraProp);
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {    	
    	if(Subsystems.cameraProp.getPosition() == (PropPosition.UP))
    	{
    		skipTimer = true;
    	}
    	
    	Subsystems.cameraProp.setPosition(PropPosition.UP);
    	startTime = System.currentTimeMillis();
    	timer = DEFAULT_STOP_TIME;
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	if(System.currentTimeMillis() >= startTime + timer || skipTimer)
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
    
    private final double DEFAULT_STOP_TIME = 2.0;
}
