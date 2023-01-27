package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class Drivetrain extends SubsystemBase 
{
    private final Talon rightMotor = new Talon(0);
    private final Talon rightMotorFollower = new Talon(1);
    private final Talon leftMotor = new Talon(2);
    private final Talon leftMotorFollower = new Talon(3);
    private final MotorControllerGroup motorGroupLeft = new MotorControllerGroup(leftMotor, leftMotorFollower);
    private final MotorControllerGroup motorGroupRight = new MotorControllerGroup(rightMotor, rightMotorFollower);
    private final DifferentialDrive diffDrive = new DifferentialDrive(motorGroupLeft, motorGroupRight);

  public Drivetrain() 
  {
    // Invert the voltages going to the right side of the robot.
    motorGroupRight.setInverted(true);
  }


  public boolean stopped() 
  {
    //Put some code here to check if the drivetrain is stopped (test Check/GetStatus on the motor controllers)
    return false;
  }

  public void setLeft(int i) 
  {
    leftMotor.set(i);
    leftMotorFollower.set(i);
  }
  
  public void setRight(int i) 
  {
    rightMotor.set(i);
    rightMotorFollower.set(i);
  }

  public void set(int i) 
  {
    rightMotor.set(i);
    leftMotor.set(i);
    rightMotorFollower.set(i);
    leftMotorFollower.set(i);
  }

  public void stop()
  {
    rightMotor.stopMotor();
    leftMotor.stopMotor();
    rightMotorFollower.stopMotor();
    leftMotorFollower.stopMotor();
  }

  public void Drive(String mode, Double axis1, Double axis2) {
    // Flip the values going to the testbed, because otherwise it would drive backwards.
    axis1 = -axis1;
    axis2 = -axis2;

    if (mode == "Arcade") 
    {
      diffDrive.arcadeDrive(axis1, axis2);
    }
    else if (mode == "Tank")
    {
      diffDrive.tankDrive(axis1, axis2);
    }
  }

  public CommandBase driveForwardCommand(int secs, int speed) {
    return runOnce(
        () -> {
          set(speed);
          safeSleep(secs);
          stop();
        });
  }

  private void safeSleep(int secs) 
  {
    try 
    {
      Thread.sleep(secs * 1000);
    } catch(InterruptedException e)
    {
      System.out.println("Thread sleep in Drivetrain.java/driveForwardCommand interrupted!");
    }
  }

  @Override
  public void periodic() 
  {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() 
  {
    // This method will be called once per scheduler run during simulation
  }
}
