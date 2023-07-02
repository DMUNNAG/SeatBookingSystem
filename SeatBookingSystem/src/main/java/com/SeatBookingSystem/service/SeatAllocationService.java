package com.SeatBookingSystem.service;

import com.SeatBookingSystem.entity.User;
import com.SeatBookingSystem.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SeatBookingSystem.entity.Building;
import com.SeatBookingSystem.entity.Floor;
import com.SeatBookingSystem.entity.Seat;
import com.SeatBookingSystem.repository.BuildingRepository;
import com.SeatBookingSystem.repository.SeatRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class SeatAllocationService {
	private final BuildingRepository buildingRepository;
	private final SeatRepository seatRepository;

	@Autowired
	private UserRepository userRepository;

	public SeatAllocationService(BuildingRepository buildingRepository, SeatRepository seatRepository) {
		this.buildingRepository = buildingRepository;
		this.seatRepository = seatRepository;
	}

	public void initializeSeatAllocation() {
		// Create the building with 10 floors
		Building building = new Building();
		buildingRepository.save(building);

		for (int floorNum = 1; floorNum <= 10; floorNum++) {
			Floor floor = new Floor();
			floor.setFloorNum(floorNum);
			floor.setBuilding(building);
			building.getFloors().add(floor);

			// Here we are creating 100 seats for each floor
			for (int seatRowNum = 1; seatRowNum <= 10; seatRowNum++) {
				for (int seatColumnNum = 1; seatColumnNum <= 10; seatColumnNum++) {
					Seat seat = new Seat();
					seat.setFloor(floor);
					seat.setSeatRowNum(seatRowNum);
					seat.setSeatColumnNum(seatColumnNum);
					seat.setBookingStatus(false);
					seatRepository.save(seat);
					floor.getSeats().add(seat);
				}
			}
		}
	}

	//to book the seat in particular floor with seatnumber in (row&column)
	public String bookSeat(long userId, int floorNum, int seatRowNum, int seatColumnNum) {
		Seat seat = seatRepository.findByFloorFloorNumAndSeatRowNumAndSeatColumnNum(floorNum, seatRowNum,
				seatColumnNum);
		//Optional<User> user= userRepository.findById(userId);
		User user=new User();
		if (seat == null) {
			return "Seat not found";
		}

		if (seat.isBookingStatus()) {
			if (seat.getUserId().getUserId()==userId) {
				return "You have already booked this seat";
			} else {
				return "Seat is already booked by another user";
			}
		}

		/*if (!isBookingWithinMonth()) {
			return "You can only book a seat for a month";
		}*/

		seat.setBookingStatus(true);
		user.setUserId(userId);
		seat.setBookingStartDate(LocalDate.now());
		seat.setBookingEndDate(seat.getBookingStartDate().plusDays(30));
		seat.setUserId(user);
		seatRepository.save(seat);

		return "Seat booked successfully";
	}

/*	public Seat showDetails(long userId){
		//Seat seat = new Seat();
		Optional<Seat> seat1 = seatRepository.findAll().stream().filter(seat->seat.getUserId().getUserId()==userId).findFirst();
		log.info("seat {}",seat1.get().getUserId());
		if(seat1.isPresent()){
			return seat1.get();
		}
		return null;
	}*/

	public String getSeatStatus(long userId, int floorNum, int seatRowNum, int seatColumnNum) {
		Seat seat = seatRepository.findByFloorFloorNumAndSeatRowNumAndSeatColumnNum(floorNum, seatRowNum,
				seatColumnNum);
		if (seat == null) {
			return "Seat not found";
		}

		if (seat.isBookingStatus()) {
			if (seat.getUserId().getUserId()==userId) {
				return "Seat is booked by "+userId+" Seat number: " + seat.getSeatRowNum() + " :row - column: " + seat.getSeatColumnNum();
			} else {
				return "Seat is already booked by another user";
			}
		}

		return "Seat is available";
	}

	/*private boolean isBookingWithinMonth() {
		LocalDate currentDate = LocalDate.now();
		LocalDate bookingLimit = currentDate.plusMonths(1);

		// Calculate the last day of the current month
		LocalDate lastDayOfMonth = currentDate.withDayOfMonth(currentDate.lengthOfMonth());

		return bookingLimit.isBefore(lastDayOfMonth) || bookingLimit.isEqual(lastDayOfMonth);
	}*/

}
