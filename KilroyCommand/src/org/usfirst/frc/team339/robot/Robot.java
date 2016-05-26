
package org.usfirst.frc.team339.robot;

import org.usfirst.frc.team339.robot.commands.Autonomous;
import org.usfirst.frc.team339.robot.commands.DriverControl;
import org.usfirst.frc.team339.robotmap.Hardware;
import org.usfirst.frc.team339.robotmap.Subsystems;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot
{

	public Autonomous autonomous;
	public DriverControl teleop;
	public static OI oi;


	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit ()
	{
		oi = new OI();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit ()
	{
		if (autonomous != null)
		{
			autonomous.cancel();
		}
		if (teleop != null)
		{
			teleop.cancel();
		}

	}

	public void disabledPeriodic ()
	{
		//Scheduler.getInstance().run();
	}

	/**
	 * Start autonomous.
	 * Passes in the value of the six-position switch for a starting position.
	 */
	public void autonomousInit ()
	{
		int lane = Hardware.startingPositionDial.getPosition() + 1;
		this.autonomous = new Autonomous(lane);
		this.autonomous.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic ()
	{
		Scheduler.getInstance().run();
	}

	public void teleopInit ()
	{
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to 
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomous != null)
		{
			autonomous.cancel();
		}

		//Set the default command for the transmission to DriverControl,
		//so that it will return to this whenever it is interrupted.
		Subsystems.transmission.setCommandDefault(new DriverControl());

		teleop = new DriverControl();
		teleop.start();

	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic ()
	{
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic ()
	{
		LiveWindow.run();
	}
}
