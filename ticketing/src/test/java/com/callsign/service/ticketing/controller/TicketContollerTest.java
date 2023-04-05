package com.callsign.service.ticketing.controller;

import com.callsign.service.ticketing.controllers.TicketController;
import com.callsign.service.ticketing.enums.CustomerType;
import com.callsign.service.ticketing.enums.TicketMessage;
import com.callsign.service.ticketing.enums.TicketPriority;
import com.callsign.service.ticketing.models.entity.DeliveryTicketDetails;
import com.callsign.service.ticketing.services.interfaces.TicketHandlerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * @author Junaid Shakeel
 * @project Exercise
 */
@SpringBootTest
@AutoConfigureMockMvc
public class TicketContollerTest {

    private MockMvc mockMvc;
    @Mock
    TicketHandlerService ticketHandlerService;

    @InjectMocks
    TicketController ticketController;

    @BeforeEach
    public void setUp(){
        LocalDateTime datetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(21, 30));
        DeliveryTicketDetails deliveryTicketDetails_1 = new DeliveryTicketDetails(
                100, CustomerType.VIP,"0", datetime,datetime.plusMinutes(30),datetime.plusMinutes(40),
                TicketPriority.SEVERE, TicketMessage.DELIVERY_TIME_ESTIMATION_ISSUE.getMessage(),""
        );
        when(ticketHandlerService.getUnresolvedTickets(0,10))
                .thenReturn(Stream.of(deliveryTicketDetails_1).collect(Collectors.toList()));

        this.mockMvc= MockMvcBuilders.standaloneSetup(ticketController).build();
    }
    @Test
    public void getAllSortedTicketsTestSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/callsign/api/v1/tickets/all")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data",hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[:1].isTicketResolved",contains(false)));

    }

}
