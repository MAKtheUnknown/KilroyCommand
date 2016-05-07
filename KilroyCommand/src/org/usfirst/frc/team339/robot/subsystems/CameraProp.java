package org.usfirst.frc.team339.robot.subsystems;

import org.usfirst.frc.team339.robotmap.Hardware;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CameraProp extends Subsystem {
    
	public static enum PropPosition
	{
		UP,
		DOWN
	}
	
	private PropPosition currentState;
	
    public void toggleCamera()
    {
    	if(Hardware.cameraSolenoid.get() == DoubleSolenoid.Value.kForward)
    	{
    		Hardware.ringLightRelay.set(Relay.Value.kReverse);
    		Hardware.cameraSolenoid.set(Value.kReverse);
    	}
    	else
    	{
    		Hardware.ringLightRelay.set(Relay.Value.kForward);
    		Hardware.cameraSolenoid.set(Value.kForward);
    	}
    }
    
    
    public void setPosition(PropPosition pos)
    {
    	if(pos == PropPosition.UP)
    	{
    		Hardware.ringLightRelay.set(Relay.Value.kForward);
    		Hardware.cameraSolenoid.set(Value.kForward);
    	}
    	else
    	{
    		Hardware.ringLightRelay.set(Relay.Value.kReverse);
    		Hardware.cameraSolenoid.set(Value.kReverse);
    	}
    }

    public PropPosition getPosition()
    {
    	return currentState;
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

