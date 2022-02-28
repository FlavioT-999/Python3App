package com.application.python3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.application.python3.calculations.data.TrackSummary;
import com.application.python3.calculations.tools.Calculator;
import com.application.python3.csv.CsvPrinter;
import com.application.python3.entities.Gite;
import com.application.python3.jsonhandler.WsJsonHandler;
import com.application.python3.openweather.client.OpenWeatherClient;
import com.application.python3.openweather.data.LocationData;
import com.application.python3.service.GiteService;
import com.application.python3.tracks.gpx.GpxTrack;
import com.application.python3.xmlhandlers.GpxXmlHandler;

import lombok.extern.slf4j.Slf4j;

/* classe che contiene il metodo main dell'applicativo*/

@Slf4j
@SpringBootApplication
public class Python3 implements CommandLineRunner 
{
	@Autowired
	GiteService giteService;
	
	public static void main(String[] args) 
	{
		SpringApplication.run(Python3.class, args);
    }
 
    @Override
    public void run(String... args) 
    {
       	if (args.length!=0)
    	{
	    	String param=args[0];
	    		    	
	    	if (param.equals("-c"))
	    	{
	    		if (args.length==2)
	    		{
	    			try
	    			{
		    			String gpxDataUri=args[1];
		    			
		    			log.info("Inizio lettura dati.");
		    			
		    			GpxTrack gpxTrack=this.getTrack(gpxDataUri);
		    			
		    			log.info("Lettura dati effettuata.");
		    			
		    			log.info("Lettura inizio calcolo dati.");
		    					    					    			
		    			TrackSummary trackSummary = this.calculateTrackSummary(gpxTrack);
		    			
		    			log.info("Calcolo dati effettuato.");
		    					    			
		    			System.out.println(trackSummary.toString());
	    			}
	    			catch(Exception ex)
	    			{
	    				log.error(ex.getMessage() + " - " + ex.getStackTrace());
	    				System.out.println("Si è verificato un errore interno all'applicativo.");
	    			}	    			
	    		}
	    		else
	    		{
	    			log.warn("Numero parametri forniti errato.");
	    			System.out.println("Devi fornire la path della traccia GPX, e solo quella!");
	    		}
	    	}
	    	else if (param.equals("-w"))
	    	{
	    		if (args.length==3)
	    		{
	    			try
	    			{
		    			String gpxDataUri=args[1];
		    			String nomeCaricatore=args[2];
		    	
		    			log.info("Inizio lettura dati.");
		    			
		    			GpxTrack gpxTrack=this.getTrack(gpxDataUri);
		    			
		    			log.info("Lettura dati effettuata.");
		    			
		    			log.info("Lettura inizio calcolo dati.");
		    				    			
		    			TrackSummary trackSummary = this.calculateTrackSummary(gpxTrack);
		    			
		    			log.info("Calcolo dati effettuato.");
			    		
		    			log.info("Inizio lettura dati metereologici.");
			    			    			
		    			Double lat=gpxTrack.getGpxTrk().getTrkseg().get(trackSummary.getIndexMaxHeight()).getLat();
		    			Double lon=gpxTrack.getGpxTrk().getTrkseg().get(trackSummary.getIndexMaxHeight()).getLon();
		    			
		    			LocationData locationData=this.getLocationData(lat, lon);
		    			
		    			log.info("Lettura dati metereologici effettuta.");
			    		
		    			System.out.println(trackSummary.toString());
		    			
		    			log.info("Inserimento dati gita nel database.");
		    			
		    			giteService.insGite(trackSummary, gpxTrack, locationData, nomeCaricatore);
		    			
		    			log.info("Inserimento dati gita nel database effettuata.");
	    			}
	    			catch (Exception ex)
	    			{
	    				log.error(ex.getMessage() + " - " + ex.getStackTrace());
	    				System.out.println("Si è verificato un errore interno all'applicativo.");
	    			}
	    		}
	    		else
	    		{
	    			log.warn("Numero parametri forniti errato.");
	    			System.out.println("Devi fornire la path della traccia GPX e il nome di chi ha caricato i dati, in quest'ordine!");   			
	    		}	
	    	}	
	    	else if (param.equals("-r"))
	    	{
	    		if (args.length==1)
	    		{
	    			try 
	    			{
		    			log.info("Inizio lettura dati da database.");
		    			
		    			List<Gite> gite=giteService.listGite();
				        
		    			log.info("Lettura dati effettuta.");
		    			
		    			if (gite.size()!=0)
		    			{
					        for(Gite gita:gite)
					        {
					        	System.out.println(gita.toString());
					        }
			    			
			    			System.out.println("Vuoi stampare su file?");
			    			
			    			if (this.isAnswerYes())
			    			{
				    			try 
				    			{
				    				System.out.println("Inserisci la path del file di output:");
				    				
				    				String fileName=this.readFileName();
				    				
				    				System.out.println("Inserisci il delimitatore dei campi su file (se si vuole un file CSV, premere return:");
				    				
				    				char delimiter=this.readDelimiter();
				    								    				
				    				log.info("Inizio stampa dati su file.");
				    							
				    				CsvPrinter csvPrinter=null;
									
									if (delimiter==0)
										csvPrinter=new CsvPrinter(gite, fileName);
									else
										csvPrinter=new CsvPrinter(gite, fileName, delimiter);
				    				
									csvPrinter.print();
									
									log.info("Stampa dati su file effettuata.");
				    			} 
				    			catch (IOException ex) 
				    			{
				    				log.error(ex.getMessage() + " - " + ex.getStackTrace());
				    				throw new Exception(ex);
								}
			    			}
		    			}
		    			else 
		    				log.info("Nessun dato nel database.");
	    			}
	    			catch (Exception ex) 
	    			{
	    				log.error(ex.getMessage() + " - " + ex.getStackTrace());
	    				System.out.println("Si è verificato un errore interno all'applicativo.");
					}
	    		}
	    		else if (args.length==4)
	    		{
	    			String gpxDataUri=args[1];
	    			String dateTimeGita=args[2];
	    			String nomeCaricatore=args[3];
	    			try 
	    			{
	    				log.info("Inizio lettura dati.");
	    				
		    			Optional<Gite> gita=giteService.findGiteById(gpxDataUri + "," + dateTimeGita + "," + nomeCaricatore);		
				        
		    			Gite gitaX=gita.get();
		    			
		    			if (gitaX!=null)
		    			{
			    			log.info("Lettura dati effettuata.");
		    						    			
			    			System.out.println(gita.toString());
			
			    			System.out.println("Vuoi stampare su file?");
			    			
			    			if (this.isAnswerYes())
			    			{
			    				System.out.println("Inserisci la path del file di output:");
			    				
			    				String fileName=this.readFileName();
			    			
			    				System.out.println("Inserisci il delimitatore dei campi su file (se si vuole un file CSV, premere return:");
			    							    				
			    				char delimiter=this.readDelimiter();
			    				
			    				log.info("Inizio stampa dati su file.");
				    						    				
			    				List<Gite>giteX=new ArrayList<Gite>();
			    				giteX.add(gitaX);		
			    							    				
								CsvPrinter csvPrinter=null;
										
								if (delimiter==0)
									csvPrinter=new CsvPrinter(giteX, fileName);
								else
									csvPrinter=new CsvPrinter(giteX, fileName, delimiter);
																
								csvPrinter.print();
								
								log.info("Stampa dati su file effettuata.");
			    			}
		    			}
		    			else
		    				log.info("Nessun dato nel database.");
	    			}
	    			catch (Exception ex)
	    			{
	    				log.error(ex.getMessage() + " - " + ex.getStackTrace());
	    				System.out.println("Si è verificato un errore interno all'applicativo.");
	    			}		    			
		    	}
	    		else
	    		{
	    			log.warn("Numero parametri forniti errato.");
	    			System.out.println("Devi fornire la path della traccia GPX, la data della gita e il nome di chi ha caricato i dati, in quest'ordine!");
	    		}
	    	}
	    	else if (param.equals("-v"))
	    	{
	    		System.out.println();
	    		System.out.println("Per leggere un file .gpx e calcolare i dati salienti dell’escursione senza scriverli su db:");
	    		System.out.println("[-args]: -c [stringa:path del file gpx]");
	    		System.out.println("Per leggere un file .gpx, calcolare i dati salienti dell’escursione e serializzarli su database:");
	    		System.out.println("[-args]: -w [stringa:path del file gpx] [stringa:nome di chi carica i dati]");
	    		System.out.println("Per leggere da database e stampare su file i dati di tutte le escursioni:");
	    		System.out.println("[-args]: -r ");
	    		System.out.println("Per leggere da database e stampare su file i dati di una escursione specifica:");
	    		System.out.println("[-args]: -r [stringa:path file gpx] [stringa:timestamp gita] [stringa: chi ha caricato i dati]");	
	    		System.out.println("Per leggere le istruzioni d'uso dell'applicativo:");
	    		System.out.println("[-args]: -v");
	    	}
	    	else
	    	{
	    		log.warn("Parametro iniziale sconosciuto.");
    			System.out.println("Segui le istruzioni nel manuale.");
	    	}
	    }
       	else
       	{
       		log.warn("Numero parametri forniti errato.");
       		System.out.println("Segui le istruzioni nel manuale.");
       	}
    }
    
