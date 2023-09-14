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

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  WPI_TalonSRX m_leftMaster = new WPI_TalonSRX(3);
  WPI_TalonSRX m_rightMaster = new WPI_TalonSRX(13);
  WPI_TalonSRX m_rightSlave = new WPI_TalonSRX(2);
  WPI_TalonSRX m_leftSlave = new WPI_TalonSRX(1);
  CANSparkMax m_switchMotor = new CANSparkMax(8, MotorType.kBrushless);
  private final XboxController m_driverController = new XboxController(0);
  DifferentialDrive drive = new DifferentialDrive(m_leftMaster, m_rightMaster);

  //private Compressor compressor = new Compressor();
	//private Solenoid cannon = new Solenoid(Open_PORT_ID, Close_PORT_ID);

  double startTime;
  int x;

  @Override
  public void robotInit() {
    m_rightMaster.setInverted(true);
    m_rightSlave.setInverted(true);
    m_leftSlave.set(ControlMode.Follower, 3);
    m_rightSlave.set(ControlMode.Follower, 13);
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

    if(m_driverController.getAButtonPressed()) {
      startTime = System.currentTimeMillis();
      x++;
    } else {
      if(System.currentTimeMillis() - startTime < 0.5) {
        if(x % 2 == 0) {
          m_switchMotor.set(0.6);
          } else {
          m_switchMotor.set(-0.6);
          }
      } else {
        m_switchMotor.set(0);
      }
    }
    drive.arcadeDrive(-m_driverController.getLeftY(), -m_driverController.getRightX());
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
