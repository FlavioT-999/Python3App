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
public class MainResponse 
{
	Double temp;
    Double feels_like;
    Double temp_min;
    Double temp_max;
    int pressure;
    int humidity;
    
	@Override
	public String toString() {
		return "Main [temp=" + temp + ", feels_like=" + feels_like + ", temp_min=" + temp_min + ", temp_max=" + temp_max
				+ ", pressure=" + pressure + ", humidity=" + humidity + "]";
	}
}
