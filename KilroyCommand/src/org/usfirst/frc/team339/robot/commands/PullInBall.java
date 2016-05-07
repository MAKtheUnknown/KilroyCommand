package org.usfirst.frc.team339.robot.commands;

import org.usfirst.frc.team339.robotmap.Subsystems;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PullInBall extends Command
{

public PullInBall ()
{
    // Use requires() here to declare subsystem dependencies
    requires(Subsystems.ballIntake);
}

// Called just before this Command runs the first time
protected void initialize ()
{
}

// Called repeatedly when this Command is scheduled to run
protected void execute ()
{
    Subsystems.ballIntake.in();
}

// Make this return true when this Command no longer needs to run execute()
protected boolean isFinished ()
{
    if (Subsystems.ballIntake.hasBall() == false)
        {
        return true;
        }
    return false;
}

// Called once after isFinished returns true
protected void end ()
{
    Subsystems.ballIntake.spin(0.0);
}

// Called when another command which requires one or more of the same
// subsystems is scheduled to run
protected void interrupted ()
{
}
}
