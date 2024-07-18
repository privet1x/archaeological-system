package mas.apazniak.mas_final_s22326.controller;

import mas.apazniak.mas_final_s22326.model.Location;
import mas.apazniak.mas_final_s22326.repository.LocationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {
    private final LocationRepository locationRepository;

    public LocationController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @GetMapping
    public List<Location> list() {
        List<Location> locations = (List<Location>) locationRepository.findAll();

        return locations;
    }
}
