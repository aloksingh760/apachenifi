package com.nifi.custom.processors;

import java.math.BigInteger;
import java.sql.Timestamp;

public class MiraiBean {

	
	private float water_level_tank_1;
	private float water_level_tank_2;
	private float motor_temp_unit_1;
	private float pump_temp_unit_1;
	private float motor_temp_unit_2;
	private float pump_temp_unit_2;
	private float motor_vibration_unit_1;
	private float pump_vibration_unit_1;
	private float motor_vibration_unit_2;
	private float pump_vibration_unit_2;
	private float inlet_pressure_unit_1;
	private float outlet_pressure_unit_1;
	private float inlet_pressure_unit_2;
	private float outlet_pressure_unit_2;
	private float flow_unit_1;
	private float flow_unit_2;
	private String limit_switch_unit_1_valve_1;
	private String limit_switch_unit_1_valve_2;
	private String limit_switch_unit_2_valve_1;
	private String limit_switch_unit_2_valve_2;
	private float voltage_unit_1;
	private float voltage_unit_2;
	private float current_unit_1;
	private float current_unit_2;
	private float power_unit_1;
	private float power_unit_2;
	private String cTime;
	
	
	public float getWater_level_tank_1() {
		return water_level_tank_1;
	}
	public void setWater_level_tank_1(float water_level_tank_1) {
		this.water_level_tank_1 = water_level_tank_1;
	}
	public float getWater_level_tank_2() {
		return water_level_tank_2;
	}
	public void setWater_level_tank_2(float water_level_tank_2) {
		this.water_level_tank_2 = water_level_tank_2;
	}
	public float getMotor_temp_unit_1() {
		return motor_temp_unit_1;
	}
	public void setMotor_temp_unit_1(float motor_temp_unit_1) {
		this.motor_temp_unit_1 = motor_temp_unit_1;
	}
	public float getPump_temp_unit_1() {
		return pump_temp_unit_1;
	}
	public void setPump_temp_unit_1(float pump_temp_unit_1) {
		this.pump_temp_unit_1 = pump_temp_unit_1;
	}
	public float getMotor_temp_unit_2() {
		return motor_temp_unit_2;
	}
	public void setMotor_temp_unit_2(float motor_temp_unit_2) {
		this.motor_temp_unit_2 = motor_temp_unit_2;
	}
	public float getPump_temp_unit_2() {
		return pump_temp_unit_2;
	}
	public void setPump_temp_unit_2(float pump_temp_unit_2) {
		this.pump_temp_unit_2 = pump_temp_unit_2;
	}
	public float getMotor_vibration_unit_1() {
		return motor_vibration_unit_1;
	}
	public void setMotor_vibration_unit_1(float motor_vibration_unit_1) {
		this.motor_vibration_unit_1 = motor_vibration_unit_1;
	}
	public float getPump_vibration_unit_1() {
		return pump_vibration_unit_1;
	}
	public void setPump_vibration_unit_1(float pump_vibration_unit_1) {
		this.pump_vibration_unit_1 = pump_vibration_unit_1;
	}
	public float getMotor_vibration_unit_2() {
		return motor_vibration_unit_2;
	}
	public void setMotor_vibration_unit_2(float motor_vibration_unit_2) {
		this.motor_vibration_unit_2 = motor_vibration_unit_2;
	}
	public float getPump_vibration_unit_2() {
		return pump_vibration_unit_2;
	}
	public void setPump_vibration_unit_2(float pump_vibration_unit_2) {
		this.pump_vibration_unit_2 = pump_vibration_unit_2;
	}
	
	public float getInlet_pressure_unit_1() {
		return inlet_pressure_unit_1;
	}
	public void setInlet_pressure_unit_1(float inlet_pressure_unit_1) {
		this.inlet_pressure_unit_1 = inlet_pressure_unit_1;
	}
	public float getOutlet_pressure_unit_1() {
		return outlet_pressure_unit_1;
	}
	public void setOutlet_pressure_unit_1(float outlet_pressure_unit_1) {
		this.outlet_pressure_unit_1 = outlet_pressure_unit_1;
	}
	public float getInlet_pressure_unit_2() {
		return inlet_pressure_unit_2;
	}
	public void setInlet_pressure_unit_2(float intlet_pressure_unit_2) {
		this.inlet_pressure_unit_2 = intlet_pressure_unit_2;
	}
	public float getOutlet_pressure_unit_2() {
		return outlet_pressure_unit_2;
	}
	public void setOutlet_pressure_unit_2(float outlet_pressure_unit_2) {
		this.outlet_pressure_unit_2 = outlet_pressure_unit_2;
	}
	public float getFlow_unit_1() {
		return flow_unit_1;
	}
	public void setFlow_unit_1(float flow_unit_1) {
		this.flow_unit_1 = flow_unit_1;
	}
	public float getFlow_unit_2() {
		return flow_unit_2;
	}
	public void setFlow_unit_2(float flow_unit_2) {
		this.flow_unit_2 = flow_unit_2;
	}
	
	public String getLimit_switch_unit_1_valve_1() {
		return limit_switch_unit_1_valve_1;
	}
	public void setLimit_switch_unit_1_valve_1(String limit_switch_unit_1_valve_1) {
		this.limit_switch_unit_1_valve_1 = limit_switch_unit_1_valve_1;
	}
	public String getLimit_switch_unit_1_valve_2() {
		return limit_switch_unit_1_valve_2;
	}
	public void setLimit_switch_unit_1_valve_2(String limit_switch_unit_1_valve_2) {
		this.limit_switch_unit_1_valve_2 = limit_switch_unit_1_valve_2;
	}
	public String getLimit_switch_unit_2_valve_1() {
		return limit_switch_unit_2_valve_1;
	}
	public void setLimit_switch_unit_2_valve_1(String limit_switch_unit_2_valve_1) {
		this.limit_switch_unit_2_valve_1 = limit_switch_unit_2_valve_1;
	}
	public String getLimit_switch_unit_2_valve_2() {
		return limit_switch_unit_2_valve_2;
	}
	public void setLimit_switch_unit_2_valve_2(String limit_switch_unit_2_valve_2) {
		this.limit_switch_unit_2_valve_2 = limit_switch_unit_2_valve_2;
	}
	public float getVoltage_unit_1() {
		return voltage_unit_1;
	}
	public void setVoltage_unit_1(float voltage_unit_1) {
		this.voltage_unit_1 = voltage_unit_1;
	}
	public float getVoltage_unit_2() {
		return voltage_unit_2;
	}
	public void setVoltage_unit_2(float voltage_unit_2) {
		this.voltage_unit_2 = voltage_unit_2;
	}
	public float getCurrent_unit_1() {
		return current_unit_1;
	}
	public void setCurrent_unit_1(float current_unit_1) {
		this.current_unit_1 = current_unit_1;
	}
	public float getCurrent_unit_2() {
		return current_unit_2;
	}
	public void setCurrent_unit_2(float current_unit_2) {
		this.current_unit_2 = current_unit_2;
	}
	public float getPower_unit_1() {
		return power_unit_1;
	}
	public void setPower_unit_1(float power_unit_1) {
		this.power_unit_1 = power_unit_1;
	}
	public float getPower_unit_2() {
		return power_unit_2;
	}
	public void setPower_unit_2(float power_unit_2) {
		this.power_unit_2 = power_unit_2;
	}
	public String getcTime() {
		return cTime;
	}
	public void setcTime(String cTime) {
		this.cTime = cTime;
	}
		
}
