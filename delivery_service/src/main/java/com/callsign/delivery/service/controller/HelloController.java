package com.callsign.delivery.service.controller;

import com.callsign.delivery.service.model.DyanmicParam;
import com.callsign.delivery.service.services.implementation.DelieveryDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Junaid Shakeel
 * @project Exercise
 */
@RestController
@Slf4j
@RequestMapping(value = "delivery/api/v1")
public class HelloController {

    @Autowired
    DelieveryDetailsService delieveryDetailsService;
    /*
    Add for dev purpose
    * */
    @PostMapping(value = "/add",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addOne(@RequestBody  DyanmicParam dyanmicParam){
        log.debug(dyanmicParam.toString());
        delieveryDetailsService.addSingleDelivery( dyanmicParam.getCustomerType(),
                dyanmicParam.getDeliveryExpectedHour(), dyanmicParam.getDeliveryExpectedMins(),dyanmicParam.getTimeToReachDestinationHour(),dyanmicParam.getTimeToReachDestinationMins(),dyanmicParam.getMeanTimeForFood());
        return new ResponseEntity<>("{\"status\":\"success\",\"message\":\"delivery added\"}", HttpStatus.OK);
    }
}