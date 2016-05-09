package org.usfirst.frc.team339.robot;

import org.usfirst.frc.team339.HardwareInterfaces.MomentarySwitch;
import org.usfirst.frc.team339.robot.commands.AimAndShoot;
import org.usfirst.frc.team339.robot.commands.Shoot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI
{

public static Joystick leftDriver = new Joystick(0);
public static Joystick rightDriver = new Joystick(1);
public static Joystick leftOperator = new Joystick(2);
public static Joystick rightOperator = new Joystick(3);

public static Button fireNormal = new JoystickButton(leftOperator, 1);
public static Button driveAndFire = new JoystickButton(leftOperator, 8);

	public OI()
	{
		fireNormal.whenPressed(new Shoot());
		driveAndFire.whenPressed(new AimAndShoot());
	}

}

