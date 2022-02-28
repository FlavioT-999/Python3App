package com.application.python3.openweather.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/* Client del web service OpenWeather */

@Configuration
@ConfigurationProperties(prefix = "com.application.python3.openweatherclient")
public class OpenWeatherClient
{
	//@Value("${com.application.python3.openweatherclient.wsurl}")
	String wsurl;
	
	//@Value("${com.application.python3.openweatherclient.app_id}")
	String appid;
	
	public String getWeatherData(Double lat, Double lon)
	{
		HttpURLConnection conn=null;
		String json="";
		
		try 
		{	
			String dLat=lat.toString();
			String dLon=lon.toString();
					
			String urlString=this.wsurl + "?lat=%s&lon=%s&app_id=" + this.appid;
					
			String formattedUrlString=String.format(urlString, dLat, dLon);
			
			URL url=new URL(formattedUrlString);
			/*
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getForObject(urlString, LocationData.class);
			*/
			
		    conn = (HttpURLConnection) url.openConnection();
		    conn.setRequestMethod("GET");
		    conn.setRequestProperty("Accept", "application/json");
		    if (conn.getResponseCode() != 200) {
		        throw new RuntimeException("Failed : HTTP Error code : "
		                + conn.getResponseCode());
		    }
		    InputStreamReader in = new InputStreamReader(conn.getInputStream());
		    
		    BufferedReader br = new BufferedReader(in);
		    
		    String output;
		    while ((output = br.readLine()) != null) {
		        json+=output;
		    }
		} 
		catch (Exception e) 
		{
		    System.out.println("Exception in OpenWeatherClient:- " + e);
		}
		finally
		{
			conn.disconnect();
		}
			
		return json;
	}
}