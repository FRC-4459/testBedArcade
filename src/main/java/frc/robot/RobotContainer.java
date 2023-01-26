package frc.robot;

import frc.robot.commands.Autos;
import frc.robot.commands.Spin;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer 
{
  private Drivetrain drivetrain;
  private Spin spin;
  private CommandXboxController driverController;


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer(Drivetrain dt, CommandXboxController xbc) 
  {
    // Configure the trigger bindings
    configureBindings();
    // Use our Drivetrain subsystem initialized in Robot.java
    drivetrain = dt;
    // Use the controller we've passed from Robot.java
    driverController = xbc;
    // Initialize a Spin command object using our drivetrain
    spin = new Spin(drivetrain);
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() 
  {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    // new Trigger(m_exampleSubsystem::exampleCondition)
    //     .onTrue(new ExampleCommand(m_exampleSubsystem));
    
    // Schedule a Spin command as long as B is held.
    driverController.b().whileTrue(spin);
  }

  public Command getAutonomousCommand() 
  {
    // Return whichever autonomous command you want to use.
    ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}
