package org.usfirst.frc.team339.robot.subsystems;

import org.usfirst.frc.team339.HardwareInterfaces.KilroyCamera;
import org.usfirst.frc.team339.Utils.ImageProcessing;
import org.usfirst.frc.team339.Utils.ImageProcessing.ObjectRemoval;
import org.usfirst.frc.team339.Utils.ImageProcessing.ParticleReport;
import org.usfirst.frc.team339.Vision.ImageProcessor;
import org.usfirst.frc.team339.robotmap.Hardware;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.vision.AxisCamera;

/**
 *
 */
public class GoalVision extends Subsystem 
{
	
	private ImageProcessing imageProcessor;
	private KilroyCamera camera;
	private double xResolution;
	private double yResolution;
	
	private double widestBlobWidth = 0;
	private int widestBlobIndex = 0;
	private ParticleReport goalBlob;
	
    public GoalVision(ImageProcessing processor, KilroyCamera camera)
    {
    	this.imageProcessor = processor;
    	this.camera = camera;
    	
    	initCamera();
    	initProcessing();
    	
    }
    
    public void processNewImage()
    {
    	//try to take a picture and save it in memory and on the "hard disk"
		try
		{
			//if (Hardware.axisCamera.freshImage() == true)
			{
				imageProcessor.updateImage(
				        Hardware.axisCamera.getImage());

			}
		}
		//This is NI yelling at us for something being wrong
		catch (NIVisionException e)
		{
			//if something wrong happens, tell the stupid programmers 
			//who let it happen more information about where it came from
			e.printStackTrace();
		}
		//tell imageProcessor to use the image we just took to look for 
		//blobs
		imageProcessor.updateParticleAnalysisReports();
		for (int i =
		        0; i < imageProcessor
		                .getParticleAnalysisReports().length; i++)
		{
			if (imageProcessor
			        .getParticleAnalysisReports().length > 0 &&
			        this.widestBlobWidth < imageProcessor
			                .getParticleAnalysisReports()[i].boundingRectWidth)
			{	
				//set blob as our goal
				goalBlob = imageProcessor.getParticleAnalysisReports()[i];
				this.widestBlobIndex = goalBlob.boundingRectWidth;
			}
		}
		
    }
    
    /**
     * 
     * @return the x center of mass of the goal in the image.
     */
    public double getRawGoalX()
    {
    	return this.goalBlob.center_mass_x;
    }
    
    /**
     * 
     * @return the y center of mass of the goal in the image.
     */
    public double getRawGoalY()
    {
    	return this.goalBlob.center_mass_y;
    }
    
    /**
     * 
     * @return the x center of mass of the goal in the image
     * in proportion to the width of the image.
     */
    public double getProportionalGoalX()
    {
    	return ((this.getRawGoalX() / xResolution) - (xResolution/2));
    }

    /**
     * 
     * @return the y center of mass of the goal in the image
     * in proportion to the width of the image.
     */
    public double getProportionalGoalY()
    {
    	return ((this.getRawGoalY() / yResolution) - (yResolution/2));
    }
    
    private void initCamera()
    {
    	this.camera.writeBrightness(Hardware.MINIMUM_AXIS_CAMERA_BRIGHTNESS);
    	this.camera.writeExposureControl(AxisCamera.ExposureControl.kHold);
    	this.camera.writeMaxFPS(Hardware.AXIS_FPS);
    	this.camera.writeResolution(Hardware.AXIS_RESOLUTION);
    	switch (this.camera.getResolution())
    	{
    	case k640x480:
    		xResolution = (640.0);
    		yResolution = (480.0);
    		break;
    	case k480x360:
    		xResolution = (480.0);
    		yResolution = (360.0);
    		break;
    	case k320x240:
    		xResolution = (320.0);
    		yResolution = (240.0);
    		break;
    	case k240x180:
    		xResolution = (240.0);
    		yResolution = (180.0);
    		break;
    	case k176x144:
    		xResolution = (176.0);
    		yResolution = (144.0);
    		break;
    	default:
    	case k160x120:
    		xResolution = (160.0);
    		yResolution = (120.0);
    		break;
    	}
    }
    
    private void initProcessing()
    {
		// Sets the hue, saturation, and luminance values for the vision
		// processing.
		//Hardware.imageProcessor.setHSLValues(0, 255, 0, 75, 5, 141);
		//Hardware.imageProcessor.setHSLValues(0, 115, 0, 69, 17, 44);
		imageProcessor.setHSLValues(78, 141, 55, 255, 9, 47);
		// Has us remove small objects at the intensity of 5. May have to
		// change those values.
		// Hardware.imageProcessor.setObjectRemoval(ObjectRemoval.BORDER);
		imageProcessor.setObjectRemoval(ObjectRemoval.SMALL,
		        2);//3
		// Has us convex hull our image so that the goal becomes a rectangle.
		imageProcessor.setUseConvexHull(true);
		// we could also crop the image to only include blobs in our
		// good height range, which removes the possibility of
		// convex hull connecting the two totes when we carry more than one
		// info on cropping image here:
		// http://www.chiefdelphi.com/forums/showthread.php?t=134264
		// Removed criteria that drops blobs outside the center that was in
		// this general area, we need to keep them so we can tell where it is
		// on the screen if it isn't in the center
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

