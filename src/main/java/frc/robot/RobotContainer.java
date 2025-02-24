// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.Arrays;
import org.photonvision.PhotonCamera;
import Team4450.Lib.Util;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.apriltag.AprilTag;
import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.ResetToAbsoluteCommand;
import frc.robot.commands.ResetToForwardCommand;
import frc.robot.commands.SwerveDriveCommand;
import frc.robot.subsystems.SwerveDriveBase;
//import edu.wpi.first.wpilibj2.command.button.Button;
//Above import is depreciated


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer 
{
  // The robot's subsystems and commands are defined here.

  public final SwerveDriveBase m_driveBase = new SwerveDriveBase();

  private final XboxController m_controller = new XboxController(0);

  private final PhotonCamera phCamera = new PhotonCamera("phCamera" );

  private final AprilTagFieldLayout aprlFieldLayout = new AprilTagFieldLayout(Arrays.asList(
    new AprilTag(1, new Pose3d(Units.inchesToMeters(610.77), Units.inchesToMeters( 42.19), Units.inchesToMeters(18.22), new Rotation3d(0.0, 0.0, Math.PI))),
    new AprilTag(2, new Pose3d(Units.inchesToMeters(610.77), Units.inchesToMeters(108.19), Units.inchesToMeters(18.22), new Rotation3d(0.0, 0.0, Math.PI))),
    new AprilTag(3, new Pose3d(Units.inchesToMeters(610.77), Units.inchesToMeters(174.19), Units.inchesToMeters(18.22), new Rotation3d(0.0, 0.0, Math.PI))),
    new AprilTag(4, new Pose3d(Units.inchesToMeters(636.96), Units.inchesToMeters(265.74), Units.inchesToMeters(27.38), new Rotation3d(0.0, 0.0, Math.PI))),
    new AprilTag(5, new Pose3d(Units.inchesToMeters( 14.25), Units.inchesToMeters(265.74), Units.inchesToMeters(27.38), new Rotation3d(0.0, 0.0, 0.0))),
    new AprilTag(6, new Pose3d(Units.inchesToMeters( 40.45), Units.inchesToMeters(174.19), Units.inchesToMeters(18.22), new Rotation3d(0.0, 0.0, 0.0))),
    new AprilTag(7, new Pose3d(Units.inchesToMeters( 40.45), Units.inchesToMeters(108.19), Units.inchesToMeters(18.22), new Rotation3d(0.0, 0.0, 0.0))),
    new AprilTag(8, new Pose3d(Units.inchesToMeters( 40.45), Units.inchesToMeters( 42.19), Units.inchesToMeters(18.22), new Rotation3d(0.0, 0.0, 0.0)))
  ), Units.inchesToMeters(651.25), Units.inchesToMeters(315.5));

  private final PhotonPoseEstimator photonPoseEstimator = new PhotonPoseEstimator(aprlFieldLayout, poseStrategy, phCamera, rbtCameraDist);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() 
  {
    Util.consoleLog();

    // Set up the default command for the drivetrain.
    // The controls are for field-oriented driving:
    // Left stick Y axis -> forward and backwards movement (throttle)
    // Left stick X axis -> left and right movement (strafe)
    // Right stick X axis -> rotation
    // Note: X and Y axis on stick is opposite X and Y axis on the WheelSpeeds object.
    // Wheelspeeds X axis is + down the field away from alliance wall. +Y axis is left
    // when standing at alliance wall looking down the field.
    // This is handled here by swapping the inputs. Note that first axis parameter below
    // is the X wheelspeeds input and the second is Y wheelspeeds input.

    m_driveBase.setDefaultCommand(new SwerveDriveCommand(
            m_driveBase,
            () -> m_controller.getRightY() + m_controller.getLeftY(), // test throttle on both sticks.
            () -> m_controller.getRightX(),
            () -> m_controller.getLeftX(),
            m_controller
    ));

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.
   */
  private void configureButtonBindings() 
  {
        
    new Trigger(() -> m_controller.getXButton())
        .onTrue(new InstantCommand(m_driveBase::zeroGyro));
        
    //new Button(m_controller::getXButton)
        // No requirements because we don't need to interrupt anything
    //    .whenPressed(m_driveBase::zeroGyro);
        
    new Trigger(() -> m_controller.getYButton())
        .onTrue(new InstantCommand(m_driveBase::setModulesToForward));

    //new Button(m_controller::getYButton)
    //    .whenPressed(m_driveBase::setModulesToForward);
        //.whenPressed(new ResetToForwardCommand(m_driveBase));
        
    new Trigger(() -> m_controller.getAButton())
        .onTrue(new InstantCommand(m_driveBase::setModulesToAbsolute));

    //new Button(m_controller::getAButton)
    //    .whenPressed(m_driveBase::setModulesToAbsolute);
        //.whenPressed(new ResetToAbsoluteCommand(m_driveBase));
        
    new Trigger(() -> m_controller.getLeftBumper())
        .onTrue(new InstantCommand(m_driveBase::setModulesToStartPosition));

    //new Button(m_controller::getLeftBumper)
    //    .whenPressed(m_driveBase::setModulesToStartPosition);
        
    new Trigger(() -> m_controller.getBButton())
        .onTrue(new InstantCommand(m_driveBase::resetModuleEncoders));

    //new Button(m_controller::getBButton)
    //    .whenPressed(m_driveBase::resetModuleEncoders);

    // Start button toggles autoRreturnToZero mode.
    new Trigger(() -> m_controller.getStartButton())
        .onTrue(new InstantCommand(m_driveBase::toggleAutoReturnToZero));
    
    //new Button(m_controller::getStartButton)
    //    .whenPressed(m_driveBase::toggleAutoReturnToZero);

    // Back button toggles field oriented driving mode.
    new Trigger(() -> m_controller.getBackButton())
        .onTrue(new InstantCommand(m_driveBase::toggleFieldOriented));

    //new Button(m_controller::getBackButton)
    //    .whenPressed(m_driveBase::toggleFieldOriented);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand()
  {
    // An ExampleCommand will run in autonomous
    return new InstantCommand();
  }
}
