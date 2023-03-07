// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;

public class Claw extends SubsystemBase {
    private PWMTalonSRX driverMotor = new PWMTalonSRX(Constants.clawDriverPort);
    private PWMTalonSRX gripMotor = new PWMTalonSRX(Constants.clawGripPort);

    public Claw() {}

    public void setDriverMotor(double speed)
    {
        driverMotor.set(speed);
    }

    public void setGrip(double val)
    {
        gripMotor.set(val);
    }

    @Override
    public void periodic() {}

    @Override
    public void simulationPeriodic()
    {
        // This method will be called once per scheduler run during simulation
    }
}
