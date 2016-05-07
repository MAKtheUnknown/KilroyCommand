package org.usfirst.frc.team339.robot.commands;

import org.usfirst.frc.team339.robotmap.Subsystems;

import edu.wpi.first.wpilibj.command.Command;


/**
 *
 */
public class DriveStraightDistance extends Command {

	private static double correctionFactor = .75;
	
	private double distance;
	private double motorRatio;
	
	
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
    	Subsystems.encoders.resetAll();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	double leftSpeed = this.motorRatio;
    	double rightSpeed = this.motorRatio;
    	
    	if(Subsystems.encoders.getLeftDistance() > Subsystems.encoders.getRightDistance())
    	{
    		if(this.motorRatio > 0)
    		{
    			leftSpeed *= correctionFactor;
    		}
    		else if(this.motorRatio < 0)
    		{
    			rightSpeed *= correctionFactor;
    		}
    	}
    	else if(Subsystems.encoders.getRightDistance() > Subsystems.encoders.getLeftDistance())
    	{
    		if(this.motorRatio > 0)
    		{
    			rightSpeed *= correctionFactor;
    		}
    		else if(this.motorRatio < 0)
    		{
    			leftSpeed *= correctionFactor;
    		}
    	}
    	
    	Subsystems.transmission.drive(rightSpeed, leftSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	if(Subsystems.encoders.getAdverageDistance() >= this.distance)
    	{
    		return true;
    	}
    	
        return false;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
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
