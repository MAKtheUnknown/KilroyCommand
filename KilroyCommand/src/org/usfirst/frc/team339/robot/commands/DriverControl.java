package org.usfirst.frc.team339.robot.commands;

import org.usfirst.frc.team339.Hardware.Hardware;
import org.usfirst.frc.team339.robot.OI;
import org.usfirst.frc.team339.robotmap.Subsystems;

import edu.wpi.first.wpilibj.command.Command;

/**
 *	Executes regular manual Teleoperated operation.
 *	(Driving, gear shifts, etc.)
 */
public class DriverControl extends Command {



	
	public DriverControl() 
    {    
    	requires(Subsystems.transmission);
    	requires(Subsystems.intakeArm);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	Subsystems.transmission.setLeftJoystickReversed(false);
    	Subsystems.transmission.setRightJoystickReversed(false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	boolean armOverride = false;
    	
    	
    	Subsystems.transmission.drive(OI.rightDriver.getY(), OI.leftDriver.getY());
    	
		// If we're pressing the upshift button, shift up.
		if (OI.rightDriver
		        .getRawButton(GEAR_UPSHIFT_JOYSTICK_BUTTON) == true)
		{
			Subsystems.transmission.upshift();
		}
		// If we press the downshift button, shift down.
		if (OI.rightDriver
		        .getRawButton(GEAR_DOWNSHIFT_JOYSTICK_BUTTON) == true)
		{
			Subsystems.transmission.downshift();
		}
		
		// Use override while override button is pressed.
		if(OI.rightOperator.getRawButton(ARM_OVERRIDE_BUTTON) == true)
		{
			armOverride = true;
		}
		//manually move the arm.
    	Subsystems.intakeArm.moveReasonably((int)OI.rightOperator.getY(), armOverride);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    private static final int GEAR_UPSHIFT_JOYSTICK_BUTTON = 3;
	private static final int GEAR_DOWNSHIFT_JOYSTICK_BUTTON = 2;
	private static final int ARM_OVERRIDE_BUTTON = 4;
}
