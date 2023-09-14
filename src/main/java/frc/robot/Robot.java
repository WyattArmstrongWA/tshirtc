// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;



public class Robot extends TimedRobot {

  WPI_TalonSRX m_leftMaster = new WPI_TalonSRX(3);
  WPI_TalonSRX m_rightMaster = new WPI_TalonSRX(13);
  WPI_TalonSRX m_rightSlave = new WPI_TalonSRX(2);
  WPI_TalonSRX m_leftSlave = new WPI_TalonSRX(1);
  //assign drivetrain motor IDs

  CANSparkMax m_switchMotor = new CANSparkMax(8, MotorType.kBrushless);
  //assign valve motor ID

  private final XboxController m_driverController = new XboxController(0);
  //assign controller port

  DifferentialDrive drive = new DifferentialDrive(m_leftMaster, m_rightMaster);
  //create differential drivetrain with leading/master motors


  //double startTime;
  //int x;
  //create variables for use in timer and iteration logic

  @Override
  public void robotInit() {

    m_rightMaster.setInverted(true);
    m_rightSlave.setInverted(true);
    //invert right side motors

    m_leftSlave.set(ControlMode.Follower, 3);
    m_rightSlave.set(ControlMode.Follower, 13);
    //assign follower status to follower/slave motors with regards to leading/master motor IDs

  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {

    if(m_driverController.getXButtonPressed()) {
      m_switchMotor.set(-0.8);
      //When the X button is pressed, the motor runs in the reverse direction, closing? the cannon valve
    }
    if(m_driverController.getXButtonReleased()) {
      m_switchMotor.set(0);
      //When the X button is released, the motor is stationary
    }
    

    if(m_driverController.getBButtonPressed()) {
      m_switchMotor.set(0.8);
      //When the B button is pressed the motor runs in the forward direction, opening? the cannon valve
    } 
    if(m_driverController.getBButtonReleased()) {
      m_switchMotor.set(0);
      //When the B button is released, the motor is stationary
    }

    drive.arcadeDrive(-m_driverController.getLeftY(), -m_driverController.getRightX());
    //
      }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
