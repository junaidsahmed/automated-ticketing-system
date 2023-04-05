package com.callsign.service.ticketing.repos;

import com.callsign.service.ticketing.models.entity.DeliveryTicketDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Junaid Shakeel
 * @project Exercise
 */
@Repository
public interface DeliveryTicketDetailsRepo extends PagingAndSortingRepository<DeliveryTicketDetails,Integer> {
    Page<DeliveryTicketDetails> findByIsTicketResolved(boolean isResolved, Pageable pageable);
}
