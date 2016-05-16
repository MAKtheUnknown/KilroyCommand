package org.usfirst.frc.team339.robot.commands;

import org.usfirst.frc.team339.robotmap.Subsystems;
import edu.wpi.first.wpilibj.command.Command;

/**
 *	Takes ball into the arm.
 */
public class PullInBall extends Command
{

	private double timeOfEntry = 0.0;
	private double timeAfterEntry = 0.0;
	
public PullInBall ()
{
    // Use requires() here to declare subsystem dependencies
    requires(Subsystems.intakeArm);
    
    this.timeAfterEntry = this.DELAY_AFTER_BALL_DETECTION;
}

public PullInBall (double customTime)
{
    requires(Subsystems.intakeArm);
    
    this.timeAfterEntry = customTime;
}

// Called just before this Command runs the first time
protected void initialize ()
{
}

// Called repeatedly when this Command is scheduled to run
protected void execute ()
{
    Subsystems.intakeArm.pullInBall(true);
    
    if(Subsystems.intakeArm.ballIsOut() == false)
    {
    	if(timeOfEntry == 0.0)
    	{
    		timeOfEntry = System.currentTimeMillis();
    	}
    }
}

// Make this return true when this Command no longer needs to run execute()
protected boolean isFinished ()
{
    if (System.currentTimeMillis() > this.timeOfEntry + this.timeAfterEntry)
        {
        return true;
        }
    return false;
}

// Called once after isFinished returns true
protected void end ()
{
    Subsystems.intakeArm.stopIntakeMotors();
}

// Called when another command which requires one or more of the same
// subsystems is scheduled to run
protected void interrupted ()
{
}


private static final double DELAY_AFTER_BALL_DETECTION = 0.12;

}
