package com.distributedsystems.server.services;

import com.distributedsystems.server.model.Place;
import com.distributedsystems.server.model.Places;
import com.distributedsystems.server.model.RequestLogs;
import com.distributedsystems.server.producer.LogsProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogsServiceImpl implements LogsService {

    private LogsProducer logsProducer;

    @Autowired
    public LogsServiceImpl(LogsProducer logsProducer) {
        this.logsProducer = logsProducer;
    }

    @Override
    public void produceMessage(Places places) {
        RequestLogs requestLogs = new RequestLogs();
        requestLogs.setTime(LocalDateTime.now());
        requestLogs.setMessage("testMessage");

        this.logsProducer.sendLogMessage(requestLogs);
    }

    @Override
    public void produceMessage(Place place) {
        RequestLogs requestLogs = new RequestLogs();
        requestLogs.setTime(LocalDateTime.now());
        requestLogs.setMessage("testMessageForCoordinates");

        this.logsProducer.sendLogMessage(requestLogs);
    }
}
