package com.fabianpoels.opdracht01;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weatherreports")
public class WeatherReportController {

	
	@GetMapping
	public List<WeatherReport> index(@RequestParam(value="city", defaultValue="") String city) {
		return JetstreamDB.instance().root().getWeatherReportsByCity(city);
	}
	
	@GetMapping("{id}")
	public WeatherReport show(@PathVariable long id) {
		return JetstreamDB.instance().root().getWeatherReportById(id);
	}
	
	@GetMapping("/average")
	public AverageTemperature show(@RequestParam(value="city", defaultValue="") String city) {
		return JetstreamDB.instance().root().getAverageTemperature(city);
	}

	@PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody WeatherReport wr) {
		JetstreamDB.instance().root().addWeatherReport(wr);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
	

	@PostMapping("/addMultiple")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody WeatherReportWrapper list) {
		for (WeatherReport wr : list.getWeatherreports()) {
			JetstreamDB.instance().root().addWeatherReport(wr);
		}
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
}
