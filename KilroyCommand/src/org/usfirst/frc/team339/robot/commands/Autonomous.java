package org.usfirst.frc.team339.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Autonomous extends CommandGroup {
    
    public  Autonomous(int lane) 
    {
    	
    	//Drive to the edge of the Outer Works.
        addSequential(new DriveStraightDistance(DriveInformation.DISTANCE_TO_OUTER_WORKS, DriveInformation.MOTOR_RATIO_TO_OUTER_WORKS[lane]));
        
        if(lane == 1 || lane == 6)
        {
        	//Waits for the arm to be low enough to get under the low bar.
        	addSequential(new WaitUntilArmIsDown());
        }
        
        //Drive over the Outer Works.
        addSequential(new DriveStraightDistance(DriveInformation.DISTANCE_OVER_OUTER_WORKS, DriveInformation.DRIVE_OVER_OUTER_WORKS_MOTOR_RATIOS[lane]));
        
        //Drive to the Alignment Line.
        addSequential(new DriveStraightDistance(DriveInformation.DISTANCE_TO_TAPE, DriveInformation.MOTOR_RATIO_TO_A_LINE[lane]));
        
        //Put up the camera, while we are at it.
        addParallel(new PutUpCamera());
        
        //Move the center of the robot to the Alignment Line.
        addSequential(new DriveStraightDistance(DriveInformation.DISTANCE_TO_CENTRE_OF_ROBOT, DriveInformation.MOTOR_RATIO_TO_A_LINE[lane]));
        
        //Rotate on the Alignment Line if necessary to face the next point.
        addSequential(new TurnAngleInCircle(DriveInformation.ROTATE_ON_ALIGNMENT_LINE_DISTANCE[lane], 0.0, DriveInformation.DEFAULT_TURN_SPEED * (DriveInformation.ROTATE_ON_ALIGNMENT_LINE_DISTANCE[lane]/Math.abs(DriveInformation.ROTATE_ON_ALIGNMENT_LINE_DISTANCE[lane])), false));
        
        //Go forth from the Alignment Line towards the line perpendicular to the goal.
        addSequential(new DriveStraightDistance(DriveInformation.FORWARDS_FROM_ALIGNMENT_LINE_DISTANCE[lane],DriveInformation.FORWARDS_FROM_ALIGNMENT_LINE_MOTOR_RATIO[lane]));
        
        //Turn to face the goal.
        addSequential(new TurnAngleInCircle(DriveInformation.TURN_TO_FACE_GOAL_DEGREES[lane], 0.0, DriveInformation.DEFAULT_TURN_SPEED * (DriveInformation.TURN_TO_FACE_GOAL_DEGREES[lane]/Math.abs(DriveInformation.TURN_TO_FACE_GOAL_DEGREES[lane])), false));
        
        //Drive up to the goal.
        addSequential(new DriveStraightDistance(DriveInformation.DRIVE_UP_TO_GOAL[lane],DriveInformation.DRIVE_UP_TO_GOAL_MOTOR_RATIO[lane]));
        
        //Correct any error with vision processing and make the shot.
        addSequential(new AimAndShoot());
    }
    
    /**
	 * Contains distances and speeds at which to drive.
	 * 
	 * Note that many of these are arrays.
	 * This is usually to determine different speeds and distances for each
	 * lane.
	 * Such arrays will follow the following format:
	 * 
	 * </p>
	 * [0]: placeholder, so that the index will match up with the lane number
	 * </p>
	 * [1]: lane 1
	 * </p>
	 * [2]: lane 2
	 * </p>
	 * [3]: lane 3
	 * </p>
	 * [4]: lane 4
	 * </p>
	 * [5]: lane 5
	 * </p>
	 * [6]: "lane 6" -- not a real lane, but can serve as an alternate strategy.
	 */
	private static final class DriveInformation
	{

		/**
		 * A set of motor ratios that will be used in succession
		 * when accelerating from zero.
		 * 
		 * Index here does not correspond to lane, but rather order of
		 * execution.
		 */
		private static final double[] ACCELERATION_RATIOS =
		        {
		                0.1,
		                0.2,
		                0.3
		        };

		/**
		 * The time at which each acceleration stage will end.
		 * 
		 * Index here does not correspond to lane, but rather order of
		 * execution.
		 */
		private static final double[] ACCELERATION_TIMES =
		        {
		                0.2,
		                0.4,
		                0.6
		        };

		/**
		 * The speed at which we want to go over the outer works, for each lane.
		 * note that it is to be low for lane 1, yet high for the others,
		 * so as to accommodate more difficult obstacles.
		 */
		private static final double[] DRIVE_OVER_OUTER_WORKS_MOTOR_RATIOS =
		        {
		                0.0,
		                0.5,//not much is required for the low bar.
		                0.5,
		                0.8,
		                0.5,
		                0.5,
		                0.4
		        };

		private static final boolean[] BRING_ARM_DOWN_BEFORE_DEFENSES =
		        {
		                false, // a placeholder, allowing lane to line up with index
		                true,  // lane 1 - bring arm down
		                false, // lane 2
		                false, // lane 3
		                false, // lane 4
		                false, // lane 5
		                true   // lane 6 - bring arm down
		        };
		/**
		 * For each lane, decides whether or not to break on the Alignment Line
		 */
		private static final boolean[] BREAK_ON_ALIGNMENT_LINE =
		        {
		                false, // A placeholder, allowing lane to line up with index.
		                false, //
		                false, //
		                true, // true in lanes 3 and 4, 
		                true, // because we mean to turn next.
		                false, // 
		                true //or go backwards.
		        };

		private static final boolean[] SKIP_TO_DRIVE_BY_CAMERA =
		        {
		                false,
		                false,
		                false,
		                false,
		                true, //lane 4.
		                false,
		                false
		        };

		/**
		 * The motor controller values for moving to the outer works.
		 * As these are initial speeds, keep them low, to go easy on the motors.
		 * Lane is indicated by index.
		 */
		private static final double[] MOTOR_RATIO_TO_OUTER_WORKS =
		        {
		                0.0, // nothing. Not used. Arbitrary; makes it work.
		                0.50,//0.25, // lane 1, should be extra low.
		                1.0, // lane 2
		                0.5, // lane 3
		                0.5, // lane 4
		                0.5, // lane 5
		                0.5 //backup plan
		        };

		/**
		 * "Speeds" at which to drive from the Outer Works to the Alignment
		 * Line.
		 */
		private static final double[] MOTOR_RATIO_TO_A_LINE =
		        {
		                0.0, //PLACEHOLDER
		                0.5, //lane 1
		                0.5, //lane 2
		                0.4, //lane 3
		                0.4, //lane 4
		                0.5,  //lane 5
		                0.0 //backup plan
		        };

		/**
		 * Distances to rotate upon reaching alignment line.
		 * Lane is indicated by index.
		 * Set to Zero for 1, 2, and 5.
		 */
		private static final double[] ROTATE_ON_ALIGNMENT_LINE_DISTANCE =
		        {
		                0.0, // nothing. Not used. Arbitrary; makes it work.
		                0.0, // lane 1 (not neccesary)
		                0.0, // lane 2 (not neccesary)
		                -72.85,//-20, // lane 3
		                64.24,//24.8, // lane 4
		                0.0, // lane 5 (not neccesary)
		                0.0 //backup plan
		        };

		/**
		 * Distances to drive after reaching alignment tape.
		 * Lane is indicated by index.
		 * 16 inchworms added inevitably, to start from centre of robot.
		 */
		private static final double[] FORWARDS_FROM_ALIGNMENT_LINE_DISTANCE =
		        {
		                0.0, // nothing. Not used. Arbitrary; makes it work.
		                36.57,// lane 1 //48.57
		                77.44,//68.0,// lane 2
		                66.14,//64.0, // lane 3
		                63.2,//66.1,// lane 4
		                85.8, // lane 5
		                -169.0 //backup plan
		        };

		/**
		 * Speed when we move the centre of the robot to the Alignment line.,
		 */
		private static final double[] CENTRE_TO_ALIGNMENT_LINE_MOTOR_RATIO =
		        {
		                0.0,
		                0.5, //1
		                0.5, //2
		                .25, //3
		                .25, //4
		                0.5, //5
		                0.25 //backup plan
		        };

		/**
		 * "Speeds" at which to drive from the A-Line to the imaginary line
		 * normal to
		 * the goal.
		 */
		private static final double[] FORWARDS_FROM_ALIGNMENT_LINE_MOTOR_RATIO =
		        {
		                0.0, // nothing. A Placeholder.
		                0.5, //lane 1
		                0.5, //lane 2
		                0.7, //lane 3
		                0.7, //lane 4
		                0.5,  //lane 5
		                -0.5 //backup plan
		        };

		/**
		 * Distances to rotate to face goal.
		 */
		private static final double[] TURN_TO_FACE_GOAL_DEGREES =
		        {
		                0.0, // makes it so the indexes line up with the lane #
		                -60.0,// lane 1  //-60
		                -60.0,// lane 2
		                72.85,//20.0,// lane 3
		                -64.24,//-24.85,// lane 4
		                60, // lane 5
		                -90.0 //backup plan
		        };


		/**
		 * Distances to travel once facing the goal.
		 * Not neccesary for lanes 3 and 4; set to zero.
		 * Actually, we may not use this much at all, given that we will
		 * probably just
		 * use the IR to sense the cleets at the bottom of the tower.
		 */
		private static final double[] DRIVE_UP_TO_GOAL =
		        {
		                0.0, // nothing. Not used. Arbitrary; makes it work.
		                53.85,//previously 62.7//65.85,// lane 1
		                18.1,//52.9,// lane 2
		                0.0,// lane 3 (not neccesary)
		                0.0,// lane 4 (not neccesary)
		                32.8,//12.0, // lane 5
		                0.0 //backup plan
		        };

		/**
		 * "Speeds" at which to drive to the Batter.
		 */
		private static final double[] DRIVE_UP_TO_GOAL_MOTOR_RATIO =
		        {
		                0.0,
		                0.55,
		                0.5,
		                0.5,
		                0.5,
		                0.5,
		                0.0
		        };

		/**
		 * Time to delay at A-line. Only used if reversing.
		 */
		private static final double[] DELAY_IF_REVERSE =
		        {
		                0.0,
		                0.0,
		                0.0,
		                0.0,
		                0.0,
		                0.0,
		                1.0//only used in "lane 6."
		        };

		/**
		 * Should we want to stop upon going over the outer works,
		 * this should add extra distance, for assurance.
		 */
		private static final double[] ADDED_DISTANCE_FROM_OW =
		        {
		                0.0,
		                0.0,
		                0.0,
		                48.0,// Further driving is disabled in this lane, 
		                // so this is to be sure we are all the way over.
		                60.0,
		                0.0,
		                0.0
		        };

		/**
		 * Distance from Outer Works checkpoint to Alignment Line.
		 * The front of the robot will be touching the Lion.
		 */
		private static final double DISTANCE_TO_TAPE = 27.5;


		/**
		 * Distance to get the front of the robot to the Outer Works.
		 */
		private static final double DISTANCE_TO_OUTER_WORKS = 16.75; //prev. 22.75;

		/**
		 * Distance to travel to get over the Outer Works.
		 */
		private static final double DISTANCE_OVER_OUTER_WORKS = 104.86; //prev. 98.86;

		/**
		 * The distance to the central pivot point from the front of the robot.
		 * We will use this so that we may rotate around a desired point at the
		 * end of a
		 * distance.
		 */
		private static final double DISTANCE_TO_CENTRE_OF_ROBOT = 16.0;

		/**
		 * Speed at which to make turns by default.
		 */
		private static final double DEFAULT_TURN_SPEED = 0.55;

		/**
		 * Speed at which we may align by camera.
		 */
		private static final double ALIGNMENT_SPEED = 0.5;
	}
}
