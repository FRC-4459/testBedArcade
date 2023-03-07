package frc.robot;

import frc.robot.commands.Autos;
// import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Drivetrain;
// import frc.robot.commands.ClawGrab;
// import frc.robot.commands.ClawRelease;
// import frc.robot.commands.ClawDrive;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  private Drivetrain drivetrain;
  // private Claw claw;
  // private ClawGrab clawGrab;
  // private ClawRelease clawRelease;
  // private ClawDrive clawDriveForward;
  // private ClawDrive clawDriveBack;
  private CommandXboxController driverController;


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer(Drivetrain dt, CommandXboxController xbc) {
    // Use our Drivetrain subsystem initialized in Robot.java
    drivetrain = dt;
    
    // Use the controller we've passed from Robot.java
    driverController = xbc;
    
    // Initalize a new Claw subsystem
    // claw = new Claw();
    
    // Initalize a new ClawGrab command
    // clawGrab = new ClawGrab(claw, false);
    // clawRelease = new ClawRelease(claw, true);
    
    // Claw Drive commands
    // clawDriveForward = new ClawDrive(claw, 1, false);
    // clawDriveBack = new ClawDrive(claw, -1, false);
    
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in
   * {@link edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link CommandXboxController
   * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller PS4} controllers or
   * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight joysticks}.
   */
  private void configureBindings()
  {
    // driverController.x().toggleOnTrue(clawGrab);
    // driverController.y().onTrue(clawRelease);
    // driverController.rightBumper().whileTrue(clawDriveForward);
    // driverController.leftBumper().whileTrue(clawDriveBack);
  }

  public Command getAutonomousCommand()
  {
    // Return whichever autonomous command you want to use.
    return Autos.pushPieceAuto(drivetrain);
  }
}
