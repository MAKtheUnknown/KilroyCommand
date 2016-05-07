package org.usfirst.frc.team339.robot.subsystems;

import org.usfirst.frc.team339.HardwareInterfaces.IRSensor;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem
{

SpeedController motor;
IRSensor ballSensor;

public Intake (SpeedController motor, IRSensor ballSensor)
{
    this.motor = motor;
    this.ballSensor = ballSensor;

}

// Put methods for controlling this subsystem
// here. Call these from Commands.
public void spin (double speed)
{
    if (Math.abs(speed) <= 1)
        {
        motor.set(speed);
        }
}

public void in ()
{
    motor.set(DEFAULT_SPEED);
}

public void out ()
{
    motor.set(-DEFAULT_SPEED);
}

public boolean hasBall ()
{
    return ballSensor.isOn();
}


public void initDefaultCommand ()
{
    // Set the default command for a subsystem here.
    //setDefaultCommand(new MySpecialCommand());
}

private double DEFAULT_SPEED = 1.0;

}

