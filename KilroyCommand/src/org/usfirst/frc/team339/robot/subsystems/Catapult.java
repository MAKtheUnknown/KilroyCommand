package org.usfirst.frc.team339.robot.subsystems;

import org.usfirst.frc.team339.robotmap.Hardware;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Catapult extends Subsystem
{

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public void fire ()
	{
		//RELEASE THE KRACKEN! I mean, the pressurised air...
		Hardware.catapultSolenoid0.set(true);
		Hardware.catapultSolenoid1.set(true);
		Hardware.catapultSolenoid2.set(true);
	}

	public void lower ()
	{
		Hardware.catapultSolenoid0.set(false);
		Hardware.catapultSolenoid1.set(false);
		Hardware.catapultSolenoid2.set(false);
	}

	public void initDefaultCommand ()
	{
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}
}

