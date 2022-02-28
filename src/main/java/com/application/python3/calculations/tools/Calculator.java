package com.application.python3.calculations.tools;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.apache.commons.math3.util.Precision;

import com.application.python3.calculations.data.TrackSummary;
import com.application.python3.tracks.gpx.GpxTrack;
import com.application.python3.tracks.gpx.GpxTrkPt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


/* Classe che calcola i dati salienti dell'escursione */

@Getter
@Setter
@NoArgsConstructor
public class Calculator 
{
	private TrackSummary trackSummary;
	private GpxTrack gpxTrack;
	
	public Calculator(GpxTrack gpxTrack) 
	{
		super();
		this.gpxTrack = gpxTrack;
	}
	
	public TrackSummary execute()
	{
		Double distance=0.0;
		Double climb=0.0;
		Double descent=0.0;
		Long totalTime=0L;
		Double deltaHeight;
		Double maxHeight=0.0;
		int maxHeightPosition=0;  // questo campo segnala l'indice del tracciato gps cui corrisponde la massima altezza
		
		int noPoints=this.gpxTrack.getGpxTrk().getTrkseg().size();
		
		for (int i=0; i<(noPoints-1); i++)
		{
			distance+=this.calculateDistanceBetweenTwoPoints(i);
			
			deltaHeight=round(this.deltaHeight(i));
			
			if (deltaHeight>0)
				climb+=deltaHeight;
			else
				descent+=Math.abs(deltaHeight);
			
			if (this.changeMaxHeight(i, maxHeight))
			{
				maxHeight=this.gpxTrack.getGpxTrk().getTrkseg().get(i).getEle();
				maxHeightPosition=i;
			}		
			
		}
		
		totalTime=this.calculateTotalTime()	;	
		
		distance=round(distance);
		climb=round(climb);
		descent=round(descent);
		maxHeight=round(maxHeight);			
		
		this.trackSummary=new TrackSummary(distance, climb, descent, totalTime, maxHeightPosition, maxHeight);
		
		return this.trackSummary;
	}
	
	private Double calculateDistanceBetweenTwoPoints(int i)
	{
		GpxTrkPt trkPt1=this.gpxTrack.getGpxTrk().getTrkseg().get(i);
		GpxTrkPt trkPt2=this.gpxTrack.getGpxTrk().getTrkseg().get(i+1);
		
		Double lat1=trkPt1.getLat();
		Double lat2=trkPt2.getLat();
		Double lon1=trkPt1.getLon();
		Double lon2=trkPt2.getLon();
		
		
		Double phi1 = lat1 * Math.PI/180; 
		Double phi2 = lat2* Math.PI/180;
		Double lmbd1= lon1* Math.PI/180;
		Double lmbd2= lon2* Math.PI/180;
		
		Double deltaLamba = (lon2-lon1) * Math.PI/180;
		
		Double x = (lmbd2-lmbd1) * Math.cos((phi1+phi2)/2);
		Double y = (phi2-phi1);
		Double d = Math.sqrt(x*x + y*y) * Constants.R;
		
		return d; 
	}
	
	private Double deltaHeight(int i)
	{
		GpxTrkPt trkPt1=this.gpxTrack.getGpxTrk().getTrkseg().get(i);
		GpxTrkPt trkPt2=this.gpxTrack.getGpxTrk().getTrkseg().get(i+1);
		
		Double height1=trkPt1.getEle();
		Double height2=trkPt2.getEle();
		
		return height2-height1;		
	}
	
	private boolean changeMaxHeight(int i, Double maxHeight)
	{
		GpxTrkPt trkPt=this.gpxTrack.getGpxTrk().getTrkseg().get(i);
		
		Double ele=trkPt.getEle();
		
		return (ele>maxHeight);
	}
	
	private Long calculateTotalTime()
	{
		int noPoints=this.gpxTrack.getGpxTrk().getTrkseg().size()-1;
		
		LocalDateTime t1=this.gpxTrack.getGpxTrk().getTrkseg().get(0).getTime();
		LocalDateTime t2=this.gpxTrack.getGpxTrk().getTrkseg().get(noPoints).getTime();
		
		long tt1=t1.toInstant(ZoneOffset.ofHours(0)).getEpochSecond();
		long tt2=t2.toInstant(ZoneOffset.ofHours(0)).getEpochSecond();
		
		return (tt2-tt1);		
	}
	
	private Double round(Double x)
	{	
		double y=x;
		
		double z=(Precision.round(y, 2));
		
		return new Double(z);
	}
}

