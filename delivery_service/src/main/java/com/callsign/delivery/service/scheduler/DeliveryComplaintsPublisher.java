package com.callsign.delivery.service.scheduler;

import com.callsign.delivery.service.model.DeliveryDetails;
import com.callsign.delivery.service.repo.DeliveryDetailsRepo;
import com.callsign.delivery.service.services.implementation.DeliveryCompaintsTicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Junaid Shakeel
 * @project Exercise
 */
@Slf4j
@Component
public class DeliveryComplaintsPublisher {

    @Autowired
    DeliveryCompaintsTicketService deliveryCompaintsTicketService;

    @Autowired
    KafkaTemplate kafkaTemplate;

    @Value("${kafka.topic}")
    public String deliveryComplaintsTopic;

    @Autowired
    DeliveryDetailsRepo deliveryDetailsRepo;

    /*
    this scheduler will publish delivery records for complain on kafka topic and
    update the field IsTicketPublished
    * */
    @Scheduled(fixedDelayString = "${scheduler.config.deliveryComplaintsDelay}",
            initialDelayString = "${scheduler.config.deliveryComplaintsInitialDelay}")
    void publishDeliveryComplaints(){
       List<DeliveryDetails> list= deliveryCompaintsTicketService.getDeliveryListForComplaint();
       try{
       if(!list.isEmpty()){
          list.forEach(deliver -> {
              kafkaTemplate.send(deliveryComplaintsTopic,deliver);
              deliver.setIsTicketPublished(true);
              deliveryDetailsRepo.save(deliver);
          }
          );
           log.info("publish "+list.size()+" records on  topic "+ deliveryComplaintsTopic);
       }
       else
        log.debug("delivery complaints list is empty ..... Great!");
       }catch (Exception e){
           log.error("Exception occurred while publishing the delivery records on topic "+ deliveryComplaintsTopic);
           log.error("exception message ->  "+e.getMessage());
       }
    }


}
