package com.distributedsystems.server.services;

import com.distributedsystems.server.model.Place;
import com.distributedsystems.server.model.Places;

public interface LogsService {
    void produceMessage(Places places);
    void produceMessage(Place place);
}
