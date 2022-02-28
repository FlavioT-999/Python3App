package com.application.python3.tracks.gpx;

import com.application.python3.tracks.Track;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/* Classe che mappa i tracciati .gpx */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GpxTrack extends Track 
{
	private GpxMetadata gpxMetadata;
	private GpxTrk gpxTrk;
}
