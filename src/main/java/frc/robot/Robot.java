// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  private XboxController joystick = new XboxController(0);
  TalonFXConfiguration config = new TalonFXConfiguration();
  
  private int LEFT_MOTOR_TOP_PORT = 2;
  private int LEFT_MOTOR_FRONT_PORT = 21;
  private int LEFT_MOTOR_BACK_PORT = 23;
  private int RIGHT_MOTOR_TOP_PORT = 5;
  private int RIGHT_MOTOR_FRONT_PORT = 24;
  private int RIGHT_MOTOR_BACK_PORT = 26;
  
  private TalonFX leftMotorBack = new TalonFX(LEFT_MOTOR_BACK_PORT, "Canivore");
  private TalonFX leftMotorFront = new TalonFX(LEFT_MOTOR_FRONT_PORT, "Canivore");
  private TalonFX leftMotorTop = new TalonFX(LEFT_MOTOR_TOP_PORT, "Canivore");
  private TalonFX rightMotorBack = new TalonFX(RIGHT_MOTOR_BACK_PORT, "Canivore");
  private TalonFX rightMotorFront = new TalonFX(RIGHT_MOTOR_FRONT_PORT, "Canivore");
  private TalonFX rightMotorTop = new TalonFX(RIGHT_MOTOR_TOP_PORT, "Canivore");
  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items
   * like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different
   * autonomous modes using the dashboard. The sendable chooser code works with
   * the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the
   * chooser code and
   * uncomment the getString line to get the auto name from the text box below the
   * Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional comparisons to the
   * switch structure
   * below with additional strings. If using the SendableChooser make sure to add
   * them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {

    // Get x and y inputs from controller
    double LeftY = -joystick.getLeftY(); 
    double RightY = joystick.getRightY();

    // Whatever this is
    double deadzone = 0.1;

    // Moves the motors somehow
    if (Math.abs(LeftY) >= deadzone){
      leftMotorBack.set(TalonFXControlMode.PercentOutput, LeftY);   
      leftMotorFront.set(TalonFXControlMode.PercentOutput, LeftY);
      leftMotorTop.set(TalonFXControlMode.PercentOutput, LeftY);
    } else{
      leftMotorBack.set(TalonFXControlMode.PercentOutput, 0);   
      leftMotorFront.set(TalonFXControlMode.PercentOutput, 0);
      leftMotorTop.set(TalonFXControlMode.PercentOutput, 0);
    }
    if (Math.abs(RightY) >= deadzone){
      rightMotorTop.set(TalonFXControlMode.PercentOutput, RightY);
      rightMotorFront.set(TalonFXControlMode.PercentOutput, RightY);
      rightMotorBack.set(TalonFXControlMode.PercentOutput, RightY);
    }else{
      rightMotorTop.set(TalonFXControlMode.PercentOutput, 0);
      rightMotorFront.set(TalonFXControlMode.PercentOutput, 0);
      rightMotorBack.set(TalonFXControlMode.PercentOutput, 0);
      
  }
}
  

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {
  }

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {
  }

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {
  }

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {
  }
}
