// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.Talon;

public class Drivetrain extends SubsystemBase {
    private final Talon leftMotor = new Talon(0);
    private final Talon leftMotorFollower = new Talon(1);
    private final Talon rightMotor = new Talon(2);
    private final Talon rightMotorFollower = new Talon(3);


  public Drivetrain() {}


  public boolean stopped() {
    //Put some code here to check if the drivetrain is stopped (test Check/GetStatus on the motor controllers)
    return false;
  }

  public void setLeft(int i) {
    leftMotor.set(i);
    leftMotorFollower.set(i);
  }
  
  public void setRight(int i) {
    rightMotor.set(i);
    rightMotorFollower.set(i);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
