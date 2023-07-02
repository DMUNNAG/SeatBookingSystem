package com.SeatBookingSystem.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Building {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private int floorNumber;
	private int seatNumber;
	private int teamId;
	private String bookingStatus;
	private LocalDate bookingDate;
	
	@OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
    private List<Floor> floors = new ArrayList<>();

}
