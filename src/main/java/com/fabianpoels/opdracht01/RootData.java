package com.fabianpoels.opdracht01;

import java.util.ArrayList;
import java.util.List;

public class RootData {

	private final List<WeatherReport> weatherReports = new ArrayList<>();
	
	public List<WeatherReport> getWeatherReports() {
		return this.weatherReports;
	}
	
	public List<WeatherReport> getWeatherReportsByCity(String city) {
		if (city.equalsIgnoreCase("")) {
			return this.weatherReports;
		} else {
			List<WeatherReport> result = new ArrayList<>();
			for (WeatherReport wr : this.weatherReports) {
				if (wr.getCity().equalsIgnoreCase(city)) {
					result.add(wr);
				}
			}
			return result;
		}
	}

	public synchronized void addWeatherReport(WeatherReport wr) {
		if (weatherReports.size() == 0) {
			weatherReports.add(wr);
		} else {
			boolean hasDuplicate = false;
			for (WeatherReport x : weatherReports) {
				if (x.equals(wr)) {
					hasDuplicate = true;
					break;
				}
			}
			if (!hasDuplicate) {
				weatherReports.add(wr);
			}
		}
	}

	public WeatherReport getWeatherReportById(long id) {
		return weatherReports.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
	}
}
