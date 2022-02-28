package com.application.python3.calculations.tools;

import static org.junit.Assert.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.xml.sax.SAXException;

import com.application.python3.calculations.data.TrackSummary;
import com.application.python3.calculations.tools.Calculator;
import com.application.python3.tracks.gpx.GpxTrack;
import com.application.python3.xmlhandlers.GpxXmlHandler;


@RunWith(Parameterized.class)
public class CalculatorTest {

	private String fileUri;
	private TrackSummary expectedResult;
	
	@Parameterized.Parameters
	public static Collection dataset()
	{
		return (Collection) Arrays.asList(new Object[][] {
			{"C:\\Users\\ghana\\Desktop\\gps_project\\tracks\\gpx\\Odla_de_Valdussa.gpx",new TrackSummary(26018.42, 2211.0, 2495.5,35005L, 9218, 2842.1 )},
			{"C:\\Users\\ghana\\Desktop\\gps_project\\tracks\\gpx\\Forcella_Ciamin_Fedara_Vedla.gpx",new TrackSummary(23456.62, 2762.0, 1890.3,40005L, 8310, 2642.1 )}
		});
	}
	
	public CalculatorTest(String fileUri, TrackSummary expectedResult) 
	{
		super();
		this.fileUri=fileUri;
		this.expectedResult=expectedResult;
	}
	
	 @Test
	public void testExecute() 
	{
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
    	
		GpxXmlHandler handler=null;
		
		SAXParser saxParser;
		try 
		{
			saxParser = saxParserFactory.newSAXParser();
			handler = new GpxXmlHandler();
			saxParser.parse(new File(fileUri), handler);
		} 
		catch (ParserConfigurationException e) 
		{
			fail("Bad file parsing.");
		} 
		catch (SAXException e) 
		{
			fail("Bad file parsing.");
		} 
		catch (IOException e) 
		{
			fail("Bad file parsing.");
		}
	        	        
	    GpxTrack gpxTrack = handler.getGpxTrack();
	    
	    Calculator calculator=new Calculator(gpxTrack);
	    
	   	TrackSummary trackSummary=calculator.execute();
		
	   	TrackSummary aux=this.expectedResult;
	   	
	   	assertTrue(trackSummary.equals(aux));
	}

}
