package org.usfirst.frc.team339.robot.subsystems;

import org.usfirst.frc.team339.HardwareInterfaces.RobotPotentiometer;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem
{

SpeedController motor;
RobotPotentiometer angleSensor;

public Arm (SpeedController motor, RobotPotentiometer pot)
{
    this.motor = motor;
    this.angleSensor = pot;
}

// Put methods for controlling this subsystem
// here. Call these from Commands.
public void move (double speed)
{
    if (Math.abs(speed) <= 1)
        {
        if (angleSensor.get() > MIN && angleSensor.get() < MAX)
            {
            motor.set(speed);
            }
        }
}


/**
 * 
 * @return angle of the arm.
 */
public double getPosition ()
{
    return angleSensor.get();
}

public void initDefaultCommand ()
{
    // Set the default command for a subsystem here.
    //setDefaultCommand(new MySpecialCommand());
}

public final double MIN = 85;
public final double MAX = 245;
}

