package org.usfirst.frc.team339.robot.subsystems;

import org.usfirst.frc.team339.robot.commands.CatapultDefaultCommand;
import org.usfirst.frc.team339.robotmap.Hardware;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *	A system to raise and lower the Catapult.
 */
public class Catapult extends Subsystem
{

	/**
	 * Fire the Catapult.
	 */
	public void fire ()
	{
		//RELEASE THE KRACKEN! I mean, the pressurised air...
		Hardware.catapultSolenoid0.set(true);
		Hardware.catapultSolenoid1.set(true);
		Hardware.catapultSolenoid2.set(true);
	}

	/**
	 * Allow the Catapult to lower.
	 */
	public void lower ()
	{
		Hardware.catapultSolenoid0.set(false);
		Hardware.catapultSolenoid1.set(false);
		Hardware.catapultSolenoid2.set(false);
	}

	public void initDefaultCommand ()
	{
		// Set the default command for a subsystem here.
		setDefaultCommand(new CatapultDefaultCommand());
	}
}

