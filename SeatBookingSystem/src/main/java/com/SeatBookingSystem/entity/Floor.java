package com.SeatBookingSystem.entity;

import lombok.Data;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "floors")
public class Floor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "floor_num")
	private int floorNum;

	@OneToMany(mappedBy = "floor", cascade = CascadeType.ALL)
	private List<Seat> seats = new ArrayList<>();

	@ManyToOne(cascade = CascadeType.ALL)
	private Building building;

}
