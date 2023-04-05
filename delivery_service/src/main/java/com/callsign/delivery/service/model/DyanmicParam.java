package com.callsign.delivery.service.model;

import com.callsign.delivery.service.enums.CustomerType;
import lombok.Data;

/**
 * @author Junaid Shakeel
 * @project Exercise
 */
@Data
public class DyanmicParam {

    private CustomerType customerType;
    private int deliveryExpectedHour;
    private int deliveryExpectedMins;
    private int timeToReachDestinationHour;
    private int timeToReachDestinationMins;
    private int meanTimeForFood;
}
