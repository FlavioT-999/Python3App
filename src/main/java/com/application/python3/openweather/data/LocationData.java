package com.application.python3.openweather.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/* Classe che mappa il json prodotto dal web service OpenWeather */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationData 
{
	private Cohordinates cohordinates;
	private Weather weather;
	private MainResponse mainResponse;
	private Wind wind;
	
	@Override
	public String toString() {
		return "LocationData [cohordinates=" + this.cohordinates.toString() + ", weather=" + this.weather.toString() + 
				", mainResponse=" + this.mainResponse.toString() + ", wind=" + this.wind.toString() + "]";
	}
		
}
