package com.nifi.custom.processors;
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */




import org.apache.nifi.annotation.behavior.InputRequirement;
import org.apache.nifi.annotation.behavior.EventDriven;
import org.apache.nifi.annotation.behavior.SideEffectFree;
import org.apache.nifi.annotation.behavior.SupportsBatching;
import org.apache.nifi.components.PropertyDescriptor;
import org.apache.nifi.flowfile.FlowFile;
import org.apache.commons.io.IOUtils;
import org.apache.nifi.annotation.behavior.ReadsAttribute;
import org.apache.nifi.annotation.behavior.ReadsAttributes;
import org.apache.nifi.annotation.behavior.WritesAttribute;
import org.apache.nifi.annotation.behavior.WritesAttributes;
import org.apache.nifi.annotation.lifecycle.OnScheduled;
import org.apache.nifi.annotation.documentation.CapabilityDescription;
import org.apache.nifi.annotation.documentation.SeeAlso;
import org.apache.nifi.annotation.documentation.Tags;
import org.apache.nifi.processor.exception.ProcessException;
import org.apache.nifi.processor.AbstractProcessor;
import org.apache.nifi.processor.ProcessContext;
import org.apache.nifi.processor.ProcessSession;
import org.apache.nifi.processor.ProcessorInitializationContext;
import org.apache.nifi.processor.Relationship;
import org.apache.nifi.processor.util.StandardValidators;
import org.apache.nifi.util.StopWatch;
import org.apache.nifi.logging.ComponentLog;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.apache.nifi.processor.io.OutputStreamCallback;
import org.apache.nifi.flowfile.attributes.CoreAttributes;
import java.util.concurrent.TimeUnit;
import org.apache.nifi.expression.ExpressionLanguageScope;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;



import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Tags({"Mirai"})
@EventDriven
@SideEffectFree
@SupportsBatching
@ReadsAttributes({@ReadsAttribute(attribute="", description="")})
@WritesAttribute(attribute = "mime.type",description = "Always set to application/json")
@InputRequirement(InputRequirement.Requirement.INPUT_REQUIRED)
@CapabilityDescription("Applies a Mirai json to the flowfile JSON payload. A new FlowFile is created "
        + "with transformed content and is routed to the 'success' relationship. If the JSON transform "
        + "fails, the original FlowFile is routed to the 'failure' relationship.")
public class MiraiTranformationToKafka extends AbstractProcessor {
	
	HashMap<String, String> sensorMap;
	
	//public static final String JSON_ATTRIBUTE_NAME = "JSONAttributes";

    public static final PropertyDescriptor SENSOR_LIST = new PropertyDescriptor
            .Builder().name("SENSOR_LIST")
            .displayName("Sensor list")
            .description("mirai sensors name for transformation")
            .required(true)
            .addValidator(StandardValidators.NON_EMPTY_VALIDATOR)
            .build();


    static final PropertyDescriptor TRANSFORM_CACHE_SIZE = new PropertyDescriptor.Builder()
            .name("Transform Cache Size")
            .description("Compiling a Mirai Transform can be fairly expensive. Ideally, this will be done only once. However, if the Expression Language is used in the transform, we may need "
                + "a new Transform for each FlowFile. This value controls how many of those Transforms we cache in memory in order to avoid having to compile the Transform each time.")
            .expressionLanguageSupported(ExpressionLanguageScope.NONE)
            .addValidator(StandardValidators.POSITIVE_INTEGER_VALIDATOR)
            .defaultValue("1")
            .required(true)
            .build();
    
    public static final Relationship REL_SUCCESS = new Relationship.Builder()
            .name("success")
            .description("The FlowFile with transformed content will be routed to this relationship")
            .build();
    public static final Relationship REL_FAILURE = new Relationship.Builder()
            .name("failure")
            .description("If a FlowFile fails processing for any reason (for example, the FlowFile is not valid JSON), it will be routed to this relationship")
            .build();
    
    private List<PropertyDescriptor> descriptors;

    private Set<Relationship> relationships;

