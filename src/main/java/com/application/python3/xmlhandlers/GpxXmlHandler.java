package com.application.python3.xmlhandlers;


import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.application.python3.tracks.gpx.GpxMetadata;
import com.application.python3.tracks.gpx.GpxTrack;
import com.application.python3.tracks.gpx.GpxTrk;
import com.application.python3.tracks.gpx.GpxTrkPt;


/* Parser del file gpx */
public class GpxXmlHandler extends DefaultHandler
{
	public GpxXmlHandler() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public GpxTrack getGpxTrack()
	{
		return this.gpxTrack;
	}
	
	private GpxTrack gpxTrack=null;
	
	private GpxMetadata metadata=null;
	private GpxTrkPt gpxTrkPt=null;
	private GpxTrk gpxTrk=null;
		
	private boolean bGpx=false;
	private boolean bMeta=false;
	private boolean bMetaTime=false;
	private boolean bPtTime=false;
	private boolean bTrk=false;
	private boolean bName=false;
	private boolean bType=false;
	private boolean bTrkseg=false;
	private boolean bTrkpt=false;
	private boolean bEle=false;
	private boolean bTime=false;
		
	private StringBuilder data = null;
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		if (qName.equalsIgnoreCase("gpx")) 
		{
			this.gpxTrack = new GpxTrack();
			this.bGpx=true;
		} 		
		if (qName.equalsIgnoreCase("metadata")) 
		{
			this.metadata = new GpxMetadata();
			this.bMeta=true;
		} 
		else if (qName.equalsIgnoreCase("time") && this.bMeta) 
		{
			this.bMetaTime=true;
		} 
		else if (qName.equalsIgnoreCase("time") && !(this.bMeta)) 
		{
			this.bPtTime=true;		
		} 
		else if (qName.equalsIgnoreCase("trk")) 
		{
			this.gpxTrk=new GpxTrk();
			this.bTrk=true;
		} 
		else if (qName.equalsIgnoreCase("name")) 
		{
			this.bName=true;
		} 
		else if (qName.equalsIgnoreCase("type")) 
		{
			this.bType=true;
		}
		else if (qName.equalsIgnoreCase("trkseg")) 
		{
			this.bTrkseg=true;
		} 
		else if (qName.equalsIgnoreCase("trkpt")) 
		{
			this.gpxTrkPt=new GpxTrkPt();
			this.gpxTrkPt.setLat(Double.parseDouble(attributes.getValue("lat")));
			this.gpxTrkPt.setLon(Double.parseDouble(attributes.getValue("lon")));
			this.bTrkpt=true;
		}
		else if (qName.equalsIgnoreCase("ele")) 
		{
			this.bEle=true;
		} 
		else if (qName.equalsIgnoreCase("trkseg")) 
		{
			this.bTime=true;
		} 
			
		this.data = new StringBuilder();
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException 
	{
		if (qName.equalsIgnoreCase("gpx")) 
		{
			this.bGpx=false;
		} 
		if (qName.equalsIgnoreCase("metadata")) 
		{
			this.gpxTrack.setGpxMetadata(this.metadata);
			this.bMeta=false;
		} 
		else if (qName.equalsIgnoreCase("time") && this.bMeta) 
		{
			this.metadata = new GpxMetadata(this.data.toString());
			this.metadata.convert();
			this.bMetaTime=false;
		} 
		else if (qName.equalsIgnoreCase("time") && !(this.bMeta)) 
		{
			this.gpxTrkPt.setStrTime(this.data.toString());
			this.gpxTrkPt.convertTime();
			this.bPtTime=false;
		} 
		else if (qName.equalsIgnoreCase("trk")) 
		{
			this.gpxTrack.setGpxTrk(this.gpxTrk);
			this.bTrk=false;
		} 
		else if (qName.equalsIgnoreCase("name")) 
		{
			this.gpxTrk.setName(this.data.toString());
			this.bName=false;
		} 
		else if (qName.equalsIgnoreCase("type")) 
		{
			this.gpxTrk.setType(Integer.parseInt(this.data.toString()));
			this.bType=false;
		}
		else if (qName.equalsIgnoreCase("trkseg")) 
		{
			this.bTrkseg=true;
		} 
		else if (qName.equalsIgnoreCase("trkpt")) 
		{
			this.gpxTrk.addGpxTrkPt(this.gpxTrkPt);
			this.bTrkpt=false;
		}
		else if (qName.equalsIgnoreCase("ele")) 
		{
			this.gpxTrkPt.setEle(Double.parseDouble(data.toString()));
			this.bEle=false;
		} 
		else if (qName.equalsIgnoreCase("trkseg")) 
		{
			this.bTrkseg=false;
		} 
		
		
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		data.append(new String(ch, start, length));
	}
	
	
}
