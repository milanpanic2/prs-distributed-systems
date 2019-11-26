package com.distributedsystems.server.services;

import com.distributedsystems.server.model.CoordinatesObj;
import com.distributedsystems.server.model.Feature;
import com.distributedsystems.server.model.Place;
import com.distributedsystems.server.model.Places;

public interface MapService {
    Double getPointsDistance(Places places);
    String[] getPointsCoordinates(Place locality);
}