    @Override
    protected void init(final ProcessorInitializationContext context) {
        final List<PropertyDescriptor> descriptors = new ArrayList<PropertyDescriptor>();
        descriptors.add(SENSOR_LIST);
        descriptors.add(TRANSFORM_CACHE_SIZE);
        this.descriptors = Collections.unmodifiableList(descriptors);

        final Set<Relationship> relationships = new HashSet<Relationship>();
        relationships.add(REL_SUCCESS);
        relationships.add(REL_FAILURE);
        this.relationships = Collections.unmodifiableSet(relationships);
    }

    @Override
    public Set<Relationship> getRelationships() {
        return this.relationships;
    }

    @Override
    public final List<PropertyDescriptor> getSupportedPropertyDescriptors() {
        return descriptors;
    }

   
    
    @OnScheduled
    public void onScheduled(final ProcessContext context) {
    	
		/*
		 * logger.info("context is :"+context.getProperties());
		 * logger.info("sensor string is :"+context.getProperty(SENSOR_LIST).getValue().
		 * toString());
		 * sensorMap=gson.fromJson(context.getProperty(SENSOR_LIST).getValue().toString(
		 * ), new TypeToken<HashMap<String, String>>() {}.getType());
		 * 
		 * sensorMap.entrySet().forEach(entry->{
		 * 
		 * logger.info("senors are :"+entry.getKey() + " " + entry.getValue());
		 * System.out.println("senors are :"+entry.getKey() + " " + entry.getValue());
		 * });
		 */
    
    }
    
