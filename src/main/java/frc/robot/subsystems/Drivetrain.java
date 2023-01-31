package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.Utility;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class Drivetrain extends SubsystemBase 
{
    private final Talon rightMotor = new Talon(Constants.rightMotorPort);
    private final Talon rightMotorFollower = new Talon(Constants.rightMotorFollowerPort);
    private final Talon leftMotor = new Talon(Constants.leftMotorPort);
    private final Talon leftMotorFollower = new Talon(Constants.leftMotorFollowerPort);

    private final MotorControllerGroup motorGroupLeft = new MotorControllerGroup(leftMotor, leftMotorFollower);
    private final MotorControllerGroup motorGroupRight = new MotorControllerGroup(rightMotor, rightMotorFollower);

    private final DifferentialDrive diffDrive = new DifferentialDrive(motorGroupLeft, motorGroupRight);

    private final double speedMult = 0.8;

  public Drivetrain() 
  {
    // Invert the voltages going to the right side of the robot.
    motorGroupRight.setInverted(true);

    rightMotor.setSafetyEnabled(true);
    rightMotorFollower.setSafetyEnabled(true);
    leftMotor.setSafetyEnabled(true);
    leftMotorFollower.setSafetyEnabled(true);
  }


  public boolean stopped() 
  {
    if (Utility.allEqual(getAllMotors(), 0)) 
    {
      return true;
    }
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

    axis1 *= speedMult;
    axis2 *= speedMult;

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
          Utility.safeSleep(secs);
          stop();
        });
  }

  private double[] getAllMotors() 
  {
    double[] arr = new double[4];
    
    arr[0] = rightMotor.get() ;
    arr[1] = rightMotorFollower.get();
    arr[2] = leftMotor.get();
    arr[3] = leftMotorFollower.get();
    
    return arr;
  }

  private void feedMotors() 
  {
    leftMotor.feed();
    leftMotorFollower.feed();
    rightMotor.feed();
    rightMotorFollower.feed();
  }


  @Override
  public void periodic() 
  {
    feedMotors();
  }

  @Override
  public void simulationPeriodic() 
  {
    // This method will be called once per scheduler run during simulation
  }
}
