package com.fabianpoels.opdracht01;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ScheduledWeatherGetter {
	
	private static String apiUrlPart1 = "http://api.openweathermap.org/data/2.5/weather?q=";
	private static String apiUrlPart2 = "&units=metric&appid=434736d2c8c9aea323f3cc8ea0d1c037";

	@Scheduled(cron="0 * * * * *" )
	public void getCurrentWeatherLeuven() {
		getWeatherAndAddToMemoryDb("Leuven,be");
	}
	
	@Scheduled(cron="0 * * * * *" )
	public void getCurrentWeatherAntwerpen() {
    	getWeatherAndAddToMemoryDb("Antwerp,be");
	}
	
	@Scheduled(cron="0 * * * * *" )
	public void getCurrentWeatherGent() {
    	getWeatherAndAddToMemoryDb("Ghent,be");
	}
	
	@Scheduled(cron="0 * * * * *" )
	public void getCurrentWeatherBrussel() {
    	getWeatherAndAddToMemoryDb("Brussels,be");
	}
	
	private void getWeatherAndAddToMemoryDb(String city) {
    	RestTemplate restTemplate = new RestTemplate();
        WeatherReport q = restTemplate.getForObject(apiUrlPart1 + city + apiUrlPart2, WeatherReport.class);
        JetstreamDB.instance().root().addWeatherReport(q);
	}
}
