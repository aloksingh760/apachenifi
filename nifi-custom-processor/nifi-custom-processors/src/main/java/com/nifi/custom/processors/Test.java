package com.nifi.custom.processors;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Test {

	
	public static void main(String args[]) {
		
		Test t= new Test();
		String result ="[{\"Sensor\":\"lm0001\",\"Value\":1.0,\"ctime\":\"1559628780807\"},\r\n" + 
				"{\"Sensor\":\"lm0002\",\"Value\":0.0,\"ctime\":\"1559628780807\"},\r\n" + 
				"{\"Sensor\":\"lm0003\",\"Value\":1.0,\"ctime\":\"1559628780807\"},\r\n" + 
				"{\"Sensor\":\"lm0004\",\"Value\":0.0,\"ctime\":\"1559628780807\"},\r\n" + 
				"{\"Sensor\":\"t0001\",\"Value\":261.0,\"ctime\":\"1559628780807\"},\r\n" + 
				"{\"Sensor\":\"t0002\",\"Value\":284.0,\"ctime\":\"1559628780807\"},\r\n" + 
				"{\"Sensor\":\"t0003\",\"Value\":310.0,\"ctime\":\"1559628780807\"},\r\n" + 
				"{\"Sensor\":\"t0004\",\"Value\":8500.0,\"ctime\":\"1559628780807\"},\r\n" + 
				"{\"Sensor\":\"l0001\",\"Value\":4373.0,\"ctime\":\"1559628780807\"},\r\n" + 
				"{\"Sensor\":\"l0002\",\"Value\":5150.0,\"ctime\":\"1559628780807\"},\r\n" + 
				"{\"Sensor\":\"v0001\",\"Value\":15.0,\"ctime\":\"1559628780807\"},\r\n" + 
				"{\"Sensor\":\"v0002\",\"Value\":0.0,\"ctime\":\"1559628780807\"},\r\n" + 
				"{\"Sensor\":\"v0003\",\"Value\":16.0,\"ctime\":\"1559628780807\"},\r\n" + 
				"{\"Sensor\":\"v0004\",\"Value\":16.0,\"ctime\":\"1559628780807\"},\r\n" + 
				"{\"Sensor\":\"p0001\",\"Value\":24.0,\"ctime\":\"1559628780807\"},\r\n" + 
				"{\"Sensor\":\"p0002\",\"Value\":21.0,\"ctime\":\"1559628780807\"},\r\n" + 
				"{\"Sensor\":\"p0003\",\"Value\":0.0,\"ctime\":\"1559628780807\"},\r\n" + 
				"{\"Sensor\":\"p0004\",\"Value\":11.0,\"ctime\":\"1559628780807\"},\r\n" + 
				"{\"Sensor\":\"f0001\",\"Value\":0.0,\"ctime\":\"1559628780807\"},\r\n" + 
				"{\"Sensor\":\"f0002\",\"Value\":0.0,\"ctime\":\"1559628780807\"},\r\n" + 
				"{\"Sensor\":\"ep0001\",\"Value\":182.054992676,\"ctime\":\"1559628780807\"},\r\n" + 
				"{\"Sensor\":\"ec0001\",\"Value\":0.0,\"ctime\":\"1559628780807\"},\r\n" + 
				"{\"Sensor\":\"ev0001\",\"Value\":0.0,\"ctime\":\"1559628780807\"},\r\n" + 
				"{\"Sensor\":\"ep0002\",\"Value\":183.743499756,\"ctime\":\"1559628780807\"},\r\n" + 
				"{\"Sensor\":\"ec0002\",\"Value\":0.0,\"ctime\":\"1559628780807\"},\r\n" + 
				"{\"Sensor\":\"ev0002\",\"Value\":0.0,\"ctime\":\"1559628780807\"}]";
		
		
		String str="[{\"date_value\":\"2019-07-03\",\"Time_value\":\"10:25:00 AM\",\"unit_id\":1,\"senosor_measurunit_id\":3,\"sensor_name\":\"t0001\",\"measurunit_symbol\":\"degree celcius\",\"water_level\":0.0,\"flow_level\":0.0,\"pressure\":0.0,\"vibration\":0.0,\"temp\":67.3,\"voltage\":0.0,\"current\":0.0,\"power\":0.0}\",{\"date_value\":\"2019-07-03\",\"Time_value\":\"10:25:00 AM\",\"unit_id\":1,\"senosor_measurunit_id\":4,\"sensor_name\":\"t0002\",\"measurunit_symbol\":\"degree celcius\",\"water_level\":0.0,\"flow_level\":0.0,\"pressure\":0.0,\"vibration\":0.0,\"temp\":90.4,\"voltage\":0.0,\"current\":0.0,\"power\":0.0}\",{\"date_value\":\"2019-07-03\",\"Time_value\":\"10:25:00 AM\",\"unit_id\":2,\"senosor_measurunit_id\":5,\"sensor_name\":\"t0003\",\"measurunit_symbol\":\"degree celcius\",\"water_level\":0.0,\"flow_level\":0.0,\"pressure\":0.0,\"vibration\":0.0,\"temp\":40.0,\"voltage\":0.0,\"current\":0.0,\"power\":0.0}\",{\"date_value\":\"2019-07-03\",\"Time_value\":\"10:25:00 AM\",\"unit_id\":0,\"senosor_measurunit_id\":0,\"water_level\":0.0,\"flow_level\":0.0,\"pressure\":0.0,\"vibration\":0.0,\"temp\":0.0,\"voltage\":0.0,\"current\":0.0,\"power\":0.0}\",{\"date_value\":\"2019-07-03\",\"Time_value\":\"10:25:00 AM\",\"unit_id\":1,\"senosor_measurunit_id\":1,\"sensor_name\":\"l0001\",\"measurunit_symbol\":\"percentage\",\"water_level\":18.49,\"flow_level\":0.0,\"pressure\":0.0,\"vibration\":0.0,\"temp\":0.0,\"voltage\":0.0,\"current\":0.0,\"power\":0.0}\",{\"date_value\":\"2019-07-03\",\"Time_value\":\"10:25:00 AM\",\"unit_id\":2,\"senosor_measurunit_id\":2,\"sensor_name\":\"l0002\",\"measurunit_symbol\":\"percentage\",\"water_level\":68.07,\"flow_level\":0.0,\"pressure\":0.0,\"vibration\":0.0,\"temp\":0.0,\"voltage\":0.0,\"current\":0.0,\"power\":0.0}\",{\"date_value\":\"2019-07-03\",\"Time_value\":\"10:25:00 AM\",\"unit_id\":1,\"senosor_measurunit_id\":7,\"sensor_name\":\"v0001\",\"measurunit_symbol\":\"mm/sec\",\"water_level\":0.0,\"flow_level\":0.0,\"pressure\":0.0,\"vibration\":0.0,\"temp\":0.14,\"voltage\":0.0,\"current\":0.0,\"power\":0.0}\",{\"date_value\":\"2019-07-03\",\"Time_value\":\"10:25:00 AM\",\"unit_id\":1,\"senosor_measurunit_id\":8,\"sensor_name\":\"v0002\",\"measurunit_symbol\":\"mm/sec\",\"water_level\":0.0,\"flow_level\":0.0,\"pressure\":0.0,\"vibration\":0.0,\"temp\":0.0,\"voltage\":0.0,\"current\":0.0,\"power\":0.0}\",{\"date_value\":\"2019-07-03\",\"Time_value\":\"10:25:00 AM\",\"unit_id\":2,\"senosor_measurunit_id\":9,\"sensor_name\":\"v0003\",\"measurunit_symbol\":\"mm/sec\",\"water_level\":0.0,\"flow_level\":0.0,\"pressure\":0.0,\"vibration\":0.0,\"temp\":0.14,\"voltage\":0.0,\"current\":0.0,\"power\":0.0}\",{\"date_value\":\"2019-07-03\",\"Time_value\":\"10:25:00 AM\",\"unit_id\":2,\"senosor_measurunit_id\":10,\"sensor_name\":\"v0004\",\"measurunit_symbol\":\"mm/sec\",\"water_level\":0.0,\"flow_level\":0.0,\"pressure\":0.0,\"vibration\":0.0,\"temp\":0.13,\"voltage\":0.0,\"current\":0.0,\"power\":0.0}\",{\"date_value\":\"2019-07-03\",\"Time_value\":\"10:25:00 AM\",\"unit_id\":1,\"senosor_measurunit_id\":11,\"sensor_name\":\"p0001\",\"measurunit_symbol\":\"kPa\",\"water_level\":0.0,\"flow_level\":0.0,\"pressure\":2.4,\"vibration\":0.0,\"temp\":0.0,\"voltage\":0.0,\"current\":0.0,\"power\":0.0}\",{\"date_value\":\"2019-07-03\",\"Time_value\":\"10:25:00 AM\",\"unit_id\":1,\"senosor_measurunit_id\":12,\"sensor_name\":\"p0002\",\"measurunit_symbol\":\"kPa\",\"water_level\":0.0,\"flow_level\":0.0,\"pressure\":1.7,\"vibration\":0.0,\"temp\":0.0,\"voltage\":0.0,\"current\":0.0,\"power\":0.0}\",{\"date_value\":\"2019-07-03\",\"Time_value\":\"10:25:00 AM\",\"unit_id\":2,\"senosor_measurunit_id\":13,\"sensor_name\":\"p0003\",\"measurunit_symbol\":\"kPa\",\"water_level\":0.0,\"flow_level\":0.0,\"pressure\":0.0,\"vibration\":0.0,\"temp\":0.0,\"voltage\":0.0,\"current\":0.0,\"power\":0.0}\",{\"date_value\":\"2019-07-03\",\"Time_value\":\"10:25:00 AM\",\"unit_id\":2,\"senosor_measurunit_id\":14,\"sensor_name\":\"p0004\",\"measurunit_symbol\":\"kPa\",\"water_level\":0.0,\"flow_level\":0.0,\"pressure\":1.7,\"vibration\":0.0,\"temp\":0.0,\"voltage\":0.0,\"current\":0.0,\"power\":0.0}\",{\"date_value\":\"2019-07-03\",\"Time_value\":\"10:25:00 AM\",\"unit_id\":1,\"senosor_measurunit_id\":13,\"sensor_name\":\"f0001\",\"measurunit_symbol\":\"lpm\",\"water_level\":0.0,\"flow_level\":0.5,\"pressure\":0.0,\"vibration\":0.0,\"temp\":0.0,\"voltage\":0.0,\"current\":0.0,\"power\":0.0}\",{\"date_value\":\"2019-07-03\",\"Time_value\":\"10:25:00 AM\",\"unit_id\":2,\"senosor_measurunit_id\":14,\"sensor_name\":\"f0002\",\"measurunit_symbol\":\"lpm\",\"water_level\":0.0,\"flow_level\":0.45,\"pressure\":0.0,\"vibration\":0.0,\"temp\":0.0,\"voltage\":0.0,\"current\":0.0,\"power\":0.0}\",{\"date_value\":\"2019-07-03\",\"Time_value\":\"10:25:00 AM\",\"unit_id\":1,\"senosor_measurunit_id\":23,\"sensor_name\":\"ep0001\",\"measurunit_symbol\":\"kW\",\"water_level\":0.0,\"flow_level\":0.0,\"pressure\":0.0,\"vibration\":0.0,\"temp\":0.0,\"voltage\":0.0,\"current\":0.0,\"power\":182.0935}\",{\"date_value\":\"2019-07-03\",\"Time_value\":\"10:25:00 AM\",\"unit_id\":1,\"senosor_measurunit_id\":22,\"sensor_name\":\"ec0001\",\"measurunit_symbol\":\"\",\"water_level\":0.0,\"flow_level\":0.0,\"pressure\":0.0,\"vibration\":0.0,\"temp\":0.0,\"voltage\":1.0,\"current\":0.0,\"power\":0.0}\",{\"date_value\":\"2019-07-03\",\"Time_value\":\"10:25:00 AM\",\"unit_id\":1,\"senosor_measurunit_id\":21,\"sensor_name\":\"ev0001\",\"measurunit_symbol\":\"Volt\",\"water_level\":0.0,\"flow_level\":0.0,\"pressure\":0.0,\"vibration\":0.0,\"temp\":0.0,\"voltage\":0.0,\"current\":0.0,\"power\":0.0}\",{\"date_value\":\"2019-07-03\",\"Time_value\":\"10:25:00 AM\",\"unit_id\":2,\"senosor_measurunit_id\":26,\"sensor_name\":\"ep0002\",\"measurunit_symbol\":\"kW\",\"water_level\":0.0,\"flow_level\":0.0,\"pressure\":0.0,\"vibration\":0.0,\"temp\":0.0,\"voltage\":0.0,\"current\":0.0,\"power\":183.788}\",{\"date_value\":\"2019-07-03\",\"Time_value\":\"10:25:00 AM\",\"unit_id\":2,\"senosor_measurunit_id\":25,\"sensor_name\":\"ec0002\",\"measurunit_symbol\":\"\",\"water_level\":0.0,\"flow_level\":0.0,\"pressure\":0.0,\"vibration\":0.0,\"temp\":0.0,\"voltage\":2.0,\"current\":0.0,\"power\":0.0}\",{\"date_value\":\"2019-07-03\",\"Time_value\":\"10:25:00 AM\",\"unit_id\":2,\"senosor_measurunit_id\":24,\"sensor_name\":\"ev0002\",\"measurunit_symbol\":\"Volt\",\"water_level\":0.0,\"flow_level\":0.0,\"pressure\":0.0,\"vibration\":0.0,\"temp\":0.0,\"voltage\":0.0,\"current\":0.0,\"power\":0.0}\"]";
		str= str.replaceAll("\\\\", "").replaceAll("(\"\\{\")","\\{\"").replaceAll("(\"\\}\")","\"\\}");
				//.replaceAll("\"{", "").replaceAll("}\"", "");
		
		//str=str.replaceAll("(\"\\{\")","\\{\"").replaceAll("(\"\\}\")","\"\\}");
		str=str.replaceAll("\\}\"", "\\}").replaceAll("\\},", "\\}\n").replaceAll("[\\[\\]]","");
		//System.out.println(str);
		//System.out.println("(\"\\}\")");
		JsonParser  parser = new JsonParser();
		
		
        //JsonElement inputJson   = parser.parse(result);
     //   System.out.println(t.parseJson(result));
        
		
        //System.out.println(t.parseJson(inputJson));
		
		
		
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
			dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
			Date date = new Date();
		//	Date event = dateFormat.format(date.toGMTString());
			System.out.println(dateFormat.format(date));
			
	        DateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
	        Date d = f.parse("2019-07-02 04:08:48 PM");
	        DateFormat date1 = new SimpleDateFormat("yyyy-MM-dd");
	        DateFormat time = new SimpleDateFormat("hh:mm:ss a");
	       // System.out.println("Date: " + date1.format(d));
	        //System.out.println("Time: " + time.format(d));
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
		//Date date = new Date("2019-06-19 10:06:504");
		//System.out.println(dateFormat); 
		
	
	}
	
	  public String	parseJson(JsonElement jsonstring, HashMap<String, String> sensorMap2){
			
			 String transformedJson=null;
			 JsonArray transformedjsonArray=new JsonArray();
				try {
					//MiraiTranformationBean miraiob=new MiraiTranformationBean();
					
					
			        JsonArray elemArr = jsonstring.getAsJsonArray();
			       for (JsonElement jsonElement : elemArr) {
			    	   
			    	   MiraiTranformationBean miraiob=new MiraiTranformationBean();
			    	   JsonObject jb=jsonElement.getAsJsonObject();
			    	   
			    	   String sensorName=jb.get("Sensor").toString().replace("\"", "");
			    	   
			    	   if(sensorName.equals(sensorMap2.get("limit_switch_unit_1_valve_1"))) {
			    		   
			    		   miraiob.setUnit("CW1");
			    		   if(jb.get("Value").getAsInt()==1) {
			    		   miraiob.setLimitSwitchValve1("Open");
			    		   }else {
			    			   miraiob.setLimitSwitchValve1("Close");
			    		   }
			    	   }else if(sensorName.equals(sensorMap2.get("limit_switch_unit_1_valve_2"))) {
			    		   miraiob.setUnit("CW1");
			    		   if(jb.get("Value").getAsInt()==1) {
				    		   miraiob.setLimitSwitchValve2("Open");
				    		   }else {
				    			   miraiob.setLimitSwitchValve2("Close");
				    		   }
			    	   }else if(sensorName.equals(sensorMap2.get("limit_switch_unit_2_valve_1"))) {
			    		   miraiob.setUnit("CW2");
			    		   if(jb.get("Value").getAsInt()==1) {
				    		   miraiob.setLimitSwitchValve1("Open");
				    		   }else {
				    			   miraiob.setLimitSwitchValve1("Close");
				    		   }
			    	   }else if(sensorName.equals(sensorMap2.get("limit_switch_unit_2_valve_2"))) {
			    		   miraiob.setUnit("CW2");
			    		   if(jb.get("Value").getAsInt()==1) {
				    		   miraiob.setLimitSwitchValve2("Open");
				    		   }else {
				    			   miraiob.setLimitSwitchValve2("Close");
				    		   }
			    	   }else if(sensorName.equals(sensorMap2.get("motor_temp_unit_1"))) {
			    		   miraiob.setUnit("CW1");
			    		   miraiob.setMotor_temparature(jb.get("Value").getAsFloat()/10);
			    	   }else if(sensorName.equals(sensorMap2.get("pump_temp_unit_1"))) {
			    		   miraiob.setUnit("CW1");
			    		   miraiob.setPump_temparature(jb.get("Value").getAsFloat()/10);
			    	   }else if(sensorName.equals(sensorMap2.get("motor_temp_unit_2"))) {
			    		   miraiob.setUnit("CW2");
			    		   miraiob.setMotor_temparature(jb.get("Value").getAsFloat()/10);
			    	   }else if(sensorName.equals(sensorMap2.get("pump_temp_unit_2"))) {
			    		   miraiob.setUnit("CW2");
			    		   miraiob.setPump_temparature(jb.get("Value").getAsFloat()/10);
			    	   }else if(sensorName.equals(sensorMap2.get("motor_vibration_unit_1"))) {
			    		   miraiob.setUnit("CW1");
			    		   miraiob.setMotor_vibration(jb.get("Value").getAsFloat()/100);
			    	   }else if(sensorName.equals(sensorMap2.get("pump_vibration_unit_1"))) {
			    		   miraiob.setUnit("CW1");
			    		   miraiob.setPump_vibration(jb.get("Value").getAsFloat()/100);
			    	   }else if(sensorName.equals(sensorMap2.get("motor_vibration_unit_2"))) {
			    		   miraiob.setUnit("CW2");
			    		   miraiob.setMotor_vibration(jb.get("Value").getAsFloat()/100);
			    	   }else if(sensorName.equals(sensorMap2.get("pump_vibration_unit_2"))) {
			    		   miraiob.setUnit("CW2");
			    		   miraiob.setPump_vibration(jb.get("Value").getAsFloat()/100);
			    	   }
			    	   
			    	   else if(sensorName.equals(sensorMap2.get("motor_pressure_unit_1"))) {
			    		   miraiob.setUnit("CW1");
			    		   miraiob.setMotor_pressure(jb.get("Value").getAsFloat()/10);
			    	   }else if(sensorName.equals(sensorMap2.get("pump_pressure_unit_1"))) {
			    		   miraiob.setUnit("CW1");
			    		   miraiob.setPump_pressure(jb.get("Value").getAsFloat()/10);
			    	   }else if(sensorName.equals(sensorMap2.get("motor_pressure_unit_2"))) {
			    		   miraiob.setUnit("CW2");
			    		   miraiob.setMotor_pressure(jb.get("Value").getAsFloat()/10);
			    	   }else if(sensorName.equals(sensorMap2.get("pump_pressure_unit_2"))) {
			    		   miraiob.setUnit("CW2");
			    		   miraiob.setPump_pressure(jb.get("Value").getAsFloat()/10);
			    	   }
			    	   
			    	   else if(sensorName.equals(sensorMap2.get("water_level_tank_1"))) {
			    		   miraiob.setUnit("CW1");
			    		   miraiob.setWater_level_tank(jb.get("Value").getAsFloat()/100);
			    	   }else if(sensorName.equals(sensorMap2.get("water_level_tank_2"))) {
			    		   miraiob.setUnit("CW2");
			    		   miraiob.setWater_level_tank(jb.get("Value").getAsFloat()/100);
			    	   } 
			    	   
			    	   else if(sensorName.equals(sensorMap2.get("flow_unit_1"))) {
			    		   miraiob.setUnit("CW1");
			    		   miraiob.setWater_flow(jb.get("Value").getAsFloat()/100);
			    	   }else if(sensorName.equals(sensorMap2.get("flow_unit_2"))) {
			    		   miraiob.setUnit("CW2");
			    		   miraiob.setWater_flow(jb.get("Value").getAsFloat()/100);
			    	   }
			    	   else if(sensorName.equals(sensorMap2.get("power_unit_1"))) {
			    		   miraiob.setUnit("CW1");
			    		   miraiob.setPower(jb.get("Value").getAsFloat());
			    	   }else if(sensorName.equals(sensorMap2.get("power_unit_2"))) {
			    		   miraiob.setUnit("CW2");
			    		   miraiob.setPower(jb.get("Value").getAsFloat());
			    	   }
			    	   
			    	   else if(sensorName.equals(sensorMap2.get("voltage_unit_1"))) {
			    		   miraiob.setUnit("CW1");
			    		   miraiob.setVoltage(jb.get("Value").getAsFloat()/10);
			    	   }else if(sensorName.equals(sensorMap2.get("voltage_unit_2"))) {
			    		   miraiob.setUnit("CW2");
			    		   miraiob.setVoltage(jb.get("Value").getAsFloat()/10);
			    	   }
			    	   
			    	   else if(sensorName.equals(sensorMap2.get("current_unit_1"))) {
			    		   miraiob.setUnit("CW1");
			    		   miraiob.setCurrent(jb.get("Value").getAsFloat()/100);
			    	   }else if(sensorName.equals(sensorMap2.get("current_unit_2"))) {
			    		   miraiob.setUnit("CW2");
			    		   miraiob.setCurrent(jb.get("Value").getAsFloat()/100);
			    	   }
			    	   
			    	   
			    	   		    	   	    	 
			    	   miraiob.setEventGenratedTime(jb.get("ctime").getAsString());
			    	   
			    	 // System.out.println(jb.get("Sensor"));
			    	   Gson gson = new Gson();       
				       transformedJson=gson.toJson(miraiob);
				       transformedjsonArray.add(transformedJson);
			    			   
					
				}
			       
			       Gson gson = new Gson(); 
			       transformedJson=gson.toJson(transformedjsonArray); 
		    	   //transformedJson1=gson.toJson(transformedJson); 
			       transformedJson= transformedJson.replaceAll("\\\\", "").replaceAll("(\"\\{\")","\\{\"").replaceAll("(\"\\}\")","\"\\}").replaceAll("\\}\"", "\\}").replaceAll("\\},", "\\}\n").replaceAll("[\\[\\]]","");

				
				}  catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return transformedJson;
			}
}
