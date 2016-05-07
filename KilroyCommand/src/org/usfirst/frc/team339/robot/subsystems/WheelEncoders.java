package org.usfirst.frc.team339.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class WheelEncoders extends Subsystem {
    
	private Encoder leftFront;
	private Encoder leftRear;
	private Encoder rightFront;
	private Encoder rightRear;
	
	public WheelEncoders(Encoder left, Encoder right)
	{
		this.leftFront = left;
		this.leftRear = this.leftFront;
		this.rightFront = right;
		this.rightRear = this.rightFront;
	}
	
	public WheelEncoders(Encoder leftFront, Encoder leftRear, Encoder rightFront, Encoder rightRear)
	{
		this.leftFront = leftFront;
		this.leftRear = leftRear;
		this.rightFront = rightFront;
		this.rightRear = rightRear;
	}
	
	public void resetAll()
	{
		this.leftFront.reset();
		this.leftRear.reset();
		this.rightFront.reset();
		this.rightRear.reset();
	}
	
	public double getLeftDistance()
	{
		return (leftFront.getDistance()+leftRear.getDistance())/2;
	}

	public double getRightDistance()
	{
		return (rightFront.getDistance()+rightRear.getDistance())/2;
	}
	
	public double getAdverageDistance()
	{
		return (getLeftDistance() + getRightDistance())/2;
	}
	
	public void setDistancePerTick(double ratio)
	{
		this.leftFront.setDistancePerPulse(ratio);
		this.leftRear.setDistancePerPulse(ratio);
		this.rightFront.setDistancePerPulse(ratio);
		this.rightRear.setDistancePerPulse(ratio);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

