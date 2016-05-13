package org.usfirst.frc.team339.robot.triggers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class JoystickButtonCombination extends Trigger {
    
	JoystickButton[] combination;
	
	public JoystickButtonCombination(JoystickButton[] combination)
	{
		this.combination = combination;
	}
	
    public boolean get() 
    {
    	for(JoystickButton j : combination)
    	{
    		if(j.get() == false)
    		{
    			return false;
    		}
    	}
    	return true;
    }
}
