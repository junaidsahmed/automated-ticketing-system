package com.callsign.service.ticketing.models.entity;

import com.callsign.service.ticketing.enums.CustomerType;
import com.callsign.service.ticketing.enums.TicketPriority;
import com.callsign.service.ticketing.models.request.TicketRequest;
import com.callsign.service.ticketing.utils.DateTimeHandler;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Junaid Shakeel
 * @project Exercise
 */
@Entity
@Table(name = "delivery_ticket_details")
@Getter
@Setter
@ToString
public class DeliveryTicketDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private Integer ticketId;

    @NotNull
    @Column(name = "delivery_id",unique = true)
    private Integer deliverId;

    @NotNull
    @Column(name="customer_type",length = 10)
    private CustomerType customerType;

    @NotNull
    @Column(name = "delivery_status",length = 10)
    private String deliveryStatus;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    @Column(name="delivery_request_time")
    private LocalDateTime deliveryRequestTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    @Column(name="expected_delivery_time")
    private LocalDateTime expectedDeliveryCompletionTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull
    @Column(name = "estimated_delivery_time")
    private LocalDateTime estimatedDeliveryCompletionTime;

    @NotNull
    @Column(name="ticketPriority",length = 10)
    private TicketPriority ticketPriority;

    @NotNull
    @Column(name = "ticket_reason")
    private String ticketReason;

    @Column(name="ticket_details")
    private String ticketDetails;

    @NotNull
    @Column(name="is_ticket_resolved")
    private Boolean isTicketResolved=false;

    public DeliveryTicketDetails setDeliveryTicketDetails(TicketRequest request, TicketPriority ticketPriority, String ticketReason, String ticketDetails){
        DeliveryTicketDetails ticket= new DeliveryTicketDetails();
        ticket.setDeliverId(request.getDeliveryId());
        ticket.setCustomerType(request.getCustomerType());
        ticket.setExpectedDeliveryCompletionTime(request.getExpectedDeliveryTime());
        ticket.setEstimatedDeliveryCompletionTime(DateTimeHandler.addLocalTimeIntoLocalDateTime
                        (request.getTimeToReachDestination(),request.getRestaurantMeanTimeToPrepareFood()));
        ticket.setDeliveryRequestTime(request.getCreateDateTime());
        ticket.setDeliveryStatus(request.getDeliveryStatus());
        ticket.setTicketPriority(ticketPriority);
        ticket.setTicketReason(ticketReason);
        ticket.setTicketDetails(ticketDetails);
        return ticket;

    }

    public DeliveryTicketDetails(Integer deliverId, CustomerType customerType, String deliveryStatus, LocalDateTime deliveryRequestTime, LocalDateTime expectedDeliveryCompletionTime, LocalDateTime estimatedDeliveryCompletionTime, TicketPriority ticketPriority, String ticketReason, String ticketDetails) {
        this.deliverId = deliverId;
        this.customerType = customerType;
        this.deliveryStatus = deliveryStatus;
        this.deliveryRequestTime = deliveryRequestTime;
        this.expectedDeliveryCompletionTime = expectedDeliveryCompletionTime;
        this.estimatedDeliveryCompletionTime = estimatedDeliveryCompletionTime;
        this.ticketPriority = ticketPriority;
        this.ticketReason = ticketReason;
        this.ticketDetails = ticketDetails;
    }
    public DeliveryTicketDetails(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryTicketDetails that = (DeliveryTicketDetails) o;
        return Objects.equals(ticketId, that.ticketId) && Objects.equals(deliverId, that.deliverId) && customerType == that.customerType && Objects.equals(deliveryStatus, that.deliveryStatus) && Objects.equals(deliveryRequestTime, that.deliveryRequestTime) && Objects.equals(expectedDeliveryCompletionTime, that.expectedDeliveryCompletionTime) && Objects.equals(estimatedDeliveryCompletionTime, that.estimatedDeliveryCompletionTime) && ticketPriority == that.ticketPriority && Objects.equals(ticketReason, that.ticketReason) && Objects.equals(ticketDetails, that.ticketDetails) && Objects.equals(isTicketResolved, that.isTicketResolved);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId, deliverId, customerType, deliveryStatus, deliveryRequestTime, expectedDeliveryCompletionTime, estimatedDeliveryCompletionTime, ticketPriority, ticketReason, ticketDetails, isTicketResolved);
    }
}
