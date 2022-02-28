package com.application.python3.tracks.gpx;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/* Sezione del tracciato .gpx */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GpxTrk 
{
	private String name;
	private int type;
	private ArrayList<GpxTrkPt> trkseg;
	
	public GpxTrk(String name, int type) 
	{
		super();
		this.name = name;
		this.type = type;
		this.trkseg=new ArrayList<GpxTrkPt>();
	}
	
	public void addGpxTrkPt(GpxTrkPt item)
	{
		if (this.trkseg==null)
			this.trkseg=new ArrayList<GpxTrkPt>();
		
		this.trkseg.add(item);
	}
}
