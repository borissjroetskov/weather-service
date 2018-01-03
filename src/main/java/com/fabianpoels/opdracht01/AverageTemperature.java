package com.fabianpoels.opdracht01;

import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.LongStream;

public class AverageTemperature {

	private Long startUnixtime;
	private Long endUnixtime;
	private Long measurements;
	private String city;
	private double temp;
	
	public AverageTemperature() {
		
	}
	
	public AverageTemperature(List<WeatherReport> weatherReportsByCity) {
		weatherReportsByCity.stream().map(mapper)
		LongStream ls = weatherReportsByCity.stream().mapToLong(WeatherReport::getTime);
		setStartUnixtime(ls.min().getAsLong());
		setEndUnixtime(ls.max().getAsLong());
		DoubleStream ds = weatherReportsByCity.stream().mapToDouble(WeatherReport::getTemp);
		setMeasurements(ds.count());
		setTemp(ds.sum() / getMeasurements());
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}
}
