package com.callsign.service.ticketing.controllers;

import com.callsign.service.ticketing.models.entity.DeliveryTicketDetails;
import com.callsign.service.ticketing.models.response.BasicResponse;
import com.callsign.service.ticketing.services.interfaces.TicketHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author Junaid Shakeel
 * @project Exercise
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

   private  TicketHandlerService deliveryComplaintTicketHandlerService;

    @Autowired
    public TicketController(TicketHandlerService deliveryComplaintTicketHandlerService) {
        this.deliveryComplaintTicketHandlerService = deliveryComplaintTicketHandlerService;
    }
    @GetMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<BasicResponse<List<DeliveryTicketDetails>>> getAllSortedTickets(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam
            (defaultValue = "10") Integer pageSize) {
        log.debug("request for un-resolved tickets");
        return ResponseEntity.ok(
                new BasicResponse<>(true, HttpStatus.OK, deliveryComplaintTicketHandlerService.getUnresolvedTickets(pageNo, pageSize)));
    }
}
