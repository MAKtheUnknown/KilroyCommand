package org.usfirst.frc.team339.robot.triggers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 *
 */
public class ButtonCombination extends Trigger {
    
	Button[] combination;
	
	public ButtonCombination(Button[] fireOverrideCombo)
	{
		this.combination = fireOverrideCombo;
	}
	
    public boolean get() 
    {
    	for(Button j : combination)
    	{
    		if(j.get() == false)
    		{
    			return false;
    		}
    	}
    	return true;
    }
}
