package com.callsign.service.ticketing.services.interfaces;

import com.callsign.service.ticketing.models.entity.DeliveryTicketDetails;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Junaid Shakeel
 * @project Exercise
 */
public interface TicketHandlerService {

    List<DeliveryTicketDetails> getUnresolvedTickets(Integer pageNo,Integer pageSize);
}
