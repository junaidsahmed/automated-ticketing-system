package com.callsign.delivery.service.services.implementation;

import com.callsign.delivery.service.enums.CustomerType;
import com.callsign.delivery.service.model.DeliveryDetails;
import com.callsign.delivery.service.repo.DeliveryDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Junaid Shakeel
 * @project Exercise
 */
@Service
public class DelieveryDetailsService {

    @Autowired
    DeliveryDetailsRepo deliveryDetailsRepo;

     public void addSingleDelivery(CustomerType customerType,int eh,int em,int rh,int rm,int mt){
           LocalDate date = LocalDate.now();
           LocalTime expectedTime = LocalTime.of(eh,em);
           LocalTime reachedTime =  LocalTime.of(rh,rm);
            DeliveryDetails deliveryDetails=new DeliveryDetails(
                    customerType,
                    "0",
                    LocalDateTime.of(date,expectedTime),
                    200,
                    LocalDateTime.of(date,reachedTime),
                    LocalTime.of(0,mt)
            );
            deliveryDetailsRepo.save(deliveryDetails);
        }

}
