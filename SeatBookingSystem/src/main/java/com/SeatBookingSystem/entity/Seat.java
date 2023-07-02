package com.SeatBookingSystem.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "seats")
public class Seat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "floor_id")
	private Floor floor;

	@Column(name = "seat_row_num")
	private int seatRowNum;

	@Column(name = "seat_column_num")
	private int seatColumnNum;

	@Column(name = "booking_status")
	private boolean bookingStatus;

	@Column(name = "booking_date")
	private LocalDate bookingStartDate;
	
	@Column(name="booking_end_date")
	private LocalDate bookingEndDate;

	@OneToOne
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	private User userId;

}
