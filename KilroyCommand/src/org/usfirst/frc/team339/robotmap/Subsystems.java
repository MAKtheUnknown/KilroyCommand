package org.usfirst.frc.team339.robotmap;

import org.usfirst.frc.team339.robot.subsystems.CameraProp;
import org.usfirst.frc.team339.robot.subsystems.Catapult;
import org.usfirst.frc.team339.robot.subsystems.GoalVision;
import org.usfirst.frc.team339.robot.subsystems.ManipulatorArm;
import org.usfirst.frc.team339.robot.subsystems.WheelEncoders;
import org.usfirst.frc.team339.robot.subsystems.transmission.TransmissionFourWheel;

public class Subsystems
{

/**
 * The pneumatic ball-shooter.
 */
public static Catapult catapult = new Catapult();

/**
 * The swinging arm with the ball intake mechanism.
 */
public static ManipulatorArm intakeArm =
        new ManipulatorArm(Hardware.armMotor, Hardware.armIntakeMotor,
                Hardware.armPot, Hardware.armIR);

/**
 * The driving mechanism.
 */
public static TransmissionFourWheel transmission =
        new TransmissionFourWheel(Hardware.rightRearMotor,
                Hardware.rightFrontMotor, Hardware.leftRearMotor,
                Hardware.leftFrontMotor);;

/**
 * The set of encoders that give the wheel positions.
 */
public static WheelEncoders encoders = new WheelEncoders(
        Hardware.leftRearEncoder, Hardware.rightRearEncoder);

/**
 * The up/down function of the camera.
 */
public static CameraProp cameraProp = new CameraProp();

/**
 * The vision processing that detects the position of the goal.
 */
public static GoalVision goalVision =
        new GoalVision(Hardware.goalVision, Hardware.axisCamera);
}
