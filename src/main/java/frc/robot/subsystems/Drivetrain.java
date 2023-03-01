package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.Utility;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drivetrain extends SubsystemBase {
  private final Talon rightMotor = new Talon(Constants.rightMotorPort);
  private final Talon rightMotorFollower = new Talon(Constants.rightMotorFollowerPort);
  private final Talon leftMotor = new Talon(Constants.leftMotorPort);
  private final Talon leftMotorFollower = new Talon(Constants.leftMotorFollowerPort);

  private final MotorControllerGroup motorGroupLeft =
      new MotorControllerGroup(leftMotor, leftMotorFollower);
  private final MotorControllerGroup motorGroupRight =
      new MotorControllerGroup(rightMotor, rightMotorFollower);

  private final DifferentialDrive diffDrive =
      new DifferentialDrive(motorGroupLeft, motorGroupRight);

  private final double speedMult = 0.8;

  public Drivetrain() {
    rightMotor.setSafetyEnabled(true);
    rightMotorFollower.setSafetyEnabled(true);
    leftMotor.setSafetyEnabled(true);
    leftMotorFollower.setSafetyEnabled(true);

    rightMotor.setInverted(true);
    rightMotorFollower.setInverted(true);
  }


  public boolean stopped()
  {
    if (Utility.allEqual(getAllMotors(), 0))
    {
      return true;
    }
    return false;
  }

  public void setLeft(double i)
  {
    i = i * speedMult;
    leftMotor.set(i);
    leftMotorFollower.set(i / 2);
  }

  public void setRight(double i)
  {
    i = i * speedMult;
    rightMotor.set(i);
    rightMotorFollower.set(i);
  }

  public void set(double i)
  {
    i = i * speedMult;
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

  public void Drive(String mode, Double axis1, Double axis2)
  {
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


  public void mechanumDrive(CommandXboxController controller)
  {
    double forwardBack = controller.getLeftY();
    double left = controller.getLeftTriggerAxis();
    double right = controller.getRightTriggerAxis();
    double shiftX = controller.getRightX();
    double shiftY = controller.getRightY();

    shiftY = -shiftY;

    forwardBack *= speedMult;
    left *= speedMult;
    right *= speedMult;

    forwardBack = -forwardBack;

    if (forwardBack > 0.05 || forwardBack < -0.05)
    {
      System.out.print(forwardBack);
      System.out.print("\n");
      setLeft(forwardBack);
      setRight(forwardBack);
    }

    if (left > 0.05)
    {
      setLeft(-left);
      setRight(left);
    }

    if (right > 0.05)
    {
      setLeft(right);
      setRight(-right);
    }

    if ((shiftX > 0.12 || shiftX < -0.12) || (shiftY > 0.12 || shiftY < -0.12))
    {
      double desiredAngle = Math.atan(shiftY / shiftX) - 45;
      double motorGroup1, motorGroup2;
      
      if (shiftX > 0)
      {

        if (shiftY < 0)
        {
          motorGroup1 = Math.cos(desiredAngle);
          motorGroup2 = Math.sin(-desiredAngle);
        }
        else
        {
          motorGroup1 = Math.cos(desiredAngle);
          motorGroup2 = Math.sin(desiredAngle);
        }
      }
      
      else
      {
        if (shiftY < 0)
        {
          motorGroup1 = Math.cos(-desiredAngle);
          motorGroup2 = Math.sin(-desiredAngle);
        }
        else
        {
          motorGroup1 = Math.cos(-desiredAngle);
          motorGroup2 = Math.sin(desiredAngle);
        }
      }

      rightMotor.set(motorGroup2);
      rightMotorFollower.set(motorGroup1);
      leftMotor.set(motorGroup1);
      leftMotorFollower.set(motorGroup2);
    }
  }

  private double[] getAllMotors()
  {
    double[] arr = new double[4];

    arr[0] = rightMotor.get();
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
