package com.fabianpoels.opdracht01;

import java.util.List;
import java.util.stream.Collectors;

public class AverageTemperature {

	private Long startUnixtime;
	private Long endUnixtime;
	private Long measurements;
	private List<String> cities;
	private double temp;
	
	public AverageTemperature() {
		
	}
	
	public AverageTemperature(List<WeatherReport> weatherReports) {
		cities = weatherReports.stream().map(x -> x.getCity()).distinct().collect(Collectors.toList());
		setStartUnixtime(weatherReports.stream().mapToLong(WeatherReport::getTime).min().getAsLong());
		setEndUnixtime(weatherReports.stream().mapToLong(WeatherReport::getTime).max().getAsLong());
		setMeasurements(weatherReports.stream().mapToDouble(WeatherReport::getTemp).count());
		setTemp(weatherReports.stream().mapToDouble(WeatherReport::getTemp).sum() / getMeasurements());
	}

	public Long getStartUnixtime() {
		return startUnixtime;
	}
	public void setStartUnixtime(Long startUnixtime) {
		this.startUnixtime = startUnixtime;
	}

	public Long getEndUnixtime() {
		return endUnixtime;
	}

	public void setEndUnixtime(Long endUnixtime) {
		this.endUnixtime = endUnixtime;
	}

	public Long getMeasurements() {
		return measurements;
	}

	public void setMeasurements(Long measurements) {
		this.measurements = measurements;
	}

	public List<String> getCities() {
		return cities;
	}

	public void setCities(List<String> cities) {
		this.cities = cities;
	}

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}
}
