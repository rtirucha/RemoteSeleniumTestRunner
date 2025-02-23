package com.utils;

import java.util.HashMap;
import java.util.Map;

public class CucumberTestFeatureSelector {
	
	Map<String, String> map = new HashMap<>();
	public String featureFilePath = "C:\\Users\\rtiru\\eclipse-projects\\selenium-cucumber-java-master\\src\\test\\resources\\features\\";
	
	public void init() {
		map.put("LT_Individual","my_first11.feature");
		map.put("LT_Joint","my_first12.feature");
		
		map.put("EP_Individual","my_first21.feature");
		map.put("EP_Joint","my_first22.feature");
		
		map.put("DT_Individual","my_first31.feature");
		map.put("DT_Joint","my_first32.feature");
		
	}
	
	public String selectCucumberTestFeatureFile(String aggregator, String appType) {
		// TODO Auto-generated method stub
		String featurefileKey = String.format("%s_%s", aggregator,appType);
		System.out.println("feature file key : "+featurefileKey);
		
		return featureFilePath+map.get(featurefileKey);
	}
	
	public CucumberTestFeatureSelector() {
		init();
	}

}
