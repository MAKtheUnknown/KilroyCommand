package org.usfirst.frc.team339.robot;

import org.usfirst.frc.team339.HardwareInterfaces.MomentarySwitch;
import edu.wpi.first.wpilibj.Joystick;

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
public static MomentarySwitch cameraToggleButton =
        new MomentarySwitch(
                leftOperator, 2, false);



}