    @Override
    public void onTrigger(final ProcessContext context, final ProcessSession session) throws ProcessException {
    	
    	final ComponentLog logger = getLogger();
    	 
    	FlowFile original = session.get();
        if ( original == null ) {
            return;
        }
    	 Gson gson=new Gson();
         final StopWatch stopWatch = new StopWatch(true);
         logger.info("context is :"+context.getProperties());
         sensorMap=gson.fromJson(context.getProperty(SENSOR_LIST).getValue().toString(), new TypeToken<HashMap<String, String>>() {}.getType());
     	
         
        
        
        final JsonElement inputJson;
        try (final InputStream in = session.read(original)) {
        	
        	String result = IOUtils.toString(in, StandardCharsets.UTF_8);
        	JsonParser  parser = new JsonParser();
	         inputJson   = parser.parse(result);

           
        } catch (final Exception e) {
            logger.error("Failed to transform {}; routing to failure", new Object[] {original, e});
            session.transfer(original, REL_FAILURE);
            return;
        }
        
        final String jsonString;
       
        try {
        	sensorMap.entrySet().forEach(entry->{
        		
				logger.info("senors are :"+entry.getKey() + " " + entry.getValue());  
			 });
       
         
            jsonString = parseJson(inputJson,sensorMap);
            logger.info("tranformed json string is:"+jsonString);
        } catch (final Exception ex) {
        	ex.printStackTrace();
            logger.error("Unable to transform {} due to {}", new Object[] {original, ex.toString(), ex});
            session.transfer(original, REL_FAILURE);
            return;
        }
        
        FlowFile transformed = session.write(original, new OutputStreamCallback() {
            @Override
            public void process(OutputStream out) throws IOException {
                out.write(jsonString.getBytes(StandardCharsets.UTF_8));
            }
        	});
        
        transformed = session.putAttribute(transformed, CoreAttributes.MIME_TYPE.key(), "application/json");
        session.transfer(transformed, REL_SUCCESS);
        
        session.getProvenanceReporter().modifyContent(transformed,"Modified With Mirai transformation" ,stopWatch.getElapsed(TimeUnit.MILLISECONDS));
        logger.info("Transformed {}", new Object[]{original});
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
		       transformedJson= transformedJson.replaceAll("\\\\", "").replaceAll("(\"\\{\")","\\{\"").replaceAll("(\"\\}\")","\"\\}").replaceAll("\\}\"", "\\}");

			
			}  catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return transformedJson;
		}
    
    public String	parseJsonV1(JsonElement jsonstring, HashMap<String, String> sensorMap2){
		
    	String transformedJson1=null;
			try {
			//	MiraiBean miraiob=new MiraiBean();
				MiraiCoulumnsBean miraicolumns=new MiraiCoulumnsBean();
				
				JsonArray transformedjsonArray=new JsonArray();
		        JsonArray elemArr = jsonstring.getAsJsonArray();
		       for (JsonElement jsonElement : elemArr) {
		    	   
		    	   String transformedJson=null; 
		    	   JsonObject jb=jsonElement.getAsJsonObject();
		    	   
		    	   String sensorName=jb.get("Sensor").toString().replace("\"", "");
		    	   
		    	   if(sensorName.equals(sensorMap2.get("limit_switch_unit_1_valve_1"))) {
		    		   
		    		   miraicolumns.setMeasurunit_symbol("Boolean");
		    		   miraicolumns.setUnit_id(1);
		    		   miraicolumns.setSensor_name("lm0001");
		    		   miraicolumns.setSenosor_measurunit_id(15);
		    		   
		    		   if(jb.get("Value").getAsInt()==1) {
		    			   
		    			   miraicolumns.setSwitch_level("Open");	   
		    		  		    		   
		    		   }else {
		    			   miraicolumns.setSwitch_level("Open");	   
		    		   }
		    	   }else if(sensorName.equals(sensorMap2.get("limit_switch_unit_1_valve_2"))) {
		    		   miraicolumns.setMeasurunit_symbol("Boolean");
		    		   miraicolumns.setUnit_id(1);
		    		   miraicolumns.setSensor_name("lm0002");
		    		   miraicolumns.setSenosor_measurunit_id(16);
		    		   	if(jb.get("Value").getAsInt()==1) {
		    			   
		    			   miraicolumns.setSwitch_level("Open");	   
		    		  		    		   
		    		   }else {
		    			   miraicolumns.setSwitch_level("Open");	   
		    		   }
		    	   }else if(sensorName.equals(sensorMap2.get("limit_switch_unit_2_valve_1"))) {
		    		   miraicolumns.setMeasurunit_symbol("Boolean");
		    		   miraicolumns.setUnit_id(2);
		    		   miraicolumns.setSensor_name("lm0003");
		    		   miraicolumns.setSenosor_measurunit_id(17);
		    		   	if(jb.get("Value").getAsInt()==1) {
		    			   
		    			   miraicolumns.setSwitch_level("Open");	   
		    		  		    		   
		    		   }else {
		    			   miraicolumns.setSwitch_level("Open");	   
		    		   }
		    	   }else if(sensorName.equals(sensorMap2.get("limit_switch_unit_2_valve_2"))) {
		    		   miraicolumns.setMeasurunit_symbol("Boolean");
		    		   miraicolumns.setUnit_id(1);
		    		   miraicolumns.setSensor_name("lm0004");
		    		   miraicolumns.setSenosor_measurunit_id(18);
		    		   if(jb.get("Value").getAsInt()==1) {
		    			   miraicolumns.setSwitch_level("Open");	   
			    		   }else {
			    			   miraicolumns.setSwitch_level("Open");	   
			    		   }
		    	   }else if(sensorName.equals(sensorMap2.get("motor_temp_unit_1"))) {
		    		   miraicolumns.setMeasurunit_symbol("degree celcius");
		    		   miraicolumns.setUnit_id(1);
		    		   miraicolumns.setSensor_name("t0001");
		    		   miraicolumns.setSenosor_measurunit_id(3);
		    		   miraicolumns.setTemp(jb.get("Value").getAsFloat()/10);
		    		
		    	   }else if(sensorName.equals(sensorMap2.get("pump_temp_unit_1"))) {
		    		   miraicolumns.setMeasurunit_symbol("degree celcius");
		    		   miraicolumns.setUnit_id(1);
		    		   miraicolumns.setSensor_name("t0002");
		    		   miraicolumns.setSenosor_measurunit_id(4);
		    		   miraicolumns.setTemp(jb.get("Value").getAsFloat()/10);
		    		   
		    	   }else if(sensorName.equals(sensorMap2.get("motor_temp_unit_2"))) {
		    		   miraicolumns.setMeasurunit_symbol("degree celcius");
		    		   miraicolumns.setUnit_id(2);
		    		   miraicolumns.setSensor_name("t0003");
		    		   miraicolumns.setSenosor_measurunit_id(5);
		    		   miraicolumns.setTemp(jb.get("Value").getAsFloat()/10);
		    		  
		    	   }else if(sensorName.equals(sensorMap2.get("pump_temp_unit_2"))) {
		    		   miraicolumns.setMeasurunit_symbol("degree celcius");
		    		   miraicolumns.setUnit_id(2);
		    		   miraicolumns.setSensor_name("t0004");
		    		   miraicolumns.setSenosor_measurunit_id(6);
		    		   miraicolumns.setTemp(jb.get("Value").getAsFloat()/10);
		    		  
		    	   }else if(sensorName.equals(sensorMap2.get("motor_vibration_unit_1"))) {
		    		   miraicolumns.setMeasurunit_symbol("mm/sec");
		    		   miraicolumns.setUnit_id(1);
		    		   miraicolumns.setSensor_name("v0001");
		    		   miraicolumns.setSenosor_measurunit_id(7);
		    		   miraicolumns.setTemp(jb.get("Value").getAsFloat()/100);
		    		  // miraiob.setMotor_vibration_unit_1(jb.get("Value").getAsFloat()/100);
		    	   }else if(sensorName.equals(sensorMap2.get("pump_vibration_unit_1"))) {
		    		   miraicolumns.setMeasurunit_symbol("mm/sec");
		    		   miraicolumns.setUnit_id(1);
		    		   miraicolumns.setSensor_name("v0002");
		    		   miraicolumns.setSenosor_measurunit_id(8);
		    		   miraicolumns.setTemp(jb.get("Value").getAsFloat()/100);
		    		   //miraiob.setPump_vibration_unit_1(jb.get("Value").getAsFloat()/100);
		    	   }else if(sensorName.equals(sensorMap2.get("motor_vibration_unit_2"))) {
		    		   miraicolumns.setMeasurunit_symbol("mm/sec");
		    		   miraicolumns.setUnit_id(2);
		    		   miraicolumns.setSensor_name("v0003");
		    		   miraicolumns.setSenosor_measurunit_id(9);
		    		   miraicolumns.setTemp(jb.get("Value").getAsFloat()/100);
		    		   //miraiob.setMotor_vibration_unit_2(jb.get("Value").getAsFloat()/100);
		    	   }else if(sensorName.equals(sensorMap2.get("pump_vibration_unit_2"))) {
		    		   miraicolumns.setMeasurunit_symbol("mm/sec");
		    		   miraicolumns.setUnit_id(2);
		    		   miraicolumns.setSensor_name("v0004");
		    		   miraicolumns.setSenosor_measurunit_id(10);
		    		   miraicolumns.setTemp(jb.get("Value").getAsFloat()/100);
		    		  // miraiob.setPump_vibration_unit_2(jb.get("Value").getAsFloat()/100);
		    	   }
		    	   
		    	   else if(sensorName.equals(sensorMap2.get("motor_pressure_unit_1"))) {
		    		   miraicolumns.setMeasurunit_symbol("kPa");
		    		   miraicolumns.setUnit_id(1);
		    		   miraicolumns.setSensor_name("p0001");
		    		   miraicolumns.setSenosor_measurunit_id(11);
		    		   miraicolumns.setPressure(jb.get("Value").getAsFloat()/10);
		    		   //miraiob.setMotor_pressure_unit_1(jb.get("Value").getAsFloat()/10);
		    	   }else if(sensorName.equals(sensorMap2.get("pump_pressure_unit_1"))) {
		    		   miraicolumns.setMeasurunit_symbol("kPa");
		    		   miraicolumns.setUnit_id(1);
		    		   miraicolumns.setSensor_name("p0002");
		    		   miraicolumns.setSenosor_measurunit_id(12);
		    		   miraicolumns.setPressure(jb.get("Value").getAsFloat()/10);
		    		//   miraiob.setPump_pressure_unit_1(jb.get("Value").getAsFloat()/10);
		    	   }else if(sensorName.equals(sensorMap2.get("motor_pressure_unit_2"))) {
		    		   miraicolumns.setMeasurunit_symbol("kPa");
		    		   miraicolumns.setUnit_id(2);
		    		   miraicolumns.setSensor_name("p0003");
		    		   miraicolumns.setSenosor_measurunit_id(13);
		    		   miraicolumns.setPressure(jb.get("Value").getAsFloat()/10);
		    		   //miraiob.setMotor_pressure_unit_2(jb.get("Value").getAsFloat()/10);
		    	   }else if(sensorName.equals(sensorMap2.get("pump_pressure_unit_2"))) {
		    		   miraicolumns.setMeasurunit_symbol("kPa");
		    		   miraicolumns.setUnit_id(2);
		    		   miraicolumns.setSensor_name("p0004");
		    		   miraicolumns.setSenosor_measurunit_id(14);
		    		   miraicolumns.setPressure(jb.get("Value").getAsFloat()/10);
		    		 //  miraiob.setPump_pressure_unit_2(jb.get("Value").getAsFloat()/10);
		    	   }
		    	   
		    	   else if(sensorName.equals(sensorMap2.get("water_level_tank_1"))) {
		    		   miraicolumns.setMeasurunit_symbol("percentage");
		    		   miraicolumns.setUnit_id(1);
		    		   miraicolumns.setSensor_name("l0001");
		    		   miraicolumns.setSenosor_measurunit_id(1);
		    		   miraicolumns.setWater_level(jb.get("Value").getAsFloat()/100);
		    		  // miraiob.setWater_level_tank_1(jb.get("Value").getAsFloat()/100);
		    	   }else if(sensorName.equals(sensorMap2.get("water_level_tank_2"))) {
		    		   miraicolumns.setMeasurunit_symbol("percentage");
		    		   miraicolumns.setUnit_id(2);
		    		   //miraiob.setWater_level_tank_2(jb.get("Value").getAsFloat()/100);
		    		   
		    		   miraicolumns.setSensor_name("l0002");
		    		   miraicolumns.setSenosor_measurunit_id(2);
		    		   miraicolumns.setWater_level(jb.get("Value").getAsFloat()/100);
		    	   } 
		    	   
		    	   else if(sensorName.equals(sensorMap2.get("flow_unit_1"))) {
		    		   miraicolumns.setMeasurunit_symbol("lpm");
		    		   miraicolumns.setUnit_id(1);
		    		   miraicolumns.setSensor_name("f0001");
		    		   miraicolumns.setSenosor_measurunit_id(13);
		    		   miraicolumns.setFlow_level(jb.get("Value").getAsFloat()/100);
		    		  // miraiob.setFlow_unit_1(jb.get("Value").getAsFloat()/100);
		    	   }else if(sensorName.equals(sensorMap2.get("flow_unit_2"))) {
		    		   miraicolumns.setMeasurunit_symbol("lpm");
		    		   miraicolumns.setUnit_id(2);
		    		   miraicolumns.setSensor_name("f0002");
		    		   miraicolumns.setSenosor_measurunit_id(14);
		    		   miraicolumns.setFlow_level(jb.get("Value").getAsFloat()/100);
		    		   //miraiob.setFlow_unit_2(jb.get("Value").getAsFloat()/100);
		    	   }
		    	   else if(sensorName.equals(sensorMap2.get("power_unit_1"))) {
		    		   miraicolumns.setMeasurunit_symbol("kW");
		    		   miraicolumns.setUnit_id(1);
		    		   miraicolumns.setSensor_name("ep0001");
		    		   miraicolumns.setSenosor_measurunit_id(23);
		    		   miraicolumns.setPower(jb.get("Value").getAsFloat());
		    		   //miraiob.setPower_unit_1(jb.get("Value").getAsFloat());
		    	   }else if(sensorName.equals(sensorMap2.get("power_unit_2"))) {
		    		   miraicolumns.setMeasurunit_symbol("kW");
		    		   miraicolumns.setUnit_id(2);
		    		   miraicolumns.setSensor_name("ep0002");
		    		   miraicolumns.setSenosor_measurunit_id(26);
		    		   miraicolumns.setPower(jb.get("Value").getAsFloat());
		    	   }
		    	   
		    	   else if(sensorName.equals(sensorMap2.get("voltage_unit_1"))) {
		    		   miraicolumns.setMeasurunit_symbol("Volt");	    		   
		    		   miraicolumns.setUnit_id(1);
		    		   miraicolumns.setSensor_name("ev0001");
		    		   miraicolumns.setSenosor_measurunit_id(21);
		    		   miraicolumns.setVoltage(jb.get("Value").getAsFloat()/100);
		    		  // miraiob.setVoltage_unit_1(jb.get("Value").getAsFloat()/10);
		    	   }else if(sensorName.equals(sensorMap2.get("voltage_unit_2"))) {
		    		   miraicolumns.setMeasurunit_symbol("Volt");
		    		   miraicolumns.setUnit_id(2);
		    		   miraicolumns.setSensor_name("ev0002");
		    		   miraicolumns.setSenosor_measurunit_id(24);
		    		   miraicolumns.setVoltage(jb.get("Value").getAsFloat()/100);
		    		  // miraiob.setVoltage_unit_2(jb.get("Value").getAsFloat()/10);
		    	   }
		    	   
		    	   else if(sensorName.equals(sensorMap2.get("current_unit_1"))) {
		    		   miraicolumns.setMeasurunit_symbol("");
		    		   miraicolumns.setUnit_id(1);
		    		   miraicolumns.setSensor_name("ec0001");
		    		   miraicolumns.setSenosor_measurunit_id(22);
		    		   miraicolumns.setVoltage(jb.get("Value").getAsFloat()/100);
		    		   
		    		   //miraiob.setCurrent_unit_1(jb.get("Value").getAsFloat()/100);
		    	   }else if(sensorName.equals(sensorMap2.get("current_unit_2"))) {
		    		   miraicolumns.setMeasurunit_symbol("");
		    		   miraicolumns.setUnit_id(2);
		    		   miraicolumns.setSensor_name("ec0002");
		    		   miraicolumns.setSenosor_measurunit_id(25);
		    		   miraicolumns.setVoltage(jb.get("Value").getAsFloat()/100);
		    		   //miraiob.setCurrent_unit_2(jb.get("Value").getAsFloat()/100);
		    	   }
		    	   
		    	   DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
					Date date = new Date();
		    	  // String dstring=jb.get("ctime").getAsString();
					String dstring=dateFormat.format(date);
			        Date currentdate = dateFormat.parse(dstring);
			        DateFormat datef = new SimpleDateFormat("yyyy-MM-dd");
			        DateFormat timef = new SimpleDateFormat("hh:mm:ss a");
			     
		    	   
		    	   
		    	   miraicolumns.setDate_value(datef.format(currentdate));
		    	   miraicolumns.setTime_value(timef.format(currentdate));
		    	  // miraiob.setcTime(jb.get("ctime").getAsString());
		    	   
		    	   
		    	   
		    	   Gson gson = new Gson(); 
		    	   transformedJson=gson.toJson(miraicolumns);
		    	 //  JSONParser parser = new JSONParser();
		    	  // JSONObject json = (JSONObject) parser.parse(transformedJson);
		    	   
			       
			        
			        
			        
			        transformedjsonArray.add(transformedJson);
			        
			        
			       
			}
		       
		       Gson gson = new Gson(); 
	    	   transformedJson1=gson.toJson(transformedjsonArray);   
	    	   
	    	   
			
			}  catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return transformedJson1;
		}
 

}
