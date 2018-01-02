package com.fabianpoels.opdracht01;

import java.util.List;

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
public class ApiController {

	
	@GetMapping
	public List<WeatherReport> index(@RequestParam(value="city", defaultValue="") String city) {
		return JetstreamDB.instance().root().getWeatherReportsByCity(city);
	}
	
	@GetMapping("{id}")
	public WeatherReport show(@PathVariable long id) {
		return JetstreamDB.instance().root().getWeatherReportById(id);
	}
	
	@PostMapping
    @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
    public String create(@RequestBody WeatherReport wr) {
		JetstreamDB.instance().root().addWeatherReport(wr);
        return "redirect:/weatherreports";
    }
}
