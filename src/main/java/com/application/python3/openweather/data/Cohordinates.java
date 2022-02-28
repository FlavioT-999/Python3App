package com.application.python3.openweather.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/* Mapping di una sezione del json prodotto dal web service OpenWeather */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cohordinates 
{
	private Double lon=0.0;
	private Double lat=0.0;
		
	@Override
	public String toString() {
		return "Cohordinates [lon=" + this.lon + ", lat=" + this.lat + ", toString()=" + super.toString() + "]";
	}
}
