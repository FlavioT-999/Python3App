package com.application.python3.tracks.gpx;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/* Sezione del tracciato .gpx */


@NoArgsConstructor
public class GpxTrkPt 
{
	@Getter
	@Setter
	private Double lat;

	@Getter
	@Setter
	private Double lon;

	@Getter
	@Setter	
	private Double ele;
	
	
	@Getter
	@Setter
	private LocalDateTime  time;
	
	@Getter
	private String strTime;
		
	
	public void setStrTime(String strTime) {
		this.strTime = strTime.replace("T", " ").replace("Z", "");
	}
	
	
	public GpxTrkPt(Double lat, Double lon, Double ele, String strTime) {
		super();
		this.lat = lat;
		this.lon = lon;
		this.ele = ele;
		this.strTime = strTime.replace("T", " ").replace("Z", "");
		this.time = LocalDateTime.parse(this.strTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
	
	public void convertTime()
	{
		this.time = LocalDateTime.parse(this.strTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
}
