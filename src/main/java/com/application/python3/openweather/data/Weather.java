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
public class Weather 
{
	private int id=-1;
	private String mainS="";
	private String description="";
	private String icon="";	

	@Override
	public String toString() {
		return "Weather [id=" + this.id + ", main=" + this.mainS + ", description=" + 
				this.description + ", icon=" + this.icon + "]";
	}

	
}
