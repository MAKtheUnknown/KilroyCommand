package org.usfirst.frc.team339.robotmap;

import org.usfirst.frc.team339.HardwareInterfaces.transmission.TransmissionFourWheel;
import org.usfirst.frc.team339.robot.subsystems.Arm;
import org.usfirst.frc.team339.robot.subsystems.CameraProp;
import org.usfirst.frc.team339.robot.subsystems.Catapult;
import org.usfirst.frc.team339.robot.subsystems.Intake;
import org.usfirst.frc.team339.robot.subsystems.WheelEncoders;

public class Subsystems
{

	public static Catapult catapult = new Catapult();
	public static Arm intakeArm =
        new Arm(Hardware.armMotor, Hardware.armPot);
	public static Intake ballIntake =
        new Intake(Hardware.armIntakeMotor, Hardware.armIR);
	public static TransmissionFourWheel transmission = new TransmissionFourWheel(Hardware.rightRearMotor, Hardware.rightFrontMotor, Hardware.leftRearMotor, Hardware.leftFrontMotor);;
	public static WheelEncoders encoders = new WheelEncoders(Hardware.leftRearEncoder, Hardware.rightRearEncoder);
	public static CameraProp cameraProp = new CameraProp();
}
