package com.distributedsystems.server.services;

import com.distributedsystems.server.model.*;
import com.google.gson.Gson;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.springframework.stereotype.Service;
import javax.ws.rs.core.Response;

@Service
public class MapServiceImpl implements MapService {

    private final String REQUEST_URL =
            "https://api.openrouteservice.org/v2/directions/driving-car?api_key=5b3ce3597851110001cf624869b8b3d3193943c09a1d08526aa082c2";

    @Override
    public Double getPointsDistance(Places places) {
        String[] startCoordinates = getPointsCoordinates(places.getStartPoint());
        String[] endCoordinates = getPointsCoordinates(places.getEndPoint());

        ResteasyClient client = new ResteasyClientBuilder().build();
        String start = String.format("start=%s,%s", startCoordinates[0], startCoordinates[1]);
        String end = String.format("end=%s,%s", endCoordinates[0], endCoordinates[1]);
        String baseUrl = "https://api.openrouteservice.org/v2/directions/driving-car?api_key=5b3ce3597851110001cf624869b8b3d3193943c09a1d08526aa082c2";
        String url = String.format("%s&%s&%s", baseUrl, start, end);
        ResteasyWebTarget target = client.target(url);
        Response response = target.request().get();
        String value = response.readEntity(String.class);
        Gson g = new Gson();
        DistanceObj p = g.fromJson(value, DistanceObj.class);

        return p.getFeatures()[0].getProperties().getSegments()[0].getDistance();
    }

    @Override
    public String[] getPointsCoordinates(Place locality) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        String baseUrl = "https://api.openrouteservice.org/geocode/search/structured?api_key=5b3ce3597851110001cf624869b8b3d3193943c09a1d08526aa082c2&locality=";
        String url = String.format("%s%s", baseUrl, locality.getPlace());
        ResteasyWebTarget target = client.target(url);
        Response response = target.request().get();
        String value = response.readEntity(String.class);
        Gson g = new Gson();
        CoordinatesObj p = g.fromJson(value, CoordinatesObj.class);

        return p.getFeatures()[0].getGeometry().getCoordinates();
    }

    private String[] getPointsCoordinates(String locality) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        String baseUrl = "https://api.openrouteservice.org/geocode/search/structured?api_key=5b3ce3597851110001cf624869b8b3d3193943c09a1d08526aa082c2&locality=";
        String url = String.format("%s%s", baseUrl, locality);
        ResteasyWebTarget target = client.target(url);
        Response response = target.request().get();
        String value = response.readEntity(String.class);
        Gson g = new Gson();
        CoordinatesObj p = g.fromJson(value, CoordinatesObj.class);

        return p.getFeatures()[0].getGeometry().getCoordinates();
    }
}
