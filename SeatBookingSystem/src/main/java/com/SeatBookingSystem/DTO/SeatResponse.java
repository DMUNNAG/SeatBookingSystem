package com.SeatBookingSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatResponse {

    private long userId;
    private long seatId;
    private long floor;
    private String username;
    private boolean bookingStatus;
    private LocalDate bookingStartDate;
    private LocalDate bookingEndDate;

}
