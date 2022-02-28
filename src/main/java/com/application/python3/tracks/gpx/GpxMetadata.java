package com.application.python3.tracks.gpx;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/* Sezione del tracciato .gpx */

@NoArgsConstructor
public class GpxMetadata 
{
	@Getter
	private String strTime;
	
	@Getter
	@Setter
	private LocalDateTime   dateAndTime;
		
	
	public void setStrTime(String strTime) {
		this.strTime = strTime.replace("T", " ").replace("Z", "");
	}
	
	public GpxMetadata(String strTime) {
		super();
		this.strTime = strTime.replace("T", " ").replace("Z", "");
	}
		
	public void convert()
	{
		this.dateAndTime = LocalDateTime.parse(strTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
	
	
}
