package com.application.python3.csv;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import com.application.python3.entities.Gite;

/* Stampa dati delle escursioni su file a campi delimitati (di default CSV) */

public class CsvPrinter {

	private List<Gite> gite;
	private String fileName;
	private char delimiter;
	
	private String[] headers = { "nome_gita", "data_effettuazione", "utente_macchina", "caricamento_dati",
			             "salita", "discesa", "distanza", "tempo_totale", "altezza_max", "weather_description",
			             "temperature", "feels_like", "humidity", "wind_direction", "wind_speed"};
	
	@SuppressWarnings("deprecation")
	public void print() throws Exception
	{
		BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(this.fileName));
			
		try(CSVPrinter csvPrinter = CSVFormat.newFormat(this.delimiter).withRecordSeparator("\r\n").withHeader(this.headers).print(bufferedWriter)) 
		{
			for (Gite gita:gite)
				this.printGita(gita, csvPrinter);
		     
		} 
		catch (IOException ex) 
		{
		     throw new Exception(ex);
		}
	}
	
	
	private void printGita(Gite gita, CSVPrinter printer) throws IOException
	{
		printer.printRecord(gita.getId().getNomeGita(),
				gita.getId().getDataEffettuazione(),
				gita.getUtenteMacchina(),
				gita.getId().getCaricamentoDati(),
				gita.getSalita(),
				gita.getDiscesa(),
				gita.getDistanza(),
				gita.getTempoTotale(),
				gita.getAltezzaMax(),
				gita.getWeatherDescription(),
				gita.getTemperature(),
				gita.getFeelsLike(),
				gita.getHumidity(),
				gita.getWindDirection(),
				gita.getWindSpeed());
	}
	
	@SuppressWarnings("deprecation")
	public CsvPrinter(List<Gite> gite, String fileName) throws IOException 
	{
		super();
		this.gite = gite;
		this.fileName=fileName;
		this.delimiter=',';
	}
	
	@SuppressWarnings("deprecation")
	public CsvPrinter(List<Gite> gite, String fileName,char delimiter) throws IOException 
	{
		super();
		this.gite = gite;
		this.fileName=fileName;
		this.delimiter=delimiter;
	}
	
}
