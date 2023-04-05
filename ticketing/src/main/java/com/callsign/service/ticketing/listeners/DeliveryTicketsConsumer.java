package com.callsign.service.ticketing.listeners;

import com.callsign.service.ticketing.models.request.TicketRequest;
import com.callsign.service.ticketing.services.implementation.DeliveryComplaintTicketHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Junaid Shakeel
 * @project Exercise
 */
@Slf4j
@Component
public class DeliveryTicketsConsumer {

    private static  List<TicketRequest> ticketsList=new ArrayList<>();

    @Autowired
    DeliveryComplaintTicketHandlerService deliveryComplaintTicketHandlerService;


    @KafkaListener(topics = "${kafka.topic}",groupId = "${kafka.consumer.group-id}",containerFactory = "kafkaListenerContainerFactory")
    public void getDeliveryTicketsDetail(TicketRequest req){
        //add consume tickets in in-memory ticketsList
        this.ticketsList.add(req);

        //save the final ticket list into database
        deliveryComplaintTicketHandlerService.saveComplaintTicketList(
                //pick the data from in-memory list and make a list
                deliveryComplaintTicketHandlerService.createDeliveryComplaintTicketList());
        log.debug("consumed tickets is saved in db");

   }

   public static int getRecordSize(){
        return ticketsList.size();
   }
    /*
    return in-memory saved tickets list
    * */
   public static List<TicketRequest> getTicketRecords(){
        return ticketsList;
   }
}
