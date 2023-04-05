package com.callsign.service.ticketing.services.implementation;

import com.callsign.service.ticketing.enums.CustomerType;
import com.callsign.service.ticketing.enums.TicketMessage;
import com.callsign.service.ticketing.enums.TicketPriority;
import com.callsign.service.ticketing.listeners.DeliveryTicketsConsumer;
import com.callsign.service.ticketing.models.entity.DeliveryTicketDetails;
import com.callsign.service.ticketing.repos.DeliveryTicketDetailsRepo;
import com.callsign.service.ticketing.services.interfaces.TicketHandlerService;
import com.callsign.service.ticketing.utils.DateTimeHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Junaid Shakeel
 * @project Exercise
 */
@Slf4j
@Service
public class DeliveryComplaintTicketHandlerService implements TicketHandlerService {


    private DeliveryTicketDetailsRepo deliveryTicketDetailsRepo;

    @Autowired
    public DeliveryComplaintTicketHandlerService(DeliveryTicketDetailsRepo deliveryTicketDetailsRepo) {
        this.deliveryTicketDetailsRepo = deliveryTicketDetailsRepo;
    }

    public List<DeliveryTicketDetails> createDeliveryComplaintTicketList(){
        List<DeliveryTicketDetails> ticketList= new ArrayList<>();
        if(!DeliveryTicketsConsumer.getTicketRecords().isEmpty()){
            DeliveryTicketsConsumer.getTicketRecords().forEach(
                    x-> {
                        // if delivery time has passed for every type of customer
                        if(LocalDateTime.now().isAfter(x.getExpectedDeliveryTime())) {
                            DeliveryTicketDetails ticket = new DeliveryTicketDetails();
                            ticketList.add(
                                    ticket.setDeliveryTicketDetails(x,TicketPriority.SEVERE,
                                            TicketMessage.DELIVERY_FAILED.getMessage(),""));
                        }
                        //estimated time is more than the expected time for VIP and Loyal Customer
                        else if(DateTimeHandler.addLocalTimeIntoLocalDateTime(x.getTimeToReachDestination(),x.getRestaurantMeanTimeToPrepareFood())
                                .isAfter(x.getExpectedDeliveryTime())
                                && (x.getCustomerType().equals(CustomerType.VIP) || x.getCustomerType().equals(CustomerType.LOYAL))){
                            DeliveryTicketDetails ticket = new DeliveryTicketDetails();
                           ticketList.add(
                                   ticket.setDeliveryTicketDetails(x,TicketPriority.HIGH,
                                           TicketMessage.DELIVERY_TIME_ESTIMATION_ISSUE.getMessage(),""));
                        }
                        //estimated time is more than the expected time for NEW customer
                        else if(DateTimeHandler.addLocalTimeIntoLocalDateTime(x.getTimeToReachDestination(),x.getRestaurantMeanTimeToPrepareFood())
                                .isAfter(x.getExpectedDeliveryTime())
                                && x.getCustomerType().equals(CustomerType.NEW)){
                            DeliveryTicketDetails ticket = new DeliveryTicketDetails();
                            ticketList.add(
                                    ticket.setDeliveryTicketDetails(x,TicketPriority.LOW,
                                            TicketMessage.DELIVERY_TIME_ESTIMATION_ISSUE.getMessage(),""));
                        }
                    }
            );
        }
        return ticketList;
    }

  public void removeCreatedTicketsFromMemory(List<DeliveryTicketDetails> list){
       list.forEach(
               x-> DeliveryTicketsConsumer.getTicketRecords()
                       .removeIf(y-> y.getDeliveryId().equals(x.getDeliverId()))
       );
    }

    public void saveComplaintTicketList(List<DeliveryTicketDetails> lst)  {
        deliveryTicketDetailsRepo.saveAll(lst);
        //remove saved tickets from in-memory
        this.removeCreatedTicketsFromMemory(lst);
    }

    public void saveComplaintTicket(DeliveryTicketDetails deliveryTicketDetails){

        deliveryTicketDetailsRepo.save(deliveryTicketDetails);
    }

    @Override
    public List<DeliveryTicketDetails> getUnresolvedTickets(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("ticketPriority").ascending());
        Page<DeliveryTicketDetails> ticketList=deliveryTicketDetailsRepo.findByIsTicketResolved(false,paging);
        log.debug("Ticket List Size "+ ticketList.getContent().size());
        return ticketList.stream().sorted(Comparator.comparing(DeliveryTicketDetails::getCustomerType).thenComparing(
                DeliveryTicketDetails::getTicketPriority)).collect(Collectors.toList());
    }

}
