package org.usfirst.frc.team339.robot.commands;

import org.usfirst.frc.team339.robotmap.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

/**
 *	Moves the robot within shooting range of the detected goal.
 */
public class ApproachByCamera extends Command {

	double motorRatio;
	
    public ApproachByCamera() 
    {
        requires(Subsystems.transmission);
        requires(Subsystems.encoders);
        requires(Subsystems.goalVision);
        
        this.motorRatio = DEFAULT_MOTOR_RATIO;
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	Subsystems.encoders.resetAll();
    	Subsystems.goalVision.processNewImage();
    	
    	//Send positive values to go forwards.
    	Subsystems.transmission.setLeftJoystickReversed(true);
    	Subsystems.transmission.setRightJoystickReversed(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	double leftMotor = DEFAULT_MOTOR_RATIO;
    	double rightMotor = DEFAULT_MOTOR_RATIO;
    	
    	if(Subsystems.goalVision.getProportionalGoalY() < Y_OFFSET - DEADBAND)
    	{
    		if(Subsystems.encoders.getLeftDistance() > Subsystems.encoders.getRightDistance())
    		{
    			leftMotor *= this.CORRECTION_FACTOR;
    		}
    		else if (Subsystems.encoders.getLeftDistance() < Subsystems.encoders.getRightDistance())
    		{
    			rightMotor *= this.CORRECTION_FACTOR;
    		}
    	}
    	else if(Subsystems.goalVision.getProportionalGoalY() > Y_OFFSET + DEADBAND)
    	{
    		leftMotor *= -1;
    		rightMotor *= -1;
    		
    		if(Subsystems.encoders.getLeftDistance() < Subsystems.encoders.getRightDistance())
    		{
    			leftMotor *= this.CORRECTION_FACTOR;
    		}
    		else if (Subsystems.encoders.getLeftDistance() > Subsystems.encoders.getRightDistance())
    		{
    			rightMotor *= this.CORRECTION_FACTOR;
    		}
    	}
    	
    	Subsystems.transmission.drive(rightMotor, leftMotor);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	if(Subsystems.goalVision.getProportionalGoalY() >= Y_OFFSET - DEADBAND
    			&& Subsystems.goalVision.getProportionalGoalY() <= Y_OFFSET + DEADBAND)
    	//The goal is centered. We are at the right distance.
    	{
    		//We are aligned.
    		return true;
    	}
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	//return Driver control.
    	Subsystems.transmission.setLeftJoystickReversed(false);
    	Subsystems.transmission.setRightJoystickReversed(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    private final double Y_OFFSET = 0.0;
    private final double DEADBAND = 0.0;
    private final double DEFAULT_MOTOR_RATIO = 5.5;
    private final double CORRECTION_FACTOR = 0.8;
}
