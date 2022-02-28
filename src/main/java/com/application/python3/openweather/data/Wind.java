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
public class Wind 
{
	private Double speed;
	private int deg;
	
	@Override
	public String toString() {
		return "Wind [speed=" + speed + ", deg=" + deg + "]";
	}
}
