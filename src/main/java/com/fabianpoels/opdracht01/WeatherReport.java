package com.fabianpoels.opdracht01;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherReport {
	
	private long id;
	@JsonProperty("name")
	private String city;
	@JsonProperty("dt")
	private Long unixTime;
	private double temp;
	private int humidity;
	
	public WeatherReport() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getTime() {
		return unixTime;
	}
	
	public String getLocalTimeAsString() {
		Date date = new Date(getTime()*1000L);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		return sdf.format(date);
	}

	public void setTime(Long time) {
		this.unixTime = time;
	}
	
	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}
	
	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	@JsonProperty("main")
	private void unpackNested(Map<String,String> mainProperties) {
		this.temp = Double.parseDouble(mainProperties.get("temp"));
		this.humidity = Integer.parseInt(mainProperties.get("humidity"));
	}
	
	@Override
	public boolean equals(Object wr) {
		if ((wr instanceof WeatherReport) && this.city.equalsIgnoreCase(((WeatherReport) wr).getCity()) && this.unixTime.compareTo(((WeatherReport) wr).getTime()) == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "The temperature in " + city + " on " + getLocalTimeAsString() + " : " + getTemp() + "°C (humidity: " + getHumidity() + "%)";
	}
	
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
}
