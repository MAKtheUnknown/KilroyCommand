package org.usfirst.frc.team339.robotmap;

import org.usfirst.frc.team339.HardwareInterfaces.DoubleThrowSwitch;
import org.usfirst.frc.team339.HardwareInterfaces.IRSensor;
import org.usfirst.frc.team339.HardwareInterfaces.KilroyCamera;
import org.usfirst.frc.team339.HardwareInterfaces.RobotPotentiometer;
import org.usfirst.frc.team339.HardwareInterfaces.SingleThrowSwitch;
import org.usfirst.frc.team339.HardwareInterfaces.SixPositionSwitch;
import org.usfirst.frc.team339.HardwareInterfaces.UltraSonic;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.vision.AxisCamera;
import edu.wpi.first.wpilibj.vision.AxisCamera.Resolution;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class Hardware
{


/*------------------------
 * Public Constants
 *-----------------------*/

public static final int DELAY_POT_DEGREES = 270;
public static final int TRANSDUCER_MAX_VALUE = 200;
public static final int ARM_POT_MAX_VALUE = 270;// 360 is a placeholder

// The amount of time the camera is delayed for picture taking, so the light
// isn't too bright.
public static final double CAMERA_DELAY_TIME = .25;

// Makes the brightness to a visible level so our drivers can see.
public static final int NORMAL_AXIS_CAMERA_BRIGHTNESS = 60;

// Crazy dark brightness for retroreflective pictures
public static final int MINIMUM_AXIS_CAMERA_BRIGHTNESS = 6;

public static final int AXIS_FPS = 15;


public static final Resolution AXIS_RESOLUTION =
        AxisCamera.Resolution.k320x240;

public static final double ULTRASONIC_SCALING_FACTOR = 0.050548;

/*------------------------
 * Motor Controllers
 *-----------------------*/
public static TalonSRX leftFrontMotor = new TalonSRX(1);
public static TalonSRX rightFrontMotor = new TalonSRX(4);
public static TalonSRX leftRearMotor = new TalonSRX(3);
public static TalonSRX rightRearMotor = new TalonSRX(2);

public static VictorSP armMotor = new VictorSP(0);
public static VictorSP armIntakeMotor = new VictorSP(5);

/*------------------------
 * Encoders
 *-----------------------*/
//-----------------------
// Wiring diagram
// -----------------------
// Orange - Red PWM 1
// Yellow - White PWM 1 Signal
// Brown - Black PWM 1 (or PWM 2)
// Blue - White PWM 2 Signal
// For the AMT103 Encoders UNVERIFIED
// B - White PWM 2
// 5V - Red PWM 1 or 2
// A - White PWM 1
// X - index channel, unused
// G - Black PWM 1 or 2
// see http://www.cui.com/product/resource/amt10-v.pdf page 4 for Resolution
// (DIP Switch) Settings (currently all are off)

public static Encoder leftRearEncoder = new Encoder(5, 6);
public static Encoder rightRearEncoder = new Encoder(3, 4);

/*------------------------
 * Light Sensors
 *-----------------------*/
public static IRSensor rightIR = new IRSensor(1);
public static IRSensor leftIR = new IRSensor(0);
public static IRSensor armIR = new IRSensor(2);


/*------------------------
 * Relays
 *-----------------------*/

public static Relay ringLightRelay = new Relay(0);

//------------------------------------
// Compressor class - runs the compressor
// with a single relay
// ------------------------------------
public static Compressor compressor = new Compressor();

/*------------------------
 * Switches
 *-----------------------*/
public static SingleThrowSwitch autonomousEnabled =
        new SingleThrowSwitch(
                19);
public static SingleThrowSwitch shootHigh =
        new SingleThrowSwitch(8);
public static SingleThrowSwitch shootLow = new SingleThrowSwitch(7);
// Shoot high/low switch
public static DoubleThrowSwitch noShoot = new DoubleThrowSwitch(
        shootHigh, shootLow);

/**
 * Displays the starting position.
 * Position 0 on the switch corresponds to position 1, 1 to 2, etc.
 */
public static SixPositionSwitch startingPositionDial =
        new SixPositionSwitch(
                14, 15, 16, 17, 18, 21, true);

/*------------------------
 * Solenoids
 *-----------------------*/
public static Solenoid catapultSolenoid0 = new Solenoid(0);
public static Solenoid catapultSolenoid1 = new Solenoid(1);
public static Solenoid catapultSolenoid2 = new Solenoid(2);

public static DoubleSolenoid cameraSolenoid =
        new DoubleSolenoid(3, 4);

/*------------------------
 * Potentiometers
 *-----------------------*/
public static RobotPotentiometer delayPot =
        new RobotPotentiometer(1,
                DELAY_POT_DEGREES);
// transducer (written as a potentiometer)
// set to 50 to hit 100 psi accurately
public static RobotPotentiometer transducer =
        new RobotPotentiometer(2,
                TRANSDUCER_MAX_VALUE);
// to be used with the manipulator arm
public static RobotPotentiometer armPot = new RobotPotentiometer(3,
        ARM_POT_MAX_VALUE);

/*------------------------
 * Ultrasonics
 *-----------------------*/
public static UltraSonic ultrasonic = new UltraSonic(4,
        ULTRASONIC_SCALING_FACTOR);

/*------------------------
 * Cameras
 *-----------------------*/
public static KilroyCamera axisCamera = new KilroyCamera(true);

/*------------------------
 * Driver Station
 *-----------------------*/
public static final DriverStation driverStation = DriverStation
        .getInstance();


}
//getHeather(someFood);
