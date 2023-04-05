package com.callsign.delivery.service.services.implementation;

import com.callsign.delivery.service.model.DeliveryDetails;
import com.callsign.delivery.service.repo.DeliveryDetailsRepo;
import com.callsign.delivery.service.services.interfaces.DeliveryCompaintsTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Junaid Shakeel
 * @project Exercise
 */
@Service
public class DeliveryCompaintsTicketService implements DeliveryCompaintsTicket {
    @Autowired
    DeliveryDetailsRepo deliveryDetailsRepo;

    @Override
    public List<DeliveryDetails> getDeliveryListForComplaint() {
       List<DeliveryDetails> lst = deliveryDetailsRepo.findComplaintDeliveries();
        return lst;
    }

}
