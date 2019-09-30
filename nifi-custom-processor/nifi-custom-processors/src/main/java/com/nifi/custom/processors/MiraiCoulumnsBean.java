package com.nifi.custom.processors;

public class MiraiCoulumnsBean {
	
	String date_value;
	String Time_value;
	int unit_id;
	int senosor_measurunit_id;
	String sensor_name;
	String measurunit_symbol;
	float water_level;
	float flow_level;
	float pressure;
	float vibration;
	float temp;
	float voltage;
	float current;
	float power;
	String switch_level="0";
	
	
	public String getDate_value() {
		return date_value;
	}
	public void setDate_value(String date_value) {
		this.date_value = date_value;
	}
	public String getTime_value() {
		return Time_value;
	}
	public void setTime_value(String time_value) {
		Time_value = time_value;
	}
	public int getUnit_id() {
		return unit_id;
	}
	public void setUnit_id(int unit_id) {
		this.unit_id = unit_id;
	}
	public int getSenosor_measurunit_id() {
		return senosor_measurunit_id;
	}
	public void setSenosor_measurunit_id(int senosor_measurunit_id) {
		this.senosor_measurunit_id = senosor_measurunit_id;
	}
	public String getSensor_name() {
		return sensor_name;
	}
	public void setSensor_name(String sensor_name) {
		this.sensor_name = sensor_name;
	}
	public String getMeasurunit_symbol() {
		return measurunit_symbol;
	}
	public void setMeasurunit_symbol(String measurunit_symbol) {
		this.measurunit_symbol = measurunit_symbol;
	}
	public float getWater_level() {
		return water_level;
	}
	public void setWater_level(float water_level) {
		this.water_level = water_level;
	}
	public float getFlow_level() {
		return flow_level;
	}
	public void setFlow_level(float flow_level) {
		this.flow_level = flow_level;
	}
	public float getPressure() {
		return pressure;
	}
	public void setPressure(float pressure) {
		this.pressure = pressure;
	}
	public float getVibration() {
		return vibration;
	}
	public void setVibration(float vibration) {
		this.vibration = vibration;
	}
	public float getTemp() {
		return temp;
	}
	public void setTemp(float temp) {
		this.temp = temp;
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
	public String getSwitch_level() {
		return switch_level;
	}
	public void setSwitch_level(String switch_level) {
		this.switch_level = switch_level;
	}
	
	
}
