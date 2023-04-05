package com.callsign.service.ticketing.utils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * @author Junaid Shakeel
 * @project Exercise
 */
public class DateTimeHandler {

    public static int getTotalTimeInMinutes(LocalTime time){
            return  (time.getHour()*60)+time.getMinute();
    }

    /*
    function adds java LocalTime into LocalDateTime and provide precision upto minutes
    * */
    public static LocalDateTime addLocalTimeIntoLocalDateTime(LocalDateTime dateTime,LocalTime time){

        return dateTime.plus(getTotalTimeInMinutes(time), ChronoUnit.MINUTES);

    }
}
