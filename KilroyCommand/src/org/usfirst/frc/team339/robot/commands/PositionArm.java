package org.usfirst.frc.team339.robot.commands;

import org.usfirst.frc.team339.robotmap.Subsystems;
import edu.wpi.first.wpilibj.command.Command;

/**
 *	Puts arm in a given position at a given speed.
 */
public class PositionArm extends Command
{


private double targetPosition;
private double speed;

public PositionArm (double position, double speed)
{

    requires(Subsystems.intakeArm);

    this.targetPosition = position;
    this.speed = speed;
}

// Called just before this Command runs the first time
protected void initialize ()
{
}

// Called repeatedly when this Command is scheduled to run
protected void execute ()
{
    double sign =
            (targetPosition - Subsystems.intakeArm.getPosition()) / Math.abs(
                    (targetPosition - Subsystems.intakeArm.getPosition()));
    Subsystems.intakeArm.move(speed * sign);
}

// Make this return true when this Command no longer needs to run execute()
protected boolean isFinished ()
{
    if (Subsystems.intakeArm.getPosition() > targetPosition
            - SET_ANGLE_THRESHOLD
            && Subsystems.intakeArm.getPosition() < targetPosition
                    + SET_ANGLE_THRESHOLD)
        {
        return true;
        }
    return false;
}

// Called once after isFinished returns true
protected void end ()
{
    Subsystems.intakeArm.move(0.0);
}

// Called when another command which requires one or more of the same
// subsystems is scheduled to run
protected void interrupted ()
{
}

public final double SET_ANGLE_THRESHOLD = 5.0;

}
