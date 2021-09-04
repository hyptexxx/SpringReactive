package com.example.reactiveTrain.controller;

import com.example.reactiveTrain.service.RxSseEmitter;
import com.example.reactiveTrain.service.TemperatureSensor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TemperatureController {
    private final TemperatureSensor temperatureSensor;

    @RequestMapping(value = "/temperature-stream", method = RequestMethod.GET)
    public SseEmitter events(HttpServletRequest request) {
        RxSseEmitter emitter = new RxSseEmitter();

        temperatureSensor.temperatureStream()
                .subscribe(emitter.getSubscriber());

        return emitter;
    }
}
