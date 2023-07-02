package com.SeatBookingSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SeatBookingSystem.entity.Building;

public interface BuildingRepository extends JpaRepository<Building, Integer> {

}
