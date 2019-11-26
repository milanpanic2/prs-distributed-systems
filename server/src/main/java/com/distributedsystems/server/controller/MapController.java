package com.distributedsystems.server.controller;

import com.distributedsystems.server.model.CoordinatesObj;
import com.distributedsystems.server.model.Feature;
import com.distributedsystems.server.model.Place;
import com.distributedsystems.server.model.Places;
import com.distributedsystems.server.services.LogsService;
import com.distributedsystems.server.services.MapService;
import com.distributedsystems.server.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.BASE_URL)
public class MapController {

    private LogsService logsService;
    private MapService mapService;

    @Autowired
    public MapController(LogsService logsService, MapService mapService) {
        this.logsService = logsService;
        this.mapService = mapService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/distance")
    public ResponseEntity<Double> getDistance(@RequestBody Places places) {

        this.logsService.produceMessage(places);
        return new ResponseEntity<>(this.mapService.getPointsDistance(places), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/coordinates")
    public ResponseEntity<String[]> getCoordinates(@RequestBody Place place) {

        this.logsService.produceMessage(place);
        return new ResponseEntity<>(this.mapService.getPointsCoordinates(place), HttpStatus.OK);
    }
}
