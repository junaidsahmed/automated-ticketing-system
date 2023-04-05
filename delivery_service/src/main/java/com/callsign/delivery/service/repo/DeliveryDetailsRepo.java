package com.callsign.delivery.service.repo;

import com.callsign.delivery.service.model.DeliveryDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Junaid Shakeel
 * @project Exercise
 */
@Repository
public interface DeliveryDetailsRepo extends CrudRepository<DeliveryDetails, Integer> {
    @Query(value = "select * from delivery_details c \n" +
            "where  c.is_ticket_published=false \n" +
            "and c.delivery_status=0 and DATE(c.created_date_time)=current_date()  \n" +
            "and (c.expected_delivery_time < c.restaurant_mean_time_to_prepare_food+c.time_to_reach_destination " +
            "or Now() > expected_delivery_time)\n",
    nativeQuery = true)
    List<DeliveryDetails> findComplaintDeliveries();
}
