package com.application.python3.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.application.python3.calculations.data.TrackSummary;
import com.application.python3.entities.Gite;
import com.application.python3.entities.GiteId;
import com.application.python3.openweather.data.LocationData;
import com.application.python3.repository.GiteRepository;
import com.application.python3.tracks.gpx.GpxTrack;
import com.mysql.cj.log.Log;

import lombok.extern.slf4j.Slf4j;


/* Business layer dell'applicativo */

@Service("giteService")
@ComponentScan("python3.application.repository")
@Slf4j
public class GiteService
{
	@Autowired 
	GiteRepository giteRepository;

	public List<Gite> listGite() {
		return giteRepository.findAll();
	}
	
	public Optional<Gite> findGiteById(String filtro) throws Exception
	{
		GiteId giteId=null;
		
		try
		{
			String[] aux=filtro.split(",");
			
			giteId=new GiteId(aux[0], Timestamp.valueOf(aux[1]), aux[2]);
		}
		catch (Exception ex)
		{
			log.error("Errore nel parsing dei parametri di ricerca.");
			throw new Exception(ex);
		}
			
		return giteRepository.findById(giteId);
	}

	public void insGite(TrackSummary trackSummary, GpxTrack gpxTrack, LocationData locationData, String loader) 
	{
		GiteId giteId=new GiteId(gpxTrack.getGpxTrk().getName(), Timestamp.valueOf(gpxTrack.getGpxMetadata().getDateAndTime()), loader);
		
		String user=System.getProperty("user.name");
		double climb=trackSummary.getClimb();
		double descent=trackSummary.getDescent();
		double distance=trackSummary.getDistance();
		long totalTime=trackSummary.getTotalTime();
		double maxHeight=trackSummary.getMaxHeight();
		String description=locationData.getWeather().getDescription();
		double temp=locationData.getMainResponse().getTemp();
		double feels_like=locationData.getMainResponse().getFeels_like();
		int humidity=locationData.getMainResponse().getHumidity();
		int deg=locationData.getWind().getDeg();
		double speed= locationData.getWind().getSpeed();
				
		Gite gite=new Gite(giteId,user,climb,descent,distance,totalTime,maxHeight,description,temp,feels_like,humidity,	deg,speed);
				
		giteRepository.save(gite);		
	}
}
