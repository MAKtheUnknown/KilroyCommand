package org.usfirst.frc.team339.robot.commands;

import org.usfirst.frc.team339.robotmap.Subsystems;

import edu.wpi.first.wpilibj.command.Command;


/**
 *	Drives a given distance with primitive correction. 
 */
public class DriveStraightDistance extends Command {

	private static double correctionFactor = .75;
	
	private double distance;
	private double motorRatio;
	
	/**
	 * Drives a given distance with correction at a given speed.
	 * 
	 * @param distance
	 * @param motorRatio
	 */
    public DriveStraightDistance(double distance, double motorRatio) 
    {
        requires(Subsystems.transmission);
        requires(Subsystems.encoders);
        
        this.distance = distance;
        this.motorRatio = motorRatio;
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	//We drive a certain distance from the starting point.
    	//Start at Zero.
    	Subsystems.encoders.resetAll();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	double leftSpeed = this.motorRatio;
    	double rightSpeed = this.motorRatio;
    	
    	
    	if(Subsystems.encoders.getLeftDistance() > Subsystems.encoders.getRightDistance())
    	//the left is ahead of the right.
    	{
    		if(this.motorRatio > 0)
    		//we are going forwards
    		{
    			//slow down the left;
    			leftSpeed *= correctionFactor;
    		}
    		else if(this.motorRatio < 0)
    		//we are going backwards. The right is the one going too fast.
    		{
    			//slow down the right.
    			rightSpeed *= correctionFactor;
    		}
    	}
    	else if(Subsystems.encoders.getRightDistance() > Subsystems.encoders.getLeftDistance())
    	//the right is ahead of the left.
    	{
    		if(this.motorRatio > 0)
    		//we are going forwards.
    		{
    			//slow down the right.
    			rightSpeed *= correctionFactor;
    		}
    		else if(this.motorRatio < 0)
    		//we are going backwards. The left is the one going too fast.
    		{
    			//slow down the left.
    			leftSpeed *= correctionFactor;
    		}
    	}
    	
    	//drive with these new speeds.
    	Subsystems.transmission.drive(rightSpeed, leftSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	if(Subsystems.encoders.getAdverageDistance() >= this.distance)
    	//We have reached the required distance.
    	{
    		return true;
    	}
    	
        return false;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	//in case other commands want to use them.
    	Subsystems.encoders.resetAll();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    public static void setCorrectionFactor(double factor)
    {
    	correctionFactor = factor;
    }
}
