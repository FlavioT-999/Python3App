package com.application.python3.jsonhandler;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

import com.application.python3.openweather.data.Cohordinates;
import com.application.python3.openweather.data.LocationData;
import com.application.python3.openweather.data.MainResponse;
import com.application.python3.openweather.data.Weather;
import com.application.python3.openweather.data.Wind;

/* Parser del json prodotto dal web service. Il Json viene mappato sulla classe LocationData */

public class WsJsonHandler {
	
	public LocationData execute(String json)
	{
		JsonParser springParser = (JsonParser) JsonParserFactory.getJsonParser();
	    Map < String, Object > map = springParser.parseMap(json);
		
	    Map <String, Object > mapCoor=(Map<String, Object>) map.get("coord");
	    Cohordinates cohordinates= new Cohordinates(Double.parseDouble(mapCoor.get("lon").toString()),
	    											Double.parseDouble(mapCoor.get("lat").toString()));
	    
	    LinkedHashMap<String,Object> mapWeather=(LinkedHashMap<String,Object>)((ArrayList<Object>) map.get("weather")).get(0);
	    //Weather weather=null;
	    Weather weather= new Weather(Integer.parseInt(mapWeather.get("id").toString()),	// id
	    								  mapWeather.get("main").toString(),	//main
	    								  mapWeather.get("description").toString(),	//description	
	    								  mapWeather.get("icon").toString());	///icon
	    		
	    
	    LinkedHashMap<String,Object> mapMainResponse=(LinkedHashMap<String,Object>)map.get("main");
	    MainResponse mainResponse=new MainResponse(Double.parseDouble(mapMainResponse.get("temp").toString()),
	    										   Double.parseDouble(mapMainResponse.get("feels_like").toString()),
	    										   Double.parseDouble(mapMainResponse.get("temp_min").toString()),
	    										   Double.parseDouble(mapMainResponse.get("temp_max").toString()),
	    										   Integer.parseInt(mapMainResponse.get("pressure").toString()),
	    										   Integer.parseInt(mapMainResponse.get("humidity").toString()));		   
	    		
	    		
	    Map < String, Object > mapWind=(Map<String, Object>) map.get("wind");
	    Wind wind=new Wind(Double.parseDouble(mapWind.get("speed").toString()),
	    				   Integer.parseInt(mapWind.get("deg").toString()));
	    		
	    	
	    LocationData locationData=new LocationData(cohordinates, weather, mainResponse, wind);
	
	    return locationData;
	}
}