    private GpxTrack getTrack(String uri) throws Exception
    {
    	SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
    	GpxTrack gpxTrack = null;
    	
		try 
	    {
	        SAXParser saxParser = saxParserFactory.newSAXParser();
	        GpxXmlHandler handler = new GpxXmlHandler();
	        saxParser.parse(new File(uri), handler);
	        	        
	        gpxTrack = handler.getGpxTrack();
	    
	    }
		catch (Exception ex)
		{
			log.error("Errore nel parsing del file dati.");
			throw new Exception(ex);
		}
		
		return gpxTrack;
    }
        
	private TrackSummary calculateTrackSummary(GpxTrack gpxTrack)
	{
		Calculator calculator=new Calculator(gpxTrack);
		
		TrackSummary trackSummary=calculator.execute();
	    		
		return trackSummary;
	}
	
	private LocationData getLocationData(Double lat, Double lon)
	{
		OpenWeatherClient wsClient=new OpenWeatherClient();
		
		String json=wsClient.getWeatherData(lat, lon);
		
		WsJsonHandler wsJsonHandler=new WsJsonHandler();
		
		return wsJsonHandler.execute(json);
	}
	
	private boolean isAnswerYes() throws Exception
	{
		Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
		
        boolean result=(str.toLowerCase().equals("y")||str.toLowerCase().equals("s")); 	
	
        return result;
	}
	
	private String readFileName() throws Exception 
	{
		Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
		
		return str;
	}
	
	private char readDelimiter() throws Exception 
	{
		Scanner sc = new Scanner(System.in);
		char ch = sc.next().toCharArray()[0];
		
		return ch;
	}
	
}

