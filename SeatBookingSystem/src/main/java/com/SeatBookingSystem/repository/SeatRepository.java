package com.SeatBookingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SeatBookingSystem.entity.Seat;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long>{

	Seat findByFloorFloorNumAndSeatRowNumAndSeatColumnNum(int floorNum, int seatRowNum, int seatColumnNum);

}
