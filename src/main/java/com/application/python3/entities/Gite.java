package com.application.python3.entities;
// Generated 25 feb 2022, 17:03:20 by Hibernate Tools 5.6.3.Final

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/* Data layer dell'applicativo: classe Hibernate */

@Entity
@Table(name = "gite")
public class Gite implements java.io.Serializable 
{
	private static final long serialVersionUID = -8391183594826571582L;
	
	@EmbeddedId
	private GiteId id;
	@Column(name = "utente_macchina")
	private String utenteMacchina;
	@Column(name = "salita")
	private double salita;
	@Column(name = "discesa")
	private double discesa;
	@Column(name = "distanza")
	private double distanza;
	@Column(name = "tempo_totale")
	private long tempoTotale;
	@Column(name = "altezza_max")
	private double altezzaMax;
	@Column(name = "weather_description")
	private String weatherDescription;
	@Column(name = "temperature")
	private double temperature;
	@Column(name = "feels_like")
	private double feelsLike;
	@Column(name = "humidity")
	private int humidity;
	@Column(name = "wind_direction")
	private int windDirection;
	@Column(name = "wind_speed")
	private double windSpeed;

	public Gite() {
	}

	public Gite(GiteId id, String utenteMacchina, double salita, double discesa, double distanza, long tempoTotale,
			double altezzaMax, String weatherDescription, double temperature, double feelsLike, int humidity,
			int windDirection, double windSpeed) {
		this.id = id;
		this.utenteMacchina = utenteMacchina;
		this.salita = salita;
		this.discesa = discesa;
		this.distanza = distanza;
		this.tempoTotale = tempoTotale;
		this.altezzaMax = altezzaMax;
		this.weatherDescription = weatherDescription;
		this.temperature = temperature;
		this.feelsLike = feelsLike;
		this.humidity = humidity;
		this.windDirection = windDirection;
		this.windSpeed = windSpeed;
	}

	public GiteId getId() {
		return this.id;
	}

	public void setId(GiteId id) {
		this.id = id;
	}

	public String getUtenteMacchina() {
		return this.utenteMacchina;
	}

	public void setUtenteMacchina(String utenteMacchina) {
		this.utenteMacchina = utenteMacchina;
	}

	public double getSalita() {
		return this.salita;
	}

	public void setSalita(double salita) {
		this.salita = salita;
	}

	public double getDiscesa() {
		return this.discesa;
	}

	public void setDiscesa(double discesa) {
		this.discesa = discesa;
	}

	public double getDistanza() {
		return this.distanza;
	}

	public void setDistanza(double distanza) {
		this.distanza = distanza;
	}

	public long getTempoTotale() {
		return this.tempoTotale;
	}

	public void setTempoTotale(int tempoTotale) {
		this.tempoTotale = tempoTotale;
	}

	public double getAltezzaMax() {
		return this.altezzaMax;
	}

	public void setAltezzaMax(double altezzaMax) {
		this.altezzaMax = altezzaMax;
	}

	public String getWeatherDescription() {
		return this.weatherDescription;
	}

	public void setWeatherDescription(String weatherDescription) {
		this.weatherDescription = weatherDescription;
	}

	public double getTemperature() {
		return this.temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getFeelsLike() {
		return this.feelsLike;
	}

	public void setFeelsLike(double feelsLike) {
		this.feelsLike = feelsLike;
	}

	public int getHumidity() {
		return this.humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public int getWindDirection() {
		return this.windDirection;
	}

	public void setWindDirection(int windDirection) {
		this.windDirection = windDirection;
	}

	public double getWindSpeed() {
		return this.windSpeed;
	}

	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}

	@Override
	public String toString() {
		return "Gite [PK=(" + id.toString() + "), utenteMacchina=" + utenteMacchina + ", salita=" + salita + ", discesa=" + discesa
				+ ", distanza=" + distanza + ", tempoTotale=" + tempoTotale + ", altezzaMax=" + altezzaMax
				+ ", weatherDescription=" + weatherDescription + ", temperature=" + temperature + ", feelsLike="
				+ feelsLike + ", humidity=" + humidity + ", windDirection=" + windDirection + ", windSpeed=" + windSpeed
				+ "]";
	}
}
