// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants 
{
    public static final String	PROGRAM_NAME = "SDT-01.11.23-7";
    
    public static Robot			    robot;

    public static final double  THROTTLE_DEADBAND = .05;
    public static final double  ROTATION_DEADBAND = .05;
    public static final double  THROTTLE_SLEW = 1.5;        // rate of change % input/second.
    public static final double  ROTATION_SLEW = 3.0;

    public static final double CAMERA_HEIGHT_METERS = 0.0;
    public static final double TARGET_HEIGHT_METERS = 0.0;
    public static final double CAMERA_PITCH_RADIANS = 0.0;

    /**
     * The left-to-right distance between the drivetrain wheels
     * Should be measured from center to center.
     */
    public static final double DRIVETRAIN_TRACKWIDTH_METERS = .475; //  Measure and set trackwidth
   
    /**
     * The front-to-back distance between the drivetrain wheels.
     * Should be measured from center to center.
     */
    public static final double DRIVETRAIN_WHEELBASE_METERS = .475; // Measure and set wheelbase

    public enum ModulePosition 
    {
      FL,
      FR,
      BL,
      BR
    }

    public static final int FRONT_LEFT_MODULE_DRIVE_MOTOR = 1; // Set front left module drive motor ID
    public static final int FRONT_LEFT_MODULE_STEER_MOTOR = 2; //  Set front left module steer motor ID
    public static final int FRONT_LEFT_MODULE_STEER_ENCODER = 3; //  Set front left steer encoder ID
    public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(145.0); //  Measure and set front left steer offset

    public static final int FRONT_RIGHT_MODULE_DRIVE_MOTOR = 4; //  Set front right drive motor ID
    public static final int FRONT_RIGHT_MODULE_STEER_MOTOR = 5; //  Set front right steer motor ID
    public static final int FRONT_RIGHT_MODULE_STEER_ENCODER = 6; //  Set front right steer encoder ID
    public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(70.0); //  Measure and set front right steer offset

    public static final int BACK_LEFT_MODULE_DRIVE_MOTOR = 7; //  Set back left drive motor ID
    public static final int BACK_LEFT_MODULE_STEER_MOTOR = 8; //  Set back left steer motor ID
    public static final int BACK_LEFT_MODULE_STEER_ENCODER = 9; //  Set back left steer encoder ID
    public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(58.0); //  Measure and set back left steer offset

    public static final int BACK_RIGHT_MODULE_DRIVE_MOTOR = 10; //  Set back right drive motor ID
    public static final int BACK_RIGHT_MODULE_STEER_MOTOR = 11; //  Set back right steer motor ID
    public static final int BACK_RIGHT_MODULE_STEER_ENCODER = 12; //  Set back right steer encoder ID
    public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(123.0); //  Measure and set back right steer offset
}
