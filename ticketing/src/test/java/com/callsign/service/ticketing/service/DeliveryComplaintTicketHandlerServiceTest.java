package com.callsign.service.ticketing.service;

import com.callsign.service.ticketing.enums.CustomerType;
import com.callsign.service.ticketing.enums.TicketMessage;
import com.callsign.service.ticketing.enums.TicketPriority;
import com.callsign.service.ticketing.models.entity.DeliveryTicketDetails;
import com.callsign.service.ticketing.repos.DeliveryTicketDetailsRepo;

import com.callsign.service.ticketing.services.implementation.DeliveryComplaintTicketHandlerService;
import com.callsign.service.ticketing.services.interfaces.TicketHandlerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

/**
 * @author Junaid Shakeel
 * @project Exercise
 */

@SpringBootTest
public class DeliveryComplaintTicketHandlerServiceTest {

    @Mock
    private DeliveryTicketDetailsRepo deliveryTicketDetailsRepoMock;

    @InjectMocks
    private TicketHandlerService ticketHandlerService = new DeliveryComplaintTicketHandlerService(deliveryTicketDetailsRepoMock);

    private Page<DeliveryTicketDetails> plist;

   @BeforeEach
    public void setUpMockValues(){
        Pageable paging = PageRequest.of(0, 10, Sort.by("ticketPriority").ascending());
        LocalDateTime datetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(21, 30));
        DeliveryTicketDetails deliveryTicketDetails_1 = new DeliveryTicketDetails(
                100, CustomerType.VIP,"0", datetime,datetime.plusMinutes(30),datetime.plusMinutes(40),
                TicketPriority.SEVERE, TicketMessage.DELIVERY_TIME_ESTIMATION_ISSUE.getMessage(),""
        );
        this.plist=new PageImpl<>(Stream.of(deliveryTicketDetails_1).collect(Collectors.toList()));

        when(deliveryTicketDetailsRepoMock.findByIsTicketResolved(false,paging)).
                thenReturn(plist);

    }
    @Test
    public void getUnresolvedTicketsTest() {
        Assertions.assertEquals(plist.getContent(),ticketHandlerService.getUnresolvedTickets(0,10));
    }
}