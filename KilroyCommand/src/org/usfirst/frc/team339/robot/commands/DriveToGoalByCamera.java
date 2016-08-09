package org.usfirst.frc.team339.robot.commands;

import org.usfirst.frc.team339.robotmap.Subsystems;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives to a shooting position, correcting for both axis.
 */
public class DriveToGoalByCamera extends Command
{

private double motorRatio;

/**
 * Drives to a shooting position, correcting for both axis.
 */
public DriveToGoalByCamera ()
{
    requires(Subsystems.transmission);
    requires(Subsystems.goalVision);

    this.motorRatio = DEFAULT_MOTOR_RATIO;
}

// Called just before this Command runs the first time
protected void initialize ()
{
    Subsystems.goalVision.processNewImage();

    // Send positive values to go forwards.
    Subsystems.transmission.setLeftJoystickReversed(true);
    Subsystems.transmission.setRightJoystickReversed(true);
}

// Called repeatedly when this Command is scheduled to run
protected void execute ()
{
    double leftMotor = 0.0;
    double rightMotor = 0.0;

    if (Subsystems.goalVision.getProportionalGoalY() < Y_OFFSET
            - Y_DEADBAND)
        {
        leftMotor = DriveToGoalByCamera.DEFAULT_MOTOR_RATIO;
        rightMotor = DriveToGoalByCamera.DEFAULT_MOTOR_RATIO;
        }
    else if (Subsystems.goalVision.getProportionalGoalY() > Y_OFFSET
            + Y_DEADBAND)
        {
        leftMotor = -DriveToGoalByCamera.DEFAULT_MOTOR_RATIO;
        rightMotor = -DriveToGoalByCamera.DEFAULT_MOTOR_RATIO;
        }

    if (Subsystems.goalVision.getProportionalGoalX() < X_OFFSET
            - X_DEADBAND)
        {
        rightMotor -= CORRECTION_ADDITIVE;
        }
    else if (Subsystems.goalVision.getProportionalGoalY() < Y_OFFSET
            - Y_DEADBAND)
        {
        leftMotor -= CORRECTION_ADDITIVE;
        }

    Subsystems.transmission.drive(rightMotor, leftMotor);
}

// Make this return true when this Command no longer needs to run execute()
protected boolean isFinished ()
{
    if ((Subsystems.goalVision.getProportionalGoalX() >= X_OFFSET
            - X_DEADBAND
            && Subsystems.goalVision.getProportionalGoalX() <= X_OFFSET
                    + X_DEADBAND)
            && (Subsystems.goalVision.getProportionalGoalY() >= Y_OFFSET
                    - Y_DEADBAND
                    && Subsystems.goalVision
                            .getProportionalGoalY() <= Y_OFFSET
                                    + Y_DEADBAND))
        {
        return true;
        }
    return false;
}

// Called once after isFinished returns true
protected void end ()
{
    // return driver control.
    Subsystems.transmission.setLeftJoystickReversed(false);
    Subsystems.transmission.setRightJoystickReversed(false);
}

// Called when another command which requires one or more of the same
// subsystems is scheduled to run
protected void interrupted ()
{
}

private static final double X_OFFSET = 0.0;
private static final double Y_OFFSET = 0.0;
private static final double X_DEADBAND = 0.0;
private static final double Y_DEADBAND = 0.0;
private static final double DEFAULT_MOTOR_RATIO = 0.55;
private static final double CORRECTION_ADDITIVE = 0.35;
}
