package com.callsign.service.ticketing.models.request;



import com.callsign.service.ticketing.enums.CustomerType;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Junaid Shakeel
 * @project Exercise
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TicketRequest {

    private Integer deliveryId;

    private CustomerType customerType;


    private String deliveryStatus;


    private LocalDateTime expectedDeliveryTime;


    private Integer currentDistanceFromDestinationInMeters;


    private LocalDateTime timeToReachDestination;


    private LocalTime restaurantMeanTimeToPrepareFood;


    private Boolean isTicketPublished;

    private LocalDateTime createDateTime;


}
