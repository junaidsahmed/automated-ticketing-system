package com.callsign.service.ticketing.models;

import com.callsign.service.ticketing.enums.CustomerType;
import com.callsign.service.ticketing.enums.TicketMessage;
import com.callsign.service.ticketing.enums.TicketPriority;
import com.callsign.service.ticketing.models.entity.DeliveryTicketDetails;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Junaid Shakeel
 * @project Exercise
 */
@SpringBootTest
public class DeliveryTicketDetailsTest {

    @Test
    public void testConstructorAndGetters() {
        LocalDateTime datetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(21, 30));

        DeliveryTicketDetails deliveryTicketDetails = new DeliveryTicketDetails(
            100, CustomerType.VIP,"0", datetime,datetime.plusMinutes(30),datetime.plusMinutes(40),
                TicketPriority.SEVERE, TicketMessage.DELIVERY_TIME_ESTIMATION_ISSUE.getMessage(),""
        );
        Assertions.assertNull(deliveryTicketDetails.getTicketId());
       Assertions.assertEquals(deliveryTicketDetails.getDeliverId(),100);
       Assertions.assertEquals(deliveryTicketDetails.getCustomerType(),CustomerType.VIP);
       Assertions.assertEquals(deliveryTicketDetails.getDeliveryStatus(),"0");
       Assertions.assertEquals(deliveryTicketDetails.getDeliveryRequestTime(),datetime);
       Assertions.assertEquals(deliveryTicketDetails.getExpectedDeliveryCompletionTime(),datetime.plusMinutes(30));
       Assertions.assertEquals(deliveryTicketDetails.getEstimatedDeliveryCompletionTime(),datetime.plusMinutes(40));
       Assertions.assertEquals(deliveryTicketDetails.getTicketPriority(),TicketPriority.SEVERE);
       Assertions.assertEquals(deliveryTicketDetails.getTicketReason(),TicketMessage.DELIVERY_TIME_ESTIMATION_ISSUE.getMessage());
       Assertions.assertEquals(deliveryTicketDetails.getTicketDetails(),"");
       Assertions.assertEquals(deliveryTicketDetails.getIsTicketResolved(),false);
    }

    @Test
    public void equalsHashCodeVerify() {
        LocalDateTime datetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(21, 30));

        DeliveryTicketDetails deliveryTicketDetails_1 = new DeliveryTicketDetails(
                100, CustomerType.VIP,"0", datetime,datetime.plusMinutes(30),datetime.plusMinutes(40),
                TicketPriority.SEVERE, TicketMessage.DELIVERY_TIME_ESTIMATION_ISSUE.getMessage(),""
        );

        DeliveryTicketDetails deliveryTicketDetails_2 = new DeliveryTicketDetails(
                100, CustomerType.VIP,"0", datetime,datetime.plusMinutes(30),datetime.plusMinutes(40),
                TicketPriority.SEVERE, TicketMessage.DELIVERY_TIME_ESTIMATION_ISSUE.getMessage(),""
        );
        Assertions.assertEquals(deliveryTicketDetails_1,deliveryTicketDetails_2);
        Assertions.assertEquals(deliveryTicketDetails_1.hashCode(),deliveryTicketDetails_2.hashCode());
    }
}