package com.fabianpoels.weather;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RootData {

	private final List<WeatherReport> weatherReports = new ArrayList<>();
	
	public List<WeatherReport> getWeatherReports() {
		return this.weatherReports;
	}
	
	public List<WeatherReport> getWeatherReportsByCity(String city) {
		if (city.equalsIgnoreCase("")) {
			return this.weatherReports;
		} else {
			return this.weatherReports.stream().filter(wr -> wr.getCity().equalsIgnoreCase(city)).collect(Collectors.toList());
		}
	}

	public synchronized void addWeatherReport(WeatherReport wr) {
		if (weatherReports.size() == 0) {
			weatherReports.add(wr);
		} else {
			if (!weatherReports.contains(wr)) {
				weatherReports.add(wr);
			}
		}
		JetstreamDB.instance().storeRequired(weatherReports);
	}

	public WeatherReport getWeatherReportById(long id) {
		return weatherReports.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
	}

	public AverageTemperature getAverageTemperature(String city) {
		return new AverageTemperature(getWeatherReportsByCity(city));
	}
}
