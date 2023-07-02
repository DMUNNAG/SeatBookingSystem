package com.SeatBookingSystem.controller;

import com.SeatBookingSystem.entity.Seat;
import com.SeatBookingSystem.entity.User;
import com.SeatBookingSystem.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.SeatBookingSystem.service.SeatAllocationService;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/seat-allocation")
public class SeatAllocationController {
	private final SeatAllocationService seatAllocationService;
	private UserRepository userRepository;

	public SeatAllocationController(SeatAllocationService seatAllocationService) {
		this.seatAllocationService = seatAllocationService;
	}

	@PostMapping("/initialize")
	public String initializeSeatAllocation() {
		seatAllocationService.initializeSeatAllocation();
		log.info("Seats Initialised");
		return "Seat allocation initialized successfully";
	}

	@PostMapping("/book")
	public String bookSeat(@RequestParam long userId, @RequestParam int floorNum, @RequestParam int seatRowNum,
						   @RequestParam int seatColumnNum) {
		log.info("Seat Booked");
		return seatAllocationService.bookSeat(userId, floorNum, seatRowNum, seatColumnNum);
	}

	/*@GetMapping("/dashboard/{userId}")
	@ResponseStatus(HttpStatus.OK)
	public Seat showDetails(@RequestParam long userId){

		return seatAllocationService.showDetails(userId);

	}*/

	@GetMapping("/status")
	public String getSeatStatus(@RequestParam long userId, @RequestParam int floorNum, @RequestParam int seatRowNum,
			@RequestParam int seatColumnNum) {
		return seatAllocationService.getSeatStatus(userId, floorNum, seatRowNum, seatColumnNum);
	}
}
