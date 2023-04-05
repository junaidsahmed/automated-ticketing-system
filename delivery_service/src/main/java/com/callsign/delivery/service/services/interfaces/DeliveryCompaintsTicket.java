package com.callsign.delivery.service.services.interfaces;

import com.callsign.delivery.service.model.DeliveryDetails;

import java.util.List;

/**
 * @author Junaid Shakeel
 * @project Exercise
 */
public interface DeliveryCompaintsTicket {

    List<DeliveryDetails> getDeliveryListForComplaint();
}
