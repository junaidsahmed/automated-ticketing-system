package com.callsign.delivery.service.model;

/**
 * @author Junaid Shakeel
 * @project Exercise
 * @created 06/01/2022
 */

import com.callsign.delivery.service.enums.CustomerType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Setter
@Getter
@Entity
@Table(name = "delivery_details")
public class DeliveryDetails {
    @Id
    @Column(name = "delivery_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deliveryId;

    @NotNull(message = "customer type must not be null")
    @Column(name="customer_type")
    private CustomerType customerType;

    @NotNull(message = "delivery status must not be null")
    @Column(name="delivery_status")
    private String deliveryStatus;

    @NotNull(message = "expected delivery time must not be null")
    @Column(name="expected_delivery_time")
    private LocalDateTime expectedDeliveryTime;

    @NotNull(message = "current distance must not be null")
    @Column(name = "current_distance_from_destination_in_meters")
    private Integer currentDistanceFromDestinationInMeters;

    @NotNull(message = "time to reach destination must not be null")
    @Column(name="time_to_reach_destination")
    private LocalDateTime timeToReachDestination;

    @NotNull(message = "")
    @Column(name = "restaurant_mean_time_to_prepare_food")
    private LocalTime restaurantMeanTimeToPrepareFood;

    @NotNull
    @Column(name="is_ticket_Published")
    private Boolean isTicketPublished=false;

    @Column(name= "created_date_time",updatable = false,nullable = false)
    @CreationTimestamp
    private LocalDateTime createDateTime;


    public DeliveryDetails(CustomerType customerType, String deliveryStatus, LocalDateTime expectedDeliveryTime, Integer currentDistanceFromDestinationInMeters, LocalDateTime timeToReachDestination, LocalTime restaurantMeanTimeToPrepareFood) {
        this.customerType = customerType;
        this.deliveryStatus = deliveryStatus;
        this.expectedDeliveryTime = expectedDeliveryTime;
        this.currentDistanceFromDestinationInMeters = currentDistanceFromDestinationInMeters;
        this.timeToReachDestination = timeToReachDestination;
        this.restaurantMeanTimeToPrepareFood = restaurantMeanTimeToPrepareFood;
    }

    public  DeliveryDetails(){

    }
}
