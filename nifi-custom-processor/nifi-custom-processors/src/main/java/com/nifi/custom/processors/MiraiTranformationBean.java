package com.nifi.custom.processors;

public class MiraiTranformationBean {

	private String unit;
	private String eventGenratedTime;
	private float water_level_tank;
	private float motor_temparature;
	private float pump_temparature;
	private float motor_vibration;
	private float pump_vibration;
	private float motor_pressure;
	private float pump_pressure;
	private float water_flow;
	private String limitSwitchValve1="0";
	private String limitSwitchValve2="0";
	private float voltage;
	private float current;
	private float power;
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String setEventGenratedTime() {
		return eventGenratedTime;
	}
	public void setEventGenratedTime(String eventGenratedTime) {
		this.eventGenratedTime = eventGenratedTime;
	}
	public float getWater_level_tank() {
		return water_level_tank;
	}
	public void setWater_level_tank(float water_level_tank) {
		this.water_level_tank = water_level_tank;
	}
	public float getMotor_temparature() {
		return motor_temparature;
	}
	public void setMotor_temparature(float motor_temparature) {
		this.motor_temparature = motor_temparature;
	}
	public float getPump_temparature() {
		return pump_temparature;
	}
	public void setPump_temparature(float pump_temparature) {
		this.pump_temparature = pump_temparature;
	}
	public float getMotor_vibration() {
		return motor_vibration;
	}
	public void setMotor_vibration(float motor_vibration) {
		this.motor_vibration = motor_vibration;
	}
	public float getPump_vibration() {
		return pump_vibration;
	}
	public void setPump_vibration(float pump_vibration) {
		this.pump_vibration = pump_vibration;
	}
	public float getMotor_pressure() {
		return motor_pressure;
	}
	public void setMotor_pressure(float motor_pressure) {
		this.motor_pressure = motor_pressure;
	}
	public float getPump_pressure() {
		return pump_pressure;
	}
	public void setPump_pressure(float pump_pressure) {
		this.pump_pressure = pump_pressure;
	}
	public float getWater_flow() {
		return water_flow;
	}
	public void setWater_flow(float water_flow) {
		this.water_flow = water_flow;
	}
	public String getLimitSwitchValve1() {
		return limitSwitchValve1;
	}
	public void setLimitSwitchValve1(String limitSwitchValve1) {
		this.limitSwitchValve1 = limitSwitchValve1;
	}
	public String getLimitSwitchValve2() {
		return limitSwitchValve2;
	}
	public void setLimitSwitchValve2(String limitSwitchValve2) {
		this.limitSwitchValve2 = limitSwitchValve2;
	}
	public float getVoltage() {
		return voltage;
	}
	public void setVoltage(float voltage) {
		this.voltage = voltage;
	}
	public float getCurrent() {
		return current;
	}
	public void setCurrent(float current) {
		this.current = current;
	}
	public float getPower() {
		return power;
	}
	public void setPower(float power) {
		this.power = power;
	}
	
	
	
	
	
}
