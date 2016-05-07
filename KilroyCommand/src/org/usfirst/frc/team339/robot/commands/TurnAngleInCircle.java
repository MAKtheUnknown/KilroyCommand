package org.usfirst.frc.team339.robot.commands;

import org.usfirst.frc.team339.robotmap.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnAngleInCircle extends Command {

	private static double wheelDiameter;
	
	private static double correctionFactor = 0.75;
	
	private boolean turningRight;
	
	private double angle;
	private double radius;
	
	private double outsideFinalDistance;
	private double insideFinalDistance;
	private double insideBaseMotorRatio;
	private double outsideBaseMotorRatio;
	
    public TurnAngleInCircle(double angle, double radius, double baseMotorRatio, boolean turnRight)
    {
        requires(Subsystems.transmission);
        requires(Subsystems.encoders);
        
        this.angle = angle;
        this.radius = radius;
        this.outsideBaseMotorRatio = baseMotorRatio;
        
        this.turningRight = turnRight;
        
        this.outsideFinalDistance = Math.toRadians(this.angle) * (this.radius + (this.wheelDiameter/2));
		this.insideFinalDistance = Math.toRadians(this.angle) * (this.radius - (this.wheelDiameter/2));
    	
		this.insideBaseMotorRatio = this.outsideBaseMotorRatio * (this.insideFinalDistance / this.outsideFinalDistance);
        
        wheelDiameter = DEFAULT_WHEEL_DIAMETER;
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	Subsystems.encoders.resetAll();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	double leftMotorRatio;
    	double rightMotorRatio;
    	
    	if(this.turningRight = false)
    	{
    		rightMotorRatio = this.outsideBaseMotorRatio;
    		leftMotorRatio = this.insideBaseMotorRatio;
    		
    		if(this.angle > 0)
    		{
    			if(Subsystems.encoders.getLeftDistance() > (leftMotorRatio/rightMotorRatio) * Subsystems.encoders.getRightDistance())
    			{
    				leftMotorRatio *= correctionFactor;
    			}
    			else if (Subsystems.encoders.getLeftDistance() < (leftMotorRatio/rightMotorRatio) * Subsystems.encoders.getRightDistance())
    			{
    				rightMotorRatio *= correctionFactor;
    			}
    		}
    		else if(this.angle < 0)
    		{
    			if(Subsystems.encoders.getLeftDistance() < (leftMotorRatio/rightMotorRatio) * Subsystems.encoders.getRightDistance())
    			{
    				leftMotorRatio *= correctionFactor;
    			}
    			else if (Subsystems.encoders.getLeftDistance() > (leftMotorRatio/rightMotorRatio) * Subsystems.encoders.getRightDistance())
    			{
    				rightMotorRatio *= correctionFactor;
    			}
    		}
    	}
    	else
    	{
    		leftMotorRatio = this.outsideBaseMotorRatio;
    		rightMotorRatio = this.insideBaseMotorRatio;
    		
    		if(this.angle > 0)
    		{
    			if(Subsystems.encoders.getRightDistance() > (rightMotorRatio/leftMotorRatio) * Subsystems.encoders.getLeftDistance())
    			{
    				rightMotorRatio *= correctionFactor;
    			}
    			else if (Subsystems.encoders.getRightDistance() < (rightMotorRatio/leftMotorRatio) * Subsystems.encoders.getLeftDistance())
        			{
    				leftMotorRatio *= correctionFactor;
    			}
    		}
    		else if(this.angle < 0)
    		{
    			if(Subsystems.encoders.getRightDistance() < (rightMotorRatio/leftMotorRatio) * Subsystems.encoders.getLeftDistance())
    			{
    				rightMotorRatio *= correctionFactor;
    			}
    			else if (Subsystems.encoders.getRightDistance() > (rightMotorRatio/leftMotorRatio) * Subsystems.encoders.getLeftDistance())
        			{
    				leftMotorRatio *= correctionFactor;
    			}
    		}
    	}
    	
    	
    	Subsystems.transmission.drive(leftMotorRatio, rightMotorRatio);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
    	if(turningRight == false)
    	{
    		if(angle > 0)
    		{
    			if(2*(Subsystems.encoders.getRightDistance() - Subsystems.encoders.getLeftDistance())/this.wheelDiameter >= angle)
    			{
    				return true;
    			}
    		}
    		else if(angle < 0)
    		{
    			if(2*(Subsystems.encoders.getRightDistance() - Subsystems.encoders.getLeftDistance())/this.wheelDiameter <= angle)
    			{
    				return true;
    			}
    		}
    		else
    		{
    			return true;
    		}
    	}
    	else
    	{
    		if(angle > 0)
    		{
    			if(2*(Subsystems.encoders.getLeftDistance() - Subsystems.encoders.getRightDistance())/this.wheelDiameter >= angle)
    			{
    				return true;
    			}
    		}
    		else if(angle < 0)
    		{
    			if(2*(Subsystems.encoders.getLeftDistance() - Subsystems.encoders.getRightDistance())/this.wheelDiameter <= angle)
    			{
    				return true;
    			}
    		}
    		else
    		{
    			return true;
    		}
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
    
    private static final double DEFAULT_WHEEL_DIAMETER = 16.0;
}
